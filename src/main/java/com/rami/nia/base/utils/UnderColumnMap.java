package com.rami.nia.base.utils;

import org.apache.ibatis.type.Alias;
import org.springframework.jdbc.support.JdbcUtils;

import java.util.HashMap;

@Alias("underColumnMap")
public class UnderColumnMap extends HashMap<String, Object> {

    @Override
    public Object put(String key, Object value) {
        return super.put(StringUtils.camelToFixedString(key, "-"), StringUtils.nullToEmptyString(value));
    }

}
