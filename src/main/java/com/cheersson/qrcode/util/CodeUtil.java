package com.cheersson.qrcode.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CodeUtil {

    public static final String RS = "<<RS>>";
    public static final String GS = "<<GS>>";
    public static final String EOT = "<<EOT>>";

    public List<String> split(String code){
        List<String> resultList = new ArrayList<>();
        String[] split = StringUtils.split(code, RS);
    }
}
