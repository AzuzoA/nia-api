package com.rami.nia.base.utils;

public class StringUtils {

    public static final String EMPTY = "";

    public static String camelToFixedString(String str, String fixed) {
        str = trimToEmpty(str);
        if (isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i != 0) {
                    sb.append(fixed);
                }
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String trimToEmpty(String input) {
        if (input == null) {
            return EMPTY;
        }
        return input.trim();
    }

    public static boolean isEmpty(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        return false;
    }

    public static Object nullToEmptyString(Object obj) {
        if (obj == null) {
            return EMPTY;
        } else {
            return obj;
        }
    }
}
