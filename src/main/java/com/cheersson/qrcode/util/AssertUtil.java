package com.cheersson.qrcode.util;

import com.cheersson.qrcode.exception.BizException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Collection;

public class AssertUtil {
    public static void notNull(Object value, String msg) {
        if (value == null) {
            throw new BizException(StringUtils.isBlank(msg) ? "参数不能为空" : msg);
        }
    }

    public static void hasText(String value, String msg) {
        if (StringUtils.isBlank(value)) {
            throw new BizException(StringUtils.isBlank(msg) ? "参数不能为空" : msg);
        }
    }

    public static void notEmpty(Collection collection, String msg) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BizException(StringUtils.isBlank(msg) ? "参数不能为空" : msg);
        }
    }

    public static void isNumber(String number, String msg) {
        if (!NumberUtils.isCreatable(number)) {
            throw new BizException(StringUtils.isBlank(msg) ? "参数不是数字" : msg);
        }
    }
}
