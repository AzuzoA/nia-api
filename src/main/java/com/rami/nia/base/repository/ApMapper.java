package com.rami.nia.base.repository;

import com.rami.nia.base.model.ApReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ApMapper {

    // ap 정보 조회
    List<Map> selectApInfo(ApReq req) throws Exception;

    // ap 정보 insert
    void insertApInfo(ApReq req) throws Exception;

    // ap 정보 update
    void updateApInfo(ApReq req) throws Exception;

    // ap 정보 조회 (통합)
    List<Map> getApInfoList(ApReq req) throws Exception;

    // ap 장애 정보 조회
    List<Map> getApAlarmInfoList(ApReq req) throws Exception;

    // ap 트래픽 및 접속자 정보
    List<Map> getApTrafficClient(ApReq req) throws Exception;

    // ap reboot
    void setApReset(ApReq req) throws Exception;

    // ap 트래픽 및 접속자 정보
    List<Map> getClientHistory(ApReq req) throws Exception;

    // ap 트래픽 및 접속자 정보
    List<Map> getCtrlStatus(ApReq req) throws Exception;
}
