package com.example.order_info_micro.database;

import com.example.order_info_micro.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, String> {
    List<OrderInfo> findOrderInfoByWizardId(String id);
}

