package com.example.order_info_micro.model;

import lombok.Data;

import java.util.UUID;

@Data
public class MagicWandCatalogue {
    private UUID id;
    private String name;
    private String description;
    private int ageLimit;
    private int stock;
}
