package com.cheersson.qrcode.service;

import com.cheersson.qrcode.vo.PageVO;

import javax.management.Query;
import java.util.List;

public interface BaseService<T, Q> {
    T get(Long id);

    boolean save(T t);

    boolean saveSelective(T t);

    boolean update(T t);

    boolean updateSelective(T t);

    boolean delete(Long id);

    List<T> list(Q query);

    /**
     * 只取一条，多于一条会报错
     * @param query
     * @return
     */
    T one(Q query);

    List<T> list(Q query, int top);

    PageVO<T> pageList(Q query, int pageNum, int pageSize);

    /**
     * 根据条件更新，慎用
     * @param example
     * @return
     */
    int updateByExample(Q example);

    /**
     * 根据条件删除，慎用
     * @param example
     * @return
     */
    int deleteByExample(Q example);
}
