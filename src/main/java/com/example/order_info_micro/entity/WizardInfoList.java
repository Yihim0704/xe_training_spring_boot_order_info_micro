package com.example.order_info_micro.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WizardInfoList {
    private List<WizardInfo> wizardInfos;

    public WizardInfoList() {
        wizardInfos = new ArrayList<>();
    }
}
