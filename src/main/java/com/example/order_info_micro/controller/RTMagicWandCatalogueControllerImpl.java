package com.example.order_info_micro.controller;

import com.example.order_info_micro.integration.RTMagicWandCatalogueServiceImpl;
import com.example.order_info_micro.model.MagicWandCatalogue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/magic-wand-catalogue")
public class RTMagicWandCatalogueControllerImpl implements RTMagicWandCatalogueController {

    @Autowired
    private RTMagicWandCatalogueServiceImpl rtMagicWandCatalogueServiceImpl;

    @Override
    @GetMapping("find-all")
    public MagicWandCatalogue[] findAllMagicWandCatalogue() {
        return rtMagicWandCatalogueServiceImpl.getAllMagicWandCatalogue();
    }

    @Override
    @GetMapping("find-id/{id}")
    public MagicWandCatalogue findMagicWandCatalogueById(@PathVariable String id) {
        return rtMagicWandCatalogueServiceImpl.getMagicWandCatalogueById(id);
    }
}