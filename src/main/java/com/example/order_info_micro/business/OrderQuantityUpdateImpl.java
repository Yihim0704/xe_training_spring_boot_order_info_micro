package com.example.order_info_micro.business;

import com.example.order_info_micro.entity.OrderInfo;
import com.example.order_info_micro.exception.client.MagicWandCatalogue.MagicWandCatalogueNotValidException;
import com.example.order_info_micro.integration.RTMagicWandCatalogueService;
import com.example.order_info_micro.model.MagicWandCatalogueModel;
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
    public MagicWandCatalogueModel updateMagicWandCatalogueStockOnUpdateOrderInfo(String magicWandCatalogueId, int updatedQuantity, OrderInfo currentOrderInfo) throws HttpRequestMethodNotSupportedException {
        logger.info("Server OrderQuantityUpdate.updateMagicWandCatalogueStockOnUpdateOrderInfo");
        MagicWandCatalogueModel currentMagicWandCatalogueModel = rtMagicWandCatalogueService.getMagicWandCatalogueById(magicWandCatalogueId);
        int currentMagicWandCatalogueStock = currentMagicWandCatalogueModel.getStock();
        int currentOrderInfoQuantity = currentOrderInfo.getQuantity();
        if (updatedQuantity > currentOrderInfoQuantity) {
            int stockDecrement = updatedQuantity - currentOrderInfoQuantity;
            int updatedStock = currentMagicWandCatalogueStock - stockDecrement;
            if (updatedStock < 0) {
                throw new MagicWandCatalogueNotValidException("Magic wand catalogue's stock is not sufficient. " + "(Current: " + currentOrderInfoQuantity + ", Available: " + currentMagicWandCatalogueStock + ")", HttpStatus.BAD_REQUEST.value());
            }
            currentMagicWandCatalogueModel.setStock(updatedStock);
            return currentMagicWandCatalogueModel;
        } else {
            int stockIncrement = currentOrderInfoQuantity - updatedQuantity;
            int updatedStock = currentMagicWandCatalogueStock + stockIncrement;
            currentMagicWandCatalogueModel.setStock(updatedStock);
            return currentMagicWandCatalogueModel;
        }
    }

    @Override
    public MagicWandCatalogueModel updateMagicWandCatalogueStockOnSaveOrderInfo(String magicWandCatalogueId, int savedQuantity) throws HttpRequestMethodNotSupportedException {
        logger.info("Server OrderQuantityUpdate.updateMagicWandCatalogueStockOnSaveOrderInfo");
        MagicWandCatalogueModel currentMagicWandCatalogueModel = rtMagicWandCatalogueService.getMagicWandCatalogueById(magicWandCatalogueId);
        int currentMagicWandCatalogueStock = currentMagicWandCatalogueModel.getStock();
        if (savedQuantity > currentMagicWandCatalogueStock) {
            throw new MagicWandCatalogueNotValidException("Magic wand catalogue's stock is not sufficient. " + "(Selected: " + savedQuantity + ", Available: " + currentMagicWandCatalogueStock + ")", HttpStatus.BAD_REQUEST.value());
        } else {
            int updatedStock = currentMagicWandCatalogueStock - savedQuantity;
            currentMagicWandCatalogueModel.setStock(updatedStock);
            return currentMagicWandCatalogueModel;
        }
    }

    @Override
    public MagicWandCatalogueModel updateMagicWandCatalogueStockOnDeleteOrderInfo(String magicWandCatalogueId, int currentOrderQuantity) throws HttpRequestMethodNotSupportedException {
        logger.info("Server OrderQuantityUpdate.updateMagicWandCatalogueStockOnDeleteOrderInfo");
        MagicWandCatalogueModel currentMagicWandCatalogueModel = rtMagicWandCatalogueService.getMagicWandCatalogueById(magicWandCatalogueId);
        if (currentMagicWandCatalogueModel != null) {
            int currentMagicWandCatalogueStock = currentMagicWandCatalogueModel.getStock();
            int updateMagicWandCatalogueStock = currentMagicWandCatalogueStock + currentOrderQuantity;
            currentMagicWandCatalogueModel.setStock(updateMagicWandCatalogueStock);
            return currentMagicWandCatalogueModel;
        }
        return null;
    }
}
