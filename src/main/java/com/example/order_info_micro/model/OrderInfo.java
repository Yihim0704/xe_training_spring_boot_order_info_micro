package com.example.order_info_micro.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_info")
public class OrderInfo {
    @Id
    private String id;
    private String wizardId;
    private String wizardName;
    private String magicWandCatalogueId;
    private String magicWandCatalogueName;
    private int quantity;

    public OrderInfo() {
    }

    public OrderInfo(String id, String wizardId, String wizardName, String magicWandCatalogueId, String magicWandCatalogueName, int quantity) {
        this.id = id;
        this.wizardId = wizardId;
        this.wizardName = wizardName;
        this.magicWandCatalogueId = magicWandCatalogueId;
        this.magicWandCatalogueName = magicWandCatalogueName;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWizardId() {
        return wizardId;
    }

    public void setWizardId(String wizardId) {
        this.wizardId = wizardId;
    }

    public String getWizardName() {
        return wizardName;
    }

    public void setWizardName(String wizardName) {
        this.wizardName = wizardName;
    }

    public String getMagicWandCatalogueId() {
        return magicWandCatalogueId;
    }

    public void setMagicWandCatalogueId(String magicWandCatalogueId) {
        this.magicWandCatalogueId = magicWandCatalogueId;
    }

    public String getMagicWandCatalogueName() {
        return magicWandCatalogueName;
    }

    public void setMagicWandCatalogueName(String magicWandCatalogueName) {
        this.magicWandCatalogueName = magicWandCatalogueName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
