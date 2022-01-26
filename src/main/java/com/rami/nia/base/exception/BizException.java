package com.rami.nia.base.exception;

public class BizException extends RuntimeException {
    
    private static final long serialVersionUID = -4442144619722816016L;
    
    private int code;
    
    public BizException() {
    }
    
    public BizException(String msg) {
        super(msg);
    }
    
    public BizException(String msg, int code) {
        super(msg);
        this.code = code;
    }
    
    public BizException(String msg, Exception e) {
        super(msg, e);
    }
    
}
