package com.cheersson.qrcode.util;

import java.text.MessageFormat;

public class MessageUtil {
    public static String getMessage(String code, Object... args){
        if(args.length == 0){
            return code;
        }
        return MessageFormat.format(code, args);
    }
}
