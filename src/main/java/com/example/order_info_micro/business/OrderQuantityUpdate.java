package com.example.order_info_micro.business;

import com.example.order_info_micro.entity.OrderInfo;
import com.example.order_info_micro.model.MagicWandCatalogueModel;
import org.springframework.web.HttpRequestMethodNotSupportedException;

public interface OrderQuantityUpdate {
    MagicWandCatalogueModel updateMagicWandCatalogueStockOnUpdateOrderInfo(String magicWandCatalogueId, int updatedQuantity, OrderInfo currentOrderInfo) throws HttpRequestMethodNotSupportedException;

    MagicWandCatalogueModel updateMagicWandCatalogueStockOnSaveOrderInfo(String magicWandCatalogueId, int savedQuantity) throws HttpRequestMethodNotSupportedException;

    MagicWandCatalogueModel updateMagicWandCatalogueStockOnDeleteOrderInfo(String magicWandCatalogueId, int currentOrderQuantity) throws HttpRequestMethodNotSupportedException;
}
