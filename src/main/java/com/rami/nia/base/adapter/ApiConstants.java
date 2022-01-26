package com.rami.nia.base.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiConstants {
    
    // hucom > rami > nia
    static String Nia;

    @Value("${api.nia}")
    public void setNia(String url) {
        Nia = url;
    }
    
    
}
