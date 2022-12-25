package com.example.order_info_micro.integration;

import com.example.order_info_micro.model.MagicWandCatalogue;

public interface RTMagicWandCatalogueService {
    MagicWandCatalogue getMagicWandCatalogueById(String id);

    String updateMagicWandCatalogueById(String id, MagicWandCatalogue magicWandCatalogue);
}
