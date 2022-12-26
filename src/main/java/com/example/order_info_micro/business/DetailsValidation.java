package com.example.order_info_micro.business;

import java.util.Map;

public interface DetailsValidation {
    Map<String, String> wizardInfoDetailsValidation(String id, String name);

    Map<String, String> magicWandCatalogueDetailsValidation(String id, String name, String wizardInfoId);

    Map<String, String> magicWandCatalogueDetailsValidationOnUpdate(String id, String name, String wizardInfoId);
}
