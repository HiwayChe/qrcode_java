package com.cheersson.qrcode.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadLocalUtil {
    public static final String Key_Date_Format = "com.cheersson.qrcode.util.dateFormat";

    private static final ThreadLocal<Map<String, Object>> contextMap = new ThreadLocal<Map<String, Object>>(){
        @Override
        protected Map<String, Object> initialValue() {
            return new ConcurrentHashMap<>();
        }
    };

    public static void setData(String key, Object value){
        contextMap.get().put(key, value);
    }

    public static Object getData(String key){
        return contextMap.get().get(key);
    }
}
