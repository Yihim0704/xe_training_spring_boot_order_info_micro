package com.example.order_info_micro.service;

import com.example.order_info_micro.model.OrderInfo;

import java.util.List;

public interface OrderInfoService {

    OrderInfo saveOrderInfo(OrderInfo orderInfo);

    List<OrderInfo> getAllOrderInfo();

    OrderInfo getOrderInfoById(String id);

    OrderInfo updateOrderInfoById(String id, OrderInfo orderInfo);

    String deleteOrderInfoById(String id);
}
