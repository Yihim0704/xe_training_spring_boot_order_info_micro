package com.example.order_info_micro.service;

import com.example.order_info_micro.database.OrderInfoRepository;
import com.example.order_info_micro.exception.NoOrderInfoFoundException;
import com.example.order_info_micro.exception.OrderInfoIdNotFoundException;
import com.example.order_info_micro.model.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Override
    public OrderInfo saveOrderInfo(OrderInfo orderInfo) {
        String id = UUID.randomUUID().toString();
        orderInfo.setId(id);
        orderInfo.setWizardName(orderInfo.getWizardName().trim());
        orderInfo.setMagicWandCatalogueName(orderInfo.getMagicWandCatalogueName().trim());
        return orderInfoRepository.save(orderInfo);
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
        OrderInfo existingOrderInfo = orderInfoRepository.findById(id).orElse(null);
        existingOrderInfo.setWizardId(orderInfo.getWizardId().trim());
        existingOrderInfo.setWizardName(orderInfo.getWizardName().trim());
        existingOrderInfo.setMagicWandCatalogueId(orderInfo.getMagicWandCatalogueId().trim());
        existingOrderInfo.setMagicWandCatalogueName(orderInfo.getMagicWandCatalogueName().trim());
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
