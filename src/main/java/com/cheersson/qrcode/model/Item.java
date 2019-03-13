package com.cheersson.qrcode.model;

public class Item extends BaseModel {
    private Long id;

    private Long categoryid;

    private Long code;

    private Long name;

    private Long customercode;

    private Long customername;

    private Integer version;

    private String remark;

    private Boolean available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public Long getCustomercode() {
        return customercode;
    }

    public void setCustomercode(Long customercode) {
        this.customercode = customercode;
    }

    public Long getCustomername() {
        return customername;
    }

    public void setCustomername(Long customername) {
        this.customername = customername;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}