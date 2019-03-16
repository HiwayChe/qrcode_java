package com.cheersson.qrcode.util;

import com.cheersson.qrcode.exception.BizException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeUtil {

    public static final char leftSeparator = '«';
    public static final char rightSeparator = '»';

    public static List<String> split(String code) {
        List<String> resultList = new ArrayList<>();
        boolean left = false;
        int start = 0;
        for (int i = 0; i < code.length(); i++) {
            if(code.charAt(i) == leftSeparator){
                resultList.add(code.substring(start, i));
                start = i;
                left = true;
            }else if(code.charAt(i) == rightSeparator){
                if(!left){
                    throw new BizException(MessageUtil.getMessage("第{}位置的{}没有与之匹配的{}", i+1, rightSeparator, leftSeparator));
                }
                resultList.add(code.substring(start, i + 1));
                start = i + 1;
                left = false;
            }else{

            }
        }
        if(left){

        }
        if(start < code.length()){
            resultList.add(code.substring(start));
        }
        resultList.removeIf(""::equalsIgnoreCase);
        return resultList;
    }

    public static void validate(String code, String rule, Long categoryId) {
        List<String> codeSplit = split(code);
        List<String> ruleSplit = split(rule);
        if(codeSplit.size() != ruleSplit.size()){
            throw new BizException("扫描结果长度不符");
        }
        Map<String, String> placeholderMap = new HashMap<>();
        for (int i = 0; i < codeSplit.size(); i++) {
            String c = codeSplit.get(i);
            String r = ruleSplit.get(i);
            if(c.equalsIgnoreCase(r)){
                continue;
            }
            if(c.startsWith(leftSeparator + "") || r.startsWith(leftSeparator + "")){
                throw new BizException("分隔符不匹配");
            }else{

            }
        }
    }
}
