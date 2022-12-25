package com.example.order_info_micro.integration;

import com.example.order_info_micro.model.MagicWandCatalogue;

public interface RTMagicWandCatalogueService {
    MagicWandCatalogue getMagicWandCatalogueById(String id);

    void updateMagicWandCatalogueById(String id, MagicWandCatalogue magicWandCatalogue);
}
