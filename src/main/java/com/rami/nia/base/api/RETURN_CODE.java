package com.rami.nia.base.api;

public enum RETURN_CODE {
    OK(200), NG(500);

    int value;
    RETURN_CODE(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
