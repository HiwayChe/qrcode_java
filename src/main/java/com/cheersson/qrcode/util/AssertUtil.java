package com.cheersson.qrcode.util;

import com.cheersson.qrcode.exception.BizException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;

public class AssertUtil {

    public static void notNull(Object value) {
        notNull(value, null);
    }

    public static void notNull(Object value, String msg, Object... args) {
        if (value == null) {
            throw new BizException(StringUtils.isBlank(msg) ? "参数不能为空" : MessageUtil.getMessage(msg, args));
        }
    }

    public static void hasText(String value) {
        hasText(value, null);
    }

    public static void hasText(String value, String msg, Object... args) {
        if (StringUtils.isBlank(value)) {
            throw new BizException(StringUtils.isBlank(msg) ? "参数不能为空" : MessageUtil.getMessage(msg, args));
        }
    }

    public static void notEmpty(Collection collection) {
        notEmpty(collection, null);
    }

    public static void notEmpty(Collection collection, String msg, Object... args) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BizException(StringUtils.isBlank(msg) ? "参数不能为空" : MessageUtil.getMessage(msg, args));
        }
    }

    public static void isNumber(String number) {
        isNumber(number, null);
    }

    public static void isNumber(String number, String msg, Object... args) {
        if (!NumberUtils.isCreatable(number)) {
            throw new BizException(StringUtils.isBlank(msg) ? "参数不是数字" : MessageUtil.getMessage(msg, args));
        }
    }

    public static void isTrue(boolean value, String msg, Object... args) {
        if (!value) {
            throw new BizException(StringUtils.isBlank(msg) ? "条件不成立" : MessageUtil.getMessage(msg, args));
        }
    }

    public static void isTrue(boolean value) {
        isTrue(value, null);
    }

    public static void isEqual(Object object, Object object2) {
        ObjectUtils.nullSafeEquals(object, object2);
    }

}
