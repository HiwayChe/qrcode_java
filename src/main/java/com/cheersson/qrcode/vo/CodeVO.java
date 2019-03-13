package com.cheersson.qrcode.vo;

import java.io.Serializable;

public class CodeVO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long categoryId;
    private String code;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
