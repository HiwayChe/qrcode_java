package com.cheersson.qrcode.util;

import com.cheersson.qrcode.exception.BizException;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

public class MessageUtil {
    public static String getMessage(String code, Object... args){
        if(StringUtils.isBlank(code) || args.length == 0){
            return code;
        }
        StringBuilder sb = new StringBuilder(code.length() << 1);
        boolean left = false;
        int count = -1;
        for (int i = 0; i < code.length(); i++) {
            if(code.charAt(i) == '{'){
                left = true;
            }else if(code.charAt(i) == '}'){
                if(left){
                    if(++count < args.length){
                        sb.append(args[count]);
                        left = false;
                    }else{
                        sb.append("{}");
                        left = false;
                    }
                }else{
                    throw new BizException("{和}不匹配");
                }
            }else{
                sb.append(code.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String getMessgeWithJavaFormat(String code, Object... args){
        if(args.length == 0){
            return code;
        }
        return MessageFormat.format(code, args);
    }
}
