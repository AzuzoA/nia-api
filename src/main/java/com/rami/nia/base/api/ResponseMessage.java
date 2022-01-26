package com.rami.nia.base.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseMessage implements Serializable {

    @JsonInclude
    private int responseCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String responseMsg;

    private Object data;

    public ResponseMessage() {
        this.responseCode = RETURN_CODE.NG.getValue();
    }

    public ResponseMessage(Object o) {
        this.responseCode = RETURN_CODE.OK.getValue();
        this.data = o;
    }

}
