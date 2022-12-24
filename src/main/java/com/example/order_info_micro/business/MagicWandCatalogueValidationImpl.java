package com.example.order_info_micro.business;

import com.example.order_info_micro.integration.RTMagicWandCatalogueServiceImpl;
import com.example.order_info_micro.integration.RTWizardInfoServiceImpl;
import com.example.order_info_micro.model.MagicWandCatalogue;
import com.example.order_info_micro.model.WizardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MagicWandCatalogueValidationImpl implements MagicWandCatalogueValidation {

    @Autowired
    RTMagicWandCatalogueServiceImpl rtMagicWandCatalogueServiceImpl;

    @Autowired
    RTWizardInfoServiceImpl rtWizardInfoServiceImpl;

    @Override
    public Map<String, String> magicWandCatalogueDetailsValidation(String id, String name, String wizardInfoId) {
        Map<String, String> magicWandCatalogueDetailsResult = new HashMap<>();
        MagicWandCatalogue existMagicWandCatalogue = rtMagicWandCatalogueServiceImpl.getMagicWandCatalogueById(id);
        WizardInfo existWizardInfo = rtWizardInfoServiceImpl.getWizardInfoById(wizardInfoId);
        String existMagicWandCatalogueId = existMagicWandCatalogue.getId();
        String existMagicWandCatalogueName = existMagicWandCatalogue.getName();
        int existMagicWandCatalogueStock = existMagicWandCatalogue.getStock();
        int existMagicWandCatalogueAgeLimit = existMagicWandCatalogue.getAgeLimit();
        int existWizardInfoAge = existWizardInfo.getAge();
        if (id.equals(existMagicWandCatalogueId)) {
            if (name.equalsIgnoreCase(existMagicWandCatalogueName)) {
                if (existWizardInfoAge <= existMagicWandCatalogueAgeLimit) {
                    if (existMagicWandCatalogueStock > 0) {
                        magicWandCatalogueDetailsResult.put("Result", "Magic wand catalogue details are valid and wizard's age is applicable.");
                        magicWandCatalogueDetailsResult.put("HttpStatus", HttpStatus.OK.toString());
                        return magicWandCatalogueDetailsResult;
                    } else {
                        magicWandCatalogueDetailsResult.put("Result", "Magic wand catalogue is out of stock.");
                        magicWandCatalogueDetailsResult.put("HttpStatus", HttpStatus.OK.toString());
                        return magicWandCatalogueDetailsResult;
                    }
                } else {
                    magicWandCatalogueDetailsResult.put("Result", "Magic wand catalogue details are valid but wizard's age is not applicable.");
                    magicWandCatalogueDetailsResult.put("HttpStatus", HttpStatus.OK.toString());
                    return magicWandCatalogueDetailsResult;
                }
            } else {
                magicWandCatalogueDetailsResult.put("Result", "Magic wand catalogue name is not match.");
                magicWandCatalogueDetailsResult.put("HttpStatus", HttpStatus.NOT_FOUND.toString());
                return magicWandCatalogueDetailsResult;
            }
        } else {
            magicWandCatalogueDetailsResult.put("Result", "Magic wand catalogue Id is not match.");
            magicWandCatalogueDetailsResult.put("HttpStatus", HttpStatus.NOT_FOUND.toString());
            return magicWandCatalogueDetailsResult;
        }
    }
}
