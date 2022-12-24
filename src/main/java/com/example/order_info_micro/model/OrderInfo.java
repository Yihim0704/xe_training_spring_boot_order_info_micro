package com.example.order_info_micro.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "order_info")
public class OrderInfo {
    @Id
    private String id;

    @Length(min = 36, max = 36, message = "Wizard Id must be 36 characters including (-).")
    @Pattern(regexp = "^[a-zA-Z0-9-]+$", message = "Wizard Id should not be containing special characters except (-).")
    private String wizardId;

    @NotNull(message = "Wizard name should not be null.")
    @NotBlank(message = "Wizard name should not be blank.")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Wizard name should not be containing special characters and numbers.")
    private String wizardName;

    @Length(min = 36, max = 36, message = "Magic wand catalogue Id must be 36 characters including (-).")
    @Pattern(regexp = "^[a-zA-Z0-9-]+$", message = "Magic wand catalogue Id should not be containing special characters except (-).")
    private String magicWandCatalogueId;
    
    @NotNull(message = "Magic wand name should not be null.")
    @NotBlank(message = "Magic wand name should not be blank.")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Magic wand name should not be containing special characters and numbers.")
    private String magicWandCatalogueName;

    @Min(0)
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
