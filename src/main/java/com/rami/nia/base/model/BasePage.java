package com.rami.nia.base.model;

import lombok.Data;

@Data
public class BasePage {

    Integer page = 1;
    Integer limit = 1000;

    // TODO:ip를 넣든가 명확해지기 전에 임시
    String regi_id = "hil";
    String updt_id = "hil";
}
