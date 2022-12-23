package com.example.order_info_micro.model;

public class MagicWandCatalogue {
    private String id;
    private String name;
    private String description;
    private int ageLimit;
    private int stock;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "MagicWandCatalogue{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ageLimit=" + ageLimit +
                ", stock=" + stock +
                '}';
    }
}
