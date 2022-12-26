package com.example.order_info_micro.business;

import com.example.order_info_micro.model.OrderInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NameUpdateImpl implements NameUpdate {
    @Override
    public void updateOrderInfoWizardAndMagicWandName(List<OrderInfo> currentAllOrderInfo, OrderInfo currentOrderInfo, OrderInfo updatedOrderInfo) {
        String currentWizardName = currentOrderInfo.getWizardName();
        String currentMagicWandName = currentOrderInfo.getMagicWandCatalogueName();
        String updatedWizardName = updatedOrderInfo.getWizardName().trim();
        String updatedMagicWandName = updatedOrderInfo.getMagicWandCatalogueName().trim();
        for (OrderInfo info : currentAllOrderInfo) {
            if (currentWizardName.equalsIgnoreCase(info.getWizardName())) {
                info.setWizardName(updatedWizardName);
            }
            if (currentMagicWandName.equalsIgnoreCase(info.getMagicWandCatalogueName())) {
                info.setMagicWandCatalogueName(updatedMagicWandName);
            }
        }
    }
}
