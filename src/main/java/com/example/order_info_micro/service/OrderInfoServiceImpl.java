package com.example.order_info_micro.service;

import com.example.order_info_micro.database.OrderInfoRepository;
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
        return orderInfoRepository.save(orderInfo);
    }

    @Override
    public List<OrderInfo> getAllOrderInfo() {
        return orderInfoRepository.findAll();
    }

    @Override
    public OrderInfo getOrderInfoById(String id) {
        return orderInfoRepository.findById(id).orElse(null);
    }

    @Override
    public OrderInfo updateOrderInfoById(String id, OrderInfo orderInfo) {
        OrderInfo existingOrderInfo = orderInfoRepository.findById(id).orElse(null);
        existingOrderInfo.setQuantity(orderInfo.getQuantity());
        return orderInfoRepository.save(existingOrderInfo);
    }

    @Override
    public String deleteOrderInfoById(String id) {
        orderInfoRepository.deleteById(id);
        return "Order info has been deleted successfully !\tID: " + id;
    }
}
