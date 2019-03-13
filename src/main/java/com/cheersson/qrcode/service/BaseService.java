package com.cheersson.qrcode.service;

import com.cheersson.qrcode.vo.PageVO;

import java.util.List;

public interface BaseService<T, Q> {
    T get(Long id);

    boolean save(T t);

    boolean update(T t);

    boolean delete(Long id);

    List<T> list(Q query);

    PageVO<T> pageList(Q query);
}
