package com.cheersson.qrcode.vo;

import java.io.Serializable;

public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 0表示成功
     */
    private int code = 0;
    private String msg;
    private T data;

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setData(data);
        return result;
    }

    public static <T> ApiResult<T> fail(int code, String msg) {
        ApiResult<T> result = new ApiResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> ApiResult<T> fail(String msg) {
        return fail(-1, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
