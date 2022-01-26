package com.rami.nia.base.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("apReq")
@Data
public class ApReq extends BasePage {

    String ap_mac;
    String cli_mac;
    String start_date;
    String end_date;
    String interval;

    //입력 항목
    String ap_serial;
    String ap_vendor;
    String ap_model;
    String ap_name;
    String ap_reg_time;
    String ap_sido;
    String ap_sigungu;
    String ap_dong;
    String ap_location;
    String ap_lat;
    String ap_lng;
    String ap_description;
    String ap_memo;
}
