package com.example.order_info_micro.integration;

import com.example.order_info_micro.common.ApiUrl;
import com.example.order_info_micro.model.MagicWandCatalogue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RTMagicWandCatalogueServiceImpl implements RTMagicWandCatalogueService {

    private static final Logger logger = LoggerFactory.getLogger(RTMagicWandCatalogueServiceImpl.class);

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public MagicWandCatalogue getMagicWandCatalogueById(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<MagicWandCatalogue> response = restTemplate.exchange(ApiUrl.MAGIC_WAND_CATALOGUE_FIND_ALL_URL + id, HttpMethod.GET, entity, MagicWandCatalogue.class);
        MagicWandCatalogue magicWandCatalogue = response.getBody();
        return magicWandCatalogue;
    }
}
