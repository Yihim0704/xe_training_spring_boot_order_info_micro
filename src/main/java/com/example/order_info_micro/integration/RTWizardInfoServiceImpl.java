package com.example.order_info_micro.integration;

import com.example.order_info_micro.common.ApiUrl;
import com.example.order_info_micro.entity.WizardInfo;
import com.example.order_info_micro.exception.client.ClientErrorException;
import com.example.order_info_micro.exception.client.RestClientErrorException;
import com.example.order_info_micro.exception.client.WizardInfo.WizardInfoNotExistException;
import com.example.order_info_micro.exception.server.ServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RTWizardInfoServiceImpl implements RTWizardInfoService {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RTMagicWandCatalogueServiceImpl.class);

    @Override
    public List<WizardInfo> getAllWizardInfo() throws HttpRequestMethodNotSupportedException {
        logger.info("Client RTWizardInfoService.getAllWizardInfo");
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity<>(null, headers);
            ResponseEntity<List<WizardInfo>> response = restTemplate.exchange(ApiUrl.WIZARD_INFO_GET_ALL_URL, HttpMethod.GET, entity, new ParameterizedTypeReference<List<WizardInfo>>() {
            });
            List<WizardInfo> allWizardInfo = response.getBody();
            return allWizardInfo;
        } catch (HttpClientErrorException e) {
            throw new ClientErrorException("Internal client error");
        } catch (HttpServerErrorException e) {
            throw new ServerErrorException("Internal server error");
        } catch (RestClientException e) {
            throw new RestClientErrorException(e.getMessage());
        }
    }

    @Override
    public WizardInfo getWizardInfoById(String id) throws HttpRequestMethodNotSupportedException {
        logger.info("Client RTWizardInfoService.getWizardInfoById");
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<WizardInfo> response = restTemplate.exchange(ApiUrl.WIZARD_INFO_GET_BY_ID_URL + id, HttpMethod.GET, entity, WizardInfo.class);
            WizardInfo wizardInfo = response.getBody();
            if (wizardInfo.getId() == null) {
                throw new WizardInfoNotExistException("Wizard info Id does not exist.", HttpStatus.NOT_FOUND.value());
            }
            return wizardInfo;
        } catch (HttpClientErrorException e) {
            throw new ClientErrorException("Internal client error");
        } catch (HttpServerErrorException e) {
            throw new ServerErrorException("Internal server error");
        } catch (RestClientException e) {
            throw new RestClientErrorException(e.getMessage());
        }
    }
};