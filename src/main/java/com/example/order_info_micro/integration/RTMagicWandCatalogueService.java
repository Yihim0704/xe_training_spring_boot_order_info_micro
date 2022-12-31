package com.example.order_info_micro.integration;

import com.example.order_info_micro.entity.MagicWandCatalogue;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.List;

public interface RTMagicWandCatalogueService {

    List<MagicWandCatalogue> getAllMagicWandCatalogue() throws HttpRequestMethodNotSupportedException;

    MagicWandCatalogue getMagicWandCatalogueById(String id) throws HttpRequestMethodNotSupportedException;

    void updateMagicWandCatalogueById(String id, MagicWandCatalogue magicWandCatalogue) throws HttpRequestMethodNotSupportedException;
}
