package com.example.order_info_micro.controller;

import com.example.order_info_micro.integration.RTMagicWandCatalogueServiceImpl;
import com.example.order_info_micro.model.MagicWandCatalogue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/magic-wand-catalogue")
public class RTMagicWandCatalogueControllerImpl implements RTMagicWandCatalogueController {

    @Autowired
    private RTMagicWandCatalogueServiceImpl rtMagicWandCatalogueService;

    @Override
    @GetMapping("find-all")
    public MagicWandCatalogue[] findAllMagicWandCatalogue() {
        return rtMagicWandCatalogueService.getAllMagicWandCatalogue();
    }
}