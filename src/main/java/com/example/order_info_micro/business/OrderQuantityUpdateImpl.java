package com.example.order_info_micro.business;

import com.example.order_info_micro.exception.client.MagicWandCatalogueNotValidException;
import com.example.order_info_micro.integration.RTMagicWandCatalogueServiceImpl;
import com.example.order_info_micro.model.MagicWandCatalogue;
import com.example.order_info_micro.model.OrderInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderQuantityUpdateImpl implements OrderQuantityUpdate {

    private static final Logger logger = LoggerFactory.getLogger(RTMagicWandCatalogueServiceImpl.class);

    @Autowired
    RTMagicWandCatalogueServiceImpl rtMagicWandCatalogueServiceImpl;

    @Override
    public MagicWandCatalogue updateMagicWandCatalogueStock(String orderInfoId, String magicWandCatalogueId, int updatedQuantity, OrderInfo currentOrderInfo) {
        MagicWandCatalogue currentMagicWandCatalogue = rtMagicWandCatalogueServiceImpl.getMagicWandCatalogueById(magicWandCatalogueId);
        int currentMagicWandCatalogueStock = currentMagicWandCatalogue.getStock();
        int currentOrderInfoQuantity = currentOrderInfo.getQuantity();
        if (updatedQuantity > currentOrderInfoQuantity) {
            int stockDecrement = updatedQuantity - currentOrderInfoQuantity;
            int updatedStock = currentMagicWandCatalogueStock - stockDecrement;
            if (updatedStock < 0) {
                throw new MagicWandCatalogueNotValidException("Magic wand catalogue's stock is not sufficient.", HttpStatus.BAD_REQUEST.toString());
            }
            currentMagicWandCatalogue.setStock(updatedStock);
            return currentMagicWandCatalogue;
        } else {
            int stockIncrement = currentOrderInfoQuantity - updatedQuantity;
            int updatedStock = currentMagicWandCatalogueStock + stockIncrement;
            currentMagicWandCatalogue.setStock(updatedStock);
            return currentMagicWandCatalogue;
        }
    }

    @Override
    public Map<String, String> updateDuplicatedOrderInfo(int updatedQuantity) {
        return null;
    }
}
