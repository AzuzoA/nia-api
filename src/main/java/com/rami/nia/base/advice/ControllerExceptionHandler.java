package com.rami.nia.base.advice;

import com.rami.nia.base.constants.BaseConstants;
import com.rami.nia.base.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {
    
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    protected Map  handleException(Exception e) {
        log.error("handle Exception", e);
        Map res = new HashMap();
        res.put(BaseConstants.RES_ERR_FIELD, BaseConstants.RES_ERR_CODE);
        res.put(BaseConstants.RES_ERR_MSG, BaseConstants.RES_MSG_SYS_ERR);
        e.printStackTrace();
        return res;
    }

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    protected Map  handleBizException(BizException e) {
        log.error("handle BizException", e);
        Map res = new HashMap();
        res.put(BaseConstants.RES_ERR_FIELD, BaseConstants.RES_ERR_CODE);
        res.put(BaseConstants.RES_ERR_MSG, BaseConstants.RES_MSG_SYS_ERR);
        return res;
    }


}
