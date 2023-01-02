package com.example.order_info_micro.integration;

import com.example.order_info_micro.model.MagicWandCatalogueModel;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.List;

public interface RTMagicWandCatalogueService {

    List<MagicWandCatalogueModel> getAllMagicWandCatalogue() throws HttpRequestMethodNotSupportedException;

    MagicWandCatalogueModel getMagicWandCatalogueById(String id) throws HttpRequestMethodNotSupportedException;

    void updateMagicWandCatalogueById(String id, MagicWandCatalogueModel magicWandCatalogueModel) throws HttpRequestMethodNotSupportedException;
}
