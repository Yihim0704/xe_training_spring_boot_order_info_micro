package com.example.order_info_micro.model;

import lombok.Data;

import java.util.UUID;

@Data
public class WizardInfoModel {
    private UUID id;
    private String name;
    private int age;
    private String joinedDate;
    private boolean active;
}
