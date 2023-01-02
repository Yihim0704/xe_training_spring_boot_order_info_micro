package com.example.order_info_micro.business;

import com.example.order_info_micro.entity.OrderInfo;
import com.example.order_info_micro.model.MagicWandCatalogue;
import org.springframework.web.HttpRequestMethodNotSupportedException;

public interface OrderQuantityUpdate {
    MagicWandCatalogue updateMagicWandCatalogueStockOnUpdateOrderInfo(String magicWandCatalogueId, int updatedQuantity, OrderInfo currentOrderInfo) throws HttpRequestMethodNotSupportedException;

    MagicWandCatalogue updateMagicWandCatalogueStockOnSaveOrderInfo(String magicWandCatalogueId, int savedQuantity) throws HttpRequestMethodNotSupportedException;

    MagicWandCatalogue updateMagicWandCatalogueStockOnDeleteOrderInfo(String magicWandCatalogueId, int currentOrderQuantity) throws HttpRequestMethodNotSupportedException;
}
