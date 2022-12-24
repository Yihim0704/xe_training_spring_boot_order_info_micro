package com.example.order_info_micro.business;

import com.example.order_info_micro.integration.RTMagicWandCatalogueServiceImpl;
import com.example.order_info_micro.integration.RTWizardInfoServiceImpl;
import com.example.order_info_micro.model.WizardInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WizardInfoValidationImpl implements WizardInfoValidation {

    @Autowired
    private RTWizardInfoServiceImpl rtWizardInfoServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(RTMagicWandCatalogueServiceImpl.class);

    @Override
    public Map<String, String> wizardInfoDetailsValidation(String id, String name) {
        Map<String, String> wizardInfoDetailsResult = new HashMap<>();
        WizardInfo existWizardInfo = rtWizardInfoServiceImpl.getWizardInfoById(id);
        String existWizardInfoId = existWizardInfo.getId();
        String existWizardInfoName = existWizardInfo.getName();
        boolean existWizardInfoActive = existWizardInfo.isActive();
        if (id.equals(existWizardInfoId)) {
            if (name.equalsIgnoreCase(existWizardInfoName)) {
                if (existWizardInfoActive) {
                    wizardInfoDetailsResult.put("Result", "Wizard details are valid.");
                    wizardInfoDetailsResult.put("HttpStatus", HttpStatus.OK.toString());
                    return wizardInfoDetailsResult;
                } else {
                    wizardInfoDetailsResult.put("Result", "Wizard status is not active.");
                    wizardInfoDetailsResult.put("HttpStatus", HttpStatus.OK.toString());
                    return wizardInfoDetailsResult;
                }
            } else {
                wizardInfoDetailsResult.put("Result", "Wizard name is not match.");
                wizardInfoDetailsResult.put("HttpStatus", HttpStatus.NOT_FOUND.toString());
                return wizardInfoDetailsResult;
            }
        } else {
            wizardInfoDetailsResult.put("Result", "Wizard Id is not match.");
            wizardInfoDetailsResult.put("HttpStatus", HttpStatus.NOT_FOUND.toString());
            return wizardInfoDetailsResult;
        }
    }
}
