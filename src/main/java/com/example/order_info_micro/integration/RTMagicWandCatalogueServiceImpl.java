package com.example.order_info_micro.integration;

import com.example.order_info_micro.common.ApiUrl;
import com.example.order_info_micro.exception.client.MagicWandCatalogueNotExistException;
import com.example.order_info_micro.exception.client.NoMagicWandCatalogueFoundException;
import com.example.order_info_micro.model.MagicWandCatalogue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RTMagicWandCatalogueServiceImpl implements RTMagicWandCatalogueService {

    private static final Logger logger = LoggerFactory.getLogger(RTMagicWandCatalogueServiceImpl.class);

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public MagicWandCatalogue[] getAllMagicWandCatalogue() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<MagicWandCatalogue[]> response = restTemplate.exchange(ApiUrl.MAGIC_WAND_CATALOGUE_GET_ALL_URL, HttpMethod.GET, entity, MagicWandCatalogue[].class);
            MagicWandCatalogue[] allMagicWandCatalogue = response.getBody();
            return allMagicWandCatalogue;
        } catch (RestClientException e) {
            throw new NoMagicWandCatalogueFoundException("There is no magic wand catalogue in the database.", HttpStatus.NO_CONTENT.toString());
        }
    }

    @Override
    public MagicWandCatalogue getMagicWandCatalogueById(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<MagicWandCatalogue> response = restTemplate.exchange(ApiUrl.MAGIC_WAND_CATALOGUE_GET_BY_ID_URL + id, HttpMethod.GET, entity, MagicWandCatalogue.class);
        MagicWandCatalogue magicWandCatalogue = response.getBody();
        if (magicWandCatalogue.getId() == null) {
            throw new MagicWandCatalogueNotExistException("Magic wand catalogue Id does not exist.", HttpStatus.NOT_FOUND.toString());
        }
        return magicWandCatalogue;
    }

    @Override
    public void updateMagicWandCatalogueById(String id, MagicWandCatalogue magicWandCatalogue) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<MagicWandCatalogue> requestUpdate = new HttpEntity<MagicWandCatalogue>(magicWandCatalogue, headers);
        restTemplate.exchange(ApiUrl.MAGIC_WAND_CATALOGUE_UPDATE_BY_ID_URL + id, HttpMethod.PUT, requestUpdate, void.class);
    }
}
