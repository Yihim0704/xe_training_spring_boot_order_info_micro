package com.example.order_info_micro.integration;

import com.example.order_info_micro.common.ApiUrl;
import com.example.order_info_micro.model.WizardInfo;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RTWizardInfoServiceImpl implements RTWizardInfoService {

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public WizardInfo getWizardInfoById(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<WizardInfo> response = restTemplate.exchange(ApiUrl.WIZARD_INFO_GET_BY_ID_URL + id, HttpMethod.GET, entity, WizardInfo.class);
        WizardInfo wizardInfo = response.getBody();
        return wizardInfo;
    }

};