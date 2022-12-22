package com.example.order_info_micro.controller;

import com.example.order_info_micro.model.OrderInfo;

import java.util.List;

public interface OrderInfoController {

    OrderInfo addOrderInfo(OrderInfo orderInfo);

    List<OrderInfo> findAllOrderInfo();

    OrderInfo findOrderInfoById(String id);

    OrderInfo changeOrderInfoById(String id, OrderInfo orderInfo);

    String removeOrderInfoById(String id);
}
