package com.example.order_info_micro.service;

import com.example.order_info_micro.business.DetailsValidationImpl;
import com.example.order_info_micro.business.NameUpdateImpl;
import com.example.order_info_micro.business.OrderQuantityUpdateImpl;
import com.example.order_info_micro.database.OrderInfoRepository;
import com.example.order_info_micro.exception.client.MagicWandCatalogueNotValidException;
import com.example.order_info_micro.exception.client.WizardInfoNotValidException;
import com.example.order_info_micro.exception.server.NoOrderInfoFoundException;
import com.example.order_info_micro.exception.server.OrderInfoExistException;
import com.example.order_info_micro.exception.server.OrderInfoIdNotFoundException;
import com.example.order_info_micro.integration.RTMagicWandCatalogueServiceImpl;
import com.example.order_info_micro.model.MagicWandCatalogue;
import com.example.order_info_micro.model.OrderInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    private static final Logger logger = LoggerFactory.getLogger(RTMagicWandCatalogueServiceImpl.class);

    @Autowired
    private DetailsValidationImpl detailsValidationImpl;

    @Autowired
    private OrderQuantityUpdateImpl orderQuantityUpdateImpl;

    @Autowired
    private RTMagicWandCatalogueServiceImpl rtMagicWandCatalogueServiceImpl;

    @Autowired
    private NameUpdateImpl nameUpdateImpl;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Override
    public OrderInfo saveOrderInfo(OrderInfo orderInfo) {
        // To validate wizard info
        Map<String, String> validWizardInfo = detailsValidationImpl.wizardInfoDetailsValidation(orderInfo.getWizardId(), orderInfo.getWizardName().trim());
        // To validate magic wand catalogue
        Map<String, String> validMagicWandCatalogue = detailsValidationImpl.magicWandCatalogueDetailsValidation(orderInfo.getMagicWandCatalogueId(), orderInfo.getMagicWandCatalogueName().trim(), orderInfo.getWizardId());
        if (validWizardInfo.get("Result").equalsIgnoreCase("Wizard details are valid.")) {
            if (validMagicWandCatalogue.get("Result").equalsIgnoreCase("Magic wand catalogue details are valid and wizard's age is applicable.")) {
                // If both validations passed, proceed to check duplicated data
                List<OrderInfo> existOrderInfo = orderInfoRepository.findOrderInfoByWizardId(orderInfo.getWizardId());
                // Check the list of data whether a data is consisting both Ids
                for (OrderInfo info : existOrderInfo) {
                    if (info.getWizardId().equals(orderInfo.getWizardId()) && info.getMagicWandCatalogueId().equals(orderInfo.getMagicWandCatalogueId())) {
                        throw new OrderInfoExistException("Order info exist, consider update it with order Id: " + info.getId());
                    }
                }
                // If no duplicated, then proceed to normal saving
                // When saving, update the magic wand catalogue stock as well
                MagicWandCatalogue updateMagicWandCatalogueStock = orderQuantityUpdateImpl.updateMagicWandCatalogueStockOnSaveOrderInfo(orderInfo.getMagicWandCatalogueId(), orderInfo.getQuantity());
                rtMagicWandCatalogueServiceImpl.updateMagicWandCatalogueById(orderInfo.getMagicWandCatalogueId(), updateMagicWandCatalogueStock);
                String id = UUID.randomUUID().toString();
                orderInfo.setId(id);
                orderInfo.setWizardName(orderInfo.getWizardName().trim());
                orderInfo.setMagicWandCatalogueName(orderInfo.getMagicWandCatalogueName().trim());
                return orderInfoRepository.save(orderInfo);
            } else {
                throw new MagicWandCatalogueNotValidException(validMagicWandCatalogue.get("Result"), validMagicWandCatalogue.get("HttpStatus"));
            }
        } else {
            throw new WizardInfoNotValidException(validWizardInfo.get("Result"), validWizardInfo.get("HttpStatus"));
        }
    }

    @Override
    public List<OrderInfo> getAllOrderInfo() {
        if (orderInfoRepository.findAll().isEmpty()) {
            throw new NoOrderInfoFoundException("There is no order info in the database.");
        }
        return orderInfoRepository.findAll();
    }

    @Override
    public OrderInfo getOrderInfoById(String id) {
        if (!orderInfoRepository.findById(id).isPresent()) {
            throw new OrderInfoIdNotFoundException("Order info does not exist.");
        }
        return orderInfoRepository.findById(id).orElse(null);
    }

    @Override
    public OrderInfo updateOrderInfoById(String id, OrderInfo orderInfo) {
        if (!orderInfoRepository.findById(id).isPresent()) {
            throw new OrderInfoIdNotFoundException("Order info does not exist.");
        }
        // To validate wizard info
        Map<String, String> validWizardInfo = detailsValidationImpl.wizardInfoDetailsValidation(orderInfo.getWizardId(), orderInfo.getWizardName().trim());
        // To validate magic wand catalogue
        Map<String, String> validMagicWandCatalogue = detailsValidationImpl.magicWandCatalogueDetailsValidationOnUpdate(orderInfo.getMagicWandCatalogueId(), orderInfo.getMagicWandCatalogueName().trim(), orderInfo.getWizardId());
        if (validWizardInfo.get("Result").equalsIgnoreCase("Wizard details are valid.")) {
            if (validMagicWandCatalogue.get("Result").equalsIgnoreCase("Magic wand catalogue details are valid and wizard's age is applicable.")) {
                OrderInfo currentOderInfo = getOrderInfoById(id);
                MagicWandCatalogue updatedMagicWandCatalogueStock = orderQuantityUpdateImpl.updateMagicWandCatalogueStockOnUpdateOrderInfo(orderInfo.getMagicWandCatalogueId(), orderInfo.getQuantity(), currentOderInfo);
                rtMagicWandCatalogueServiceImpl.updateMagicWandCatalogueById(orderInfo.getMagicWandCatalogueId(), updatedMagicWandCatalogueStock);
                List<OrderInfo> currentAllOrderInfo = getAllOrderInfo();
                nameUpdateImpl.updateOrderInfoWizardAndMagicWandName(currentAllOrderInfo, currentOderInfo, orderInfo);
                currentOderInfo.setQuantity(orderInfo.getQuantity());
                return orderInfoRepository.save(currentOderInfo);
            } else {
                throw new MagicWandCatalogueNotValidException(validMagicWandCatalogue.get("Result"), validMagicWandCatalogue.get("HttpStatus"));
            }
        } else {
            throw new WizardInfoNotValidException(validWizardInfo.get("Result"), validWizardInfo.get("HttpStatus"));
        }
    }

    @Override
    public String deleteOrderInfoById(String id) {
        if (!orderInfoRepository.findById(id).isPresent()) {
            throw new OrderInfoIdNotFoundException("Order info does not exist.");
        }
        OrderInfo currentOrderInfo = getOrderInfoById(id);
        MagicWandCatalogue updatedMagicWandCatalogueStock = orderQuantityUpdateImpl.updateMagicWandCatalogueStockOnDeleteOrderInfo(currentOrderInfo.getMagicWandCatalogueId(), currentOrderInfo.getQuantity());
        if (updatedMagicWandCatalogueStock != null) {
            rtMagicWandCatalogueServiceImpl.updateMagicWandCatalogueById(currentOrderInfo.getMagicWandCatalogueId(), updatedMagicWandCatalogueStock);
        }
        orderInfoRepository.deleteById(id);
        return "Order info has been deleted successfully !\tID: " + id;
    }
}
