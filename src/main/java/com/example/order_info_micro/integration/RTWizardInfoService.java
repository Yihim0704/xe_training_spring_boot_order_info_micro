package com.example.order_info_micro.integration;

import com.example.order_info_micro.model.WizardInfo;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.List;

public interface RTWizardInfoService {

    List<WizardInfo> getAllWizardInfo() throws HttpRequestMethodNotSupportedException;

    WizardInfo getWizardInfoById(String id) throws HttpRequestMethodNotSupportedException;
}
