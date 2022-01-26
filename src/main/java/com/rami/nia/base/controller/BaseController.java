package com.rami.nia.base.controller;

import com.rami.nia.base.constants.BaseConstants;
import com.rami.nia.base.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class BaseController {
    
    @Autowired
    BaseService baseService;
    
    @GetMapping("/test")
    @ResponseBody
    public Map codes(@RequestParam(name = "ap_mac", required = true) String apMac) throws Exception {
        
        log.debug("test");
        log.debug("apMac :" + apMac);
        
        Map res = new HashMap();
        res.put(BaseConstants.RES_CODE, BaseConstants.RES_CODE_OK);
        res.put(BaseConstants.RES_MSG, BaseConstants.RES_MSG_OK);
        try {
            Map data = baseService.getCodeList(apMac);
            res.put("data", data);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            res.put(BaseConstants.RES_CODE, BaseConstants.RES_CODE_SYS_ERR);
            res.put(BaseConstants.RES_MSG, BaseConstants.RES_MSG_SYS_ERR);
        }
        
        return res;
    }
}
