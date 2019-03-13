package com.cheersson.qrcode.vo;

import java.io.Serializable;
import java.util.List;

public class PageVO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public PageVO(){}

    public PageVO(long total, List<T> data){
        this.total = total;
        this.data = data;
    }

    private long total;
    private List<T> data;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
