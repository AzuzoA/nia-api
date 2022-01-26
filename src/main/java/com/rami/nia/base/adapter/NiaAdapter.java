package com.rami.nia.base.adapter;

import com.rami.nia.base.constants.BaseConstants;
import com.rami.nia.base.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class NiaAdapter extends CommonAdapter {
    
    private final String RESULT_SUCCESS = "0";
    private final String RESULT_FAIL = "1";
    private final String RESULT_ERROR = "-1";
    
    public Map toPost(NiaApi api, Object obj) {
        return this.request(HttpMethod.POST, api, obj);
    }
    
    private Map request(HttpMethod method, NiaApi api, Object obj) {
        Map niaMap = super.request(method, api, obj, Map.class);
        
        log.debug("niaMap {}", niaMap);
        
        Map headerMap = (Map) niaMap.get("header");
        if (!Optional.ofNullable(headerMap).isPresent()) {
            throw new BizException("데이터베이스 연결이 원할하지 않습니다.\n잠시 후 다시 시도해주십시오.", BaseConstants.RES_CODE_BIZ_ERR);
        }
        String resultCode = String.valueOf(headerMap.get("success"));
        String headerMessage = String.valueOf(headerMap.get("message"));
        
        if (RESULT_ERROR.equals(resultCode)) {
            throw new BizException("[데이터베이스]\n\n" + headerMessage, BaseConstants.RES_CODE_BIZ_ERR);
        } else if (RESULT_FAIL.equals(String.valueOf(headerMap.get("success")))) {
            throw new BizException("[데이터베이스]\n\n" + headerMessage, BaseConstants.RES_CODE_BIZ_ERR);
        }
        
        niaMap.put(BaseConstants.RES_CODE, BaseConstants.RES_CODE_OK);
        niaMap.put(BaseConstants.RES_MSG, headerMessage);
        
        return niaMap;
    }
    
}
