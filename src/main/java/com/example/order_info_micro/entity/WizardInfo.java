package com.example.order_info_micro.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class WizardInfo {
    private UUID id;
    private String name;
    private int age;
    private String joinedDate;
    private boolean active;
}
