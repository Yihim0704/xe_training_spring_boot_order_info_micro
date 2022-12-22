package com.example.order_info_micro.controller;

import com.example.order_info_micro.model.OrderInfo;
import com.example.order_info_micro.service.OrderInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order-info")
public class OrderInfoControllerImpl implements OrderInfoController{
    @Autowired
    private OrderInfoServiceImpl orderInfoServiceImpl;

    @Override
    @PostMapping("add")
    public OrderInfo addOrderInfo(@RequestBody OrderInfo orderInfo) {
        return orderInfoServiceImpl.saveOrderInfo(orderInfo);
    }

    @Override
    @GetMapping("find-all")
    public List<OrderInfo> findAllOrderInfo() {
        return orderInfoServiceImpl.getAllOrderInfo();
    }

    @Override
    @GetMapping("find-id/{id}")
    public OrderInfo findOrderInfoById(@PathVariable String id) {
        return orderInfoServiceImpl.getOrderInfoById(id);
    }

    @Override
    @PutMapping("update-id/{id}")
    public OrderInfo changeOrderInfoById(@PathVariable String id, @RequestBody OrderInfo orderInfo) {
        return orderInfoServiceImpl.updateOrderInfoById(id, orderInfo);
    }

    @Override
    @DeleteMapping("delete-id/{id}")
    public String removeOrderInfoById(String id) {
        return orderInfoServiceImpl.deleteOrderInfoById(id);
    }
}
