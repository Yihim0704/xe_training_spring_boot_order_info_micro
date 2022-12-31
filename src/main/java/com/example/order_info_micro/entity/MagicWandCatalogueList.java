package com.example.order_info_micro.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MagicWandCatalogueList {
    private List<MagicWandCatalogue> magicWandCatalogues;

    public MagicWandCatalogueList() {
        magicWandCatalogues = new ArrayList<>();
    }
}
