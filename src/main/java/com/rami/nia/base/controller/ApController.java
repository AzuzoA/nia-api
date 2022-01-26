package com.rami.nia.base.controller;


import com.rami.nia.base.constants.BaseConstants;
import com.rami.nia.base.model.ApReq;
import com.rami.nia.base.service.ApService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class ApController {

    @Autowired
    ApService apService;

    @GetMapping("/getApInfo")
    @ResponseBody
    public Map getApInfo(@ModelAttribute ApReq req) throws Exception {

        Map res = new HashMap();

        // 1.page 디폴트 값은 1
        if (req.getPage() == null || req.getPage() < 1) {
            req.setPage(1);
        }
        // 2.limit 디폴트 값은 1000
        if (req.getLimit() == null || req.getLimit() < 1) {
            req.setLimit(1000);
        }

        Map data = apService.getApInfoList(req);
        res.putAll(data);

        return res;
    }

    @GetMapping("/getApAlarmInfo")
    @ResponseBody
    public Map getApAlarmInfo(@ModelAttribute ApReq req) throws Exception {

        Map res = new HashMap();

        log.debug("req in Start_date = " + req.getStart_date());
        log.debug("req in End_date = " + req.getEnd_date());

        // 1.page 디폴트 값은 1
        if (req.getPage() == null || req.getPage() < 1) {
            req.setPage(1);
        }
        // 2.limit 디폴트 값은 1000
        if (req.getLimit() == null || req.getLimit() < 1) {
            req.setLimit(1000);
        }

        Map data = apService.getApAlarmInfoList(req);
        res.putAll(data);

        return res;
    }

    @GetMapping("/getApTrafficClient")
    @ResponseBody
    public Map getApTrafficClient(@ModelAttribute ApReq req) throws Exception {

        Map res = new HashMap();

        // 1.page 디폴트 값은 1
        if (req.getPage() == null || req.getPage() < 1) {
            req.setPage(1);
        }
        // 2.limit 디폴트 값은 1000
        if (req.getLimit() == null || req.getLimit() < 1) {
            req.setLimit(1000);
        }
        // 3.interval 디폴트 값은 hour
        if (req.getInterval() == null || "".equals(req.getInterval())) {
            req.setInterval("hour");
        }

        Map data = apService.getApTrafficClient(req);
        res.putAll(data);

        return res;
    }

    @PostMapping("/setApReset")
    @ResponseBody
    public Map setApReset(@ModelAttribute ApReq req) throws Exception {

        Map res = new HashMap();

        res = apService.setApReset(req);
        return res;
    }

    @GetMapping("/getClientHistory")
    @ResponseBody
    public Map getClientHistory(@ModelAttribute ApReq req) throws Exception {

        Map res = new HashMap();

        // 1.page 디폴트 값은 1
        if (req.getPage() == null || req.getPage() < 1) {
            req.setPage(1);
        }
        // 2.limit 디폴트 값은 1000
        if (req.getLimit() == null || req.getLimit() < 1) {
            req.setLimit(1000);
        }

        Map data = apService.getClientHistory(req);
        res.putAll(data);

        return res;
    }

    @GetMapping("/getCtrlStatus")
    @ResponseBody
    public Map getCtrlStatus(@ModelAttribute ApReq req) throws Exception {

        Map res = new HashMap();

        // 1.page 디폴트 값은 1
        if (req.getPage() == null || req.getPage() < 1) {
            req.setPage(1);
        }
        // 2.limit 디폴트 값은 1000
        if (req.getLimit() == null || req.getLimit() < 1) {
            req.setLimit(1000);
        }

        Map data = apService.getCtrlStatus(req);
        res.putAll(data);

        return res;
    }

    @PostMapping("/setApInfo")
    @ResponseBody
    public Map setApInfo(@ModelAttribute ApReq req) throws Exception {

        Map res = new HashMap();

        res = apService.setApInfo(req);
        return res;
    }
}