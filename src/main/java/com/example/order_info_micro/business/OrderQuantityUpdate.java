package com.example.order_info_micro.business;

import com.example.order_info_micro.model.MagicWandCatalogue;
import com.example.order_info_micro.model.OrderInfo;

public interface OrderQuantityUpdate {
    MagicWandCatalogue updateMagicWandCatalogueStockOnUpdateOrderInfo(String magicWandCatalogueId, int updatedQuantity, OrderInfo currentOrderInfo);

    MagicWandCatalogue updateMagicWandCatalogueStockOnSaveOrderInfo(String magicWandCatalogueId, int savedQuantity);

    MagicWandCatalogue updateMagicWandCatalogueStockOnDeleteOrderInfo(String magicWandCatalogueId, int currentOrderQuantity);
}
