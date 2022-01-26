package com.rami.nia.base.adapter;

import com.rami.nia.base.constants.BaseConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API 호출
 */
@Slf4j
@Component
public class CommonAdapter {
    
    @Autowired
    private RestTemplate restTemplate;
    
    private final static MediaType mediaType = MediaType.APPLICATION_JSON;
    
    public Map<String, Object> toPost(String url, Object obj) {
        return setMethod(HttpMethod.POST, url, obj);
    }
    
    public Map<String, Object> toGet(String url, Map<String, Object> paramMap) {
        List<NameValuePair> params = Lists.newArrayList();
        
        if (paramMap != null) {
            for (Map.Entry<String, Object> paramEntry : paramMap.entrySet()) {
                Object value = paramEntry.getValue();
                if (value != null) {
                    params.add(new BasicNameValuePair(paramEntry.getKey(), value.toString()));
                }
            }
        }
        
        String queryString = URLEncodedUtils.format(params, "UTF-8");
        if (paramMap != null && queryString.length() > 0)
            url += "?" + queryString;
        return setMethod(HttpMethod.GET, url, null);
    }
    
    public Map<String, Object> toPut(String url, Object obj) {
        return setMethod(HttpMethod.PUT, url, obj);
    }
    
    public Map<String, Object> toDelete(String url, Object obj) {
        return setMethod(HttpMethod.DELETE, url, obj);
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Map<String, Object> setMethod(HttpMethod method, String url, Object obj) {
        
        Map<String, Object> res = new HashMap<>();
        
        try {
            URI uri = URI.create(url);
            log.debug("uri : " + uri);
            log.debug("obj : " + obj);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);
            HttpEntity entity = new HttpEntity(obj, headers);
            ResponseEntity<Map> responseEntity = restTemplate.exchange(uri, method, entity, Map.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            Map<String, Object> headerMap = new HashMap<>();
            res.put(BaseConstants.RES_CODE, BaseConstants.RES_CODE_SYS_ERR);
            res.put(BaseConstants.RES_MSG , e.getMessage());
            res.put("header", headerMap);
            return res;
        }
    }
    
    
    protected <T> T request(HttpMethod method, UrlInterface urlInterface, Object o, Class<T> cls) {
        ResponseEntity<T> responseEntity;
        RestTemplate restTemplate;
        
        restTemplate = new RestTemplate();
        
        String url;
        if (HttpMethod.GET.equals(method)) {
            url = AdapterUtil.getParameterBuilder(urlInterface, o);
        } else {
            url = urlInterface.getUrl();
        }
    
        log.debug(String.format("request [%s] [{%s}]", url, o.toString()));
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(o, headers);
        responseEntity = restTemplate.exchange(url, method, entity, cls);
    
        log.debug(String.format("response [%s] [{%s}]", url, responseEntity.getBody()));
    
        return responseEntity.getBody();
    }
    
    
}
