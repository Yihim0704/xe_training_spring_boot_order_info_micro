package com.example.order_info_micro.integration;

import com.example.order_info_micro.model.WizardInfoModel;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.List;

public interface RTWizardInfoService {

    List<WizardInfoModel> getAllWizardInfo() throws HttpRequestMethodNotSupportedException;

    WizardInfoModel getWizardInfoById(String id) throws HttpRequestMethodNotSupportedException;
}
