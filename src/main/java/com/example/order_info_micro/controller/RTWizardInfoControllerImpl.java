package com.example.order_info_micro.controller;

import com.example.order_info_micro.integration.RTWizardInfoServiceImpl;
import com.example.order_info_micro.model.WizardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wizard-info")
public class RTWizardInfoControllerImpl implements RTWizardInfoController {

    @Autowired
    private RTWizardInfoServiceImpl RTWizardInfoServiceImpl;

    @Override
    @GetMapping("find-id/{id}")
    public WizardInfo findWizardInfoById(@PathVariable String id) {
        return RTWizardInfoServiceImpl.getWizardInfoById(id);
    }
}