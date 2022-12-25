package com.example.order_info_micro.service;

import com.example.order_info_micro.business.MagicWandCatalogueValidationImpl;
import com.example.order_info_micro.business.OrderQuantityUpdateImpl;
import com.example.order_info_micro.business.WizardInfoValidationImpl;
import com.example.order_info_micro.database.OrderInfoRepository;
import com.example.order_info_micro.exception.client.MagicWandCatalogueNotValidException;
import com.example.order_info_micro.exception.client.WizardInfoNotValidException;
import com.example.order_info_micro.exception.server.NoOrderInfoFoundException;
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
    private WizardInfoValidationImpl wizardInfoValidationImpl;

    @Autowired
    private MagicWandCatalogueValidationImpl magicWandCatalogueValidationImpl;

    @Autowired
    private OrderQuantityUpdateImpl orderQuantityUpdateImpl;

    @Autowired
    private RTMagicWandCatalogueServiceImpl rtMagicWandCatalogueServiceImpl;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Override
    public OrderInfo saveOrderInfo(OrderInfo orderInfo) {
        Map<String, String> validWizardInfo = wizardInfoValidationImpl.wizardInfoDetailsValidation(orderInfo.getWizardId(), orderInfo.getWizardName());
        Map<String, String> validMagicWandCatalogue = magicWandCatalogueValidationImpl.magicWandCatalogueDetailsValidation(orderInfo.getMagicWandCatalogueId(), orderInfo.getMagicWandCatalogueName(), orderInfo.getWizardId());
        if (validWizardInfo.get("Result").equalsIgnoreCase("Wizard details are valid.")) {
            if (validMagicWandCatalogue.get("Result").equalsIgnoreCase("Magic wand catalogue details are valid and wizard's age is applicable.")) {
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
        OrderInfo currentOderInfo = getOrderInfoById(id);
        MagicWandCatalogue updatedMagicWandCatalogueStock = orderQuantityUpdateImpl.updateMagicWandCatalogueStock(id, orderInfo.getMagicWandCatalogueId(), orderInfo.getQuantity(), currentOderInfo);
        rtMagicWandCatalogueServiceImpl.updateMagicWandCatalogueById(orderInfo.getMagicWandCatalogueId(), updatedMagicWandCatalogueStock);
        OrderInfo existingOrderInfo = orderInfoRepository.findById(id).orElse(null);
        existingOrderInfo.setQuantity(orderInfo.getQuantity());
        return orderInfoRepository.save(existingOrderInfo);
    }

    @Override
    public String deleteOrderInfoById(String id) {
        if (!orderInfoRepository.findById(id).isPresent()) {
            throw new OrderInfoIdNotFoundException("Order info does not exist.");
        }
        orderInfoRepository.deleteById(id);
        return "Order info has been deleted successfully !\tID: " + id;
    }
}
