package com.cheersson.qrcode.service.impl;

import com.cheersson.qrcode.service.BaseService;
import com.cheersson.qrcode.vo.PageVO;

import java.util.List;

public class BaseServiceImpl<T, Q> implements BaseService<T, Q> {

    @Override
    public T get(Long id) {
        return null;
    }

    @Override
    public boolean save(T t) {
        return false;
    }

    @Override
    public boolean update(T t) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<T> list(Q query) {
        return null;
    }

    @Override
    public PageVO<T> pageList(Q query) {
        return null;
    }
}
