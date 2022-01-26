package com.rami.nia.base.service;

import com.rami.nia.base.model.ApReq;
import com.rami.nia.base.repository.ApMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ApService extends BaseService {

    @Autowired
    ApMapper apMapper;

    /**
     * ap 정보 조회
     *
     * @param apReq
     * @return
     * @throws Exception
     */
    public Map getApInfoList(ApReq apReq) throws Exception {
        List<Map> list = apMapper.getApInfoList(apReq);
        int totalPageCount = this.calTotalPage(list.size(), apReq.getLimit());
        Map data = new HashMap<>();

        // 1. 현 페이지
        data.put("page", apReq.getPage());
        // 2.totalCnt list count
        data.put("totalCount", list.size());
        // 3.hashMore 다음페이지가 있는가 체크
        if (totalPageCount > apReq.getPage()) {
            data.put("hashMore", true);
        } else {
            data.put("hashMore", false);
        }
        data.put("list", list);
        return data;
    }

    /**
     * ap 장애 정보 조회
     *
     * @param apReq
     * @return
     * @throws Exception
     */
    public Map getApAlarmInfoList(ApReq apReq) throws Exception {
        List<Map> list = apMapper.getApAlarmInfoList(apReq);
        int totalPageCount = this.calTotalPage(list.size(), apReq.getLimit());
        Map data = new HashMap<>();

        // 1. 현 페이지
        data.put("page", apReq.getPage());
        // 2.totalCnt list count
        data.put("totalCount", list.size());
        // 3.hashMore 다음페이지가 있는가 체크
        if (totalPageCount > apReq.getPage()) {
            data.put("hashMore", true);
        } else {
            data.put("hashMore", false);
        }
        data.put("list", list);
        return data;
    }

    /**
     * ap 트래픽 및 접속자 정보 조회
     *
     * @param apReq
     * @return
     * @throws Exception
     */
    public Map getApTrafficClient(ApReq apReq) throws Exception {
        List<Map> list = apMapper.getApTrafficClient(apReq);
        int totalPageCount = this.calTotalPage(list.size(), apReq.getLimit());
        Map data = new HashMap<>();

        // 1. 현 페이지
        data.put("page", apReq.getPage());
        // 2.totalCnt list count
        data.put("totalCount", list.size());
        // 3.hashMore 다음페이지가 있는가 체크
        if (totalPageCount > apReq.getPage()) {
            data.put("hashMore", true);
        } else {
            data.put("hashMore", false);
        }
        data.put("list", list);
        return data;
    }

    /**
     * ap reboot
     *
     * @param apReq
     * @return
     * @throws Exception
     */
    public Map setApReset(ApReq apReq) throws Exception {
        Map data = new HashMap<>();
        if (apReq.getAp_mac() == null || "".equals(apReq.getAp_mac())) {  // 잘못된 요청
            data.put("totalCount", -1);
            data.put("page", 1);
            data.put("hashMore", false);
            data.put("result", "NG");
            data.put("msg", "The ap_mac value is empty.");
            data.put("error", "The ap_mac value is empty.");
        } else {
            List<Map> list = apMapper.selectApInfo(apReq);
            if (list.size() > 0) {    //등록된 ap_mac 일시 저장
                apMapper.setApReset(apReq);
                data.put("totalCount", 1);
                data.put("page", 1);
                data.put("hashMore", false);
                data.put("result", "OK");
            } else {    //등록되지 않은 ap_mac 일 시
                data.put("totalCount", -1);
                data.put("page", 1);
                data.put("hashMore", false);
                data.put("result", "NG");
                data.put("msg", "There is a no ap");
                data.put("error", "There is a no ap");
            }
        }
        return data;
    }

    /**
     * ap 클라이언트 정보 조회
     *
     * @param apReq
     * @return
     * @throws Exception
     */
    public Map getClientHistory(ApReq apReq) throws Exception {
        List<Map> list = apMapper.getClientHistory(apReq);
        int totalPageCount = this.calTotalPage(list.size(), apReq.getLimit());
        Map data = new HashMap<>();

        // 1. 현 페이지
        data.put("page", apReq.getPage());
        // 2.totalCnt list count
        data.put("totalCount", list.size());
        // 3.hashMore 다음페이지가 있는가 체크
        if (totalPageCount > apReq.getPage()) {
            data.put("hashMore", true);
        } else {
            data.put("hashMore", false);
        }
        data.put("list", list);
        return data;
    }
    
    /**
     * 컨트롤러 상태 정보 조회
     *
     * @param apReq
     * @return
     * @throws Exception
     */
    public Map getCtrlStatus(ApReq apReq) throws Exception {
        List<Map> list = apMapper.getCtrlStatus(apReq);
        int totalPageCount = this.calTotalPage(list.size(), apReq.getLimit());
        Map data = new HashMap<>();

        // 1. 현 페이지
        data.put("page", apReq.getPage());
        // 2.totalCnt list count
        data.put("totalCount", list.size());
        // 3.hashMore 다음페이지가 있는가 체크
        if (totalPageCount > apReq.getPage()) {
            data.put("hashMore", true);
        } else {
            data.put("hashMore", false);
        }
        data.put("list", list);
        return data;
    }

    /**
     * ap 정보 입력
     *
     * @param apReq
     * @return
     * @throws Exception
     */
    public Map setApInfo(ApReq apReq) throws Exception {
        Map data = new HashMap<>();
        if (apReq.getAp_mac() == null || "".equals(apReq.getAp_mac())) {  // 잘못된 요청
            data.put("totalCount", -1);
            data.put("page", 1);
            data.put("hashMore", false);
            data.put("error", "The ap_mac value is empty.");
        } else {
            List<Map> list = apMapper.selectApInfo(apReq);
            if (list.size() > 0) {    // update
                apMapper.updateApInfo(apReq);
            } else {    // insert
                apMapper.insertApInfo(apReq);
            }
            data.put("totalCount", 1);
            data.put("page", 1);
            data.put("hashMore", false);
            List<Map> listA = apMapper.selectApInfo(apReq);
            data.put("list", listA);
        }
        return data;
    }
}
