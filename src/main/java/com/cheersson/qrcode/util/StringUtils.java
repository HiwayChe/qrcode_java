package com.cheersson.qrcode.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StringUtils {

    public static Map<String, Object> newMap(Object... args) {
        if (args.length == 0) {
            return Collections.emptyMap();
        }
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null || i + 1 >= args.length) {
                continue;
            }
            map.put((String) args[i], args[++i]);
        }
        return map;
    }
}
