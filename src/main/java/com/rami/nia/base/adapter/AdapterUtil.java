package com.rami.nia.base.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

class AdapterUtil {

    static String getParameterBuilder(UrlInterface url, Object obj) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url.getUrl());
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(obj, Map.class);
        map.forEach((k, v) -> uriBuilder.queryParam(String.valueOf(k), v));
        return uriBuilder.toUriString();
    }
}
