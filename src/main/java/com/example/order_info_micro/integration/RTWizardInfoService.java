package com.example.order_info_micro.integration;

import com.example.order_info_micro.model.WizardInfo;

public interface RTWizardInfoService {
    
    WizardInfo[] getAllWizardInfo();

    WizardInfo getWizardInfoById(String id);
}
