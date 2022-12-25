package com.example.order_info_micro.business;

import com.example.order_info_micro.model.MagicWandCatalogue;
import com.example.order_info_micro.model.OrderInfo;

import java.util.Map;

public interface OrderQuantityUpdate {
    MagicWandCatalogue updateMagicWandCatalogueStock(String orderInfoId, String magicWandCatalogueId, int updatedQuantity, OrderInfo currentOrderInfo);

    Map<String, String> updateDuplicatedOrderInfo(int updatedQuantity);
}
