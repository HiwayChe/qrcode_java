package com.cheersson.qrcode.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    private static Map<String, Class> classMap = new ConcurrentHashMap<>(1 << 8);

    public static Class loadClass(String className){
        Class aClass = classMap.get(className);
        if(aClass != null){
            return aClass;
        }
        try {
            aClass = Class.forName(className);
            classMap.put(className, aClass);
            return aClass;
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
