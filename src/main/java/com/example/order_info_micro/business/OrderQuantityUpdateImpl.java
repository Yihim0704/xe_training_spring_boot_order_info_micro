package com.example.order_info_micro.business;

import com.example.order_info_micro.entity.OrderInfo;
import com.example.order_info_micro.exception.client.MagicWandCatalogue.MagicWandCatalogueNotValidException;
import com.example.order_info_micro.integration.RTMagicWandCatalogueService;
import com.example.order_info_micro.model.MagicWandCatalogue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@Service
public class OrderQuantityUpdateImpl implements OrderQuantityUpdate {

    private static final Logger logger = LoggerFactory.getLogger(OrderQuantityUpdateImpl.class);

    @Autowired
    RTMagicWandCatalogueService rtMagicWandCatalogueService;

    @Override
    public MagicWandCatalogue updateMagicWandCatalogueStockOnUpdateOrderInfo(String magicWandCatalogueId, int updatedQuantity, OrderInfo currentOrderInfo) throws HttpRequestMethodNotSupportedException {
        logger.info("Server OrderQuantityUpdate.updateMagicWandCatalogueStockOnUpdateOrderInfo");
        MagicWandCatalogue currentMagicWandCatalogue = rtMagicWandCatalogueService.getMagicWandCatalogueById(magicWandCatalogueId);
        int currentMagicWandCatalogueStock = currentMagicWandCatalogue.getStock();
        int currentOrderInfoQuantity = currentOrderInfo.getQuantity();
        if (updatedQuantity > currentOrderInfoQuantity) {
            int stockDecrement = updatedQuantity - currentOrderInfoQuantity;
            int updatedStock = currentMagicWandCatalogueStock - stockDecrement;
            if (updatedStock < 0) {
                throw new MagicWandCatalogueNotValidException("Magic wand catalogue's stock is not sufficient. " + "(Current: " + currentOrderInfoQuantity + ", Available: " + currentMagicWandCatalogueStock + ")", HttpStatus.BAD_REQUEST.value());
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
    public MagicWandCatalogue updateMagicWandCatalogueStockOnSaveOrderInfo(String magicWandCatalogueId, int savedQuantity) throws HttpRequestMethodNotSupportedException {
        logger.info("Server OrderQuantityUpdate.updateMagicWandCatalogueStockOnSaveOrderInfo");
        MagicWandCatalogue currentMagicWandCatalogue = rtMagicWandCatalogueService.getMagicWandCatalogueById(magicWandCatalogueId);
        int currentMagicWandCatalogueStock = currentMagicWandCatalogue.getStock();
        if (savedQuantity > currentMagicWandCatalogueStock) {
            throw new MagicWandCatalogueNotValidException("Magic wand catalogue's stock is not sufficient. " + "(Selected: " + savedQuantity + ", Available: " + currentMagicWandCatalogueStock + ")", HttpStatus.BAD_REQUEST.value());
        } else {
            int updatedStock = currentMagicWandCatalogueStock - savedQuantity;
            currentMagicWandCatalogue.setStock(updatedStock);
            return currentMagicWandCatalogue;
        }
    }

    @Override
    public MagicWandCatalogue updateMagicWandCatalogueStockOnDeleteOrderInfo(String magicWandCatalogueId, int currentOrderQuantity) throws HttpRequestMethodNotSupportedException {
        logger.info("Server OrderQuantityUpdate.updateMagicWandCatalogueStockOnDeleteOrderInfo");
        MagicWandCatalogue currentMagicWandCatalogue = rtMagicWandCatalogueService.getMagicWandCatalogueById(magicWandCatalogueId);
        if (currentMagicWandCatalogue != null) {
            int currentMagicWandCatalogueStock = currentMagicWandCatalogue.getStock();
            int updateMagicWandCatalogueStock = currentMagicWandCatalogueStock + currentOrderQuantity;
            currentMagicWandCatalogue.setStock(updateMagicWandCatalogueStock);
            return currentMagicWandCatalogue;
        }
        return null;
    }
}
