package com.cheersson.qrcode.service.impl;

import com.cheersson.qrcode.dao.BaseDao;
import com.cheersson.qrcode.service.BaseService;
import com.cheersson.qrcode.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class BaseServiceImpl<T, Q> implements BaseService<T, Q> {

    @Autowired
    protected BaseDao baseDao;

    protected abstract Class<T> getModelClass();

    @Override
    public boolean saveSelective(T t) {
        return this.baseDao.insertSelective(t);
    }

    @Override
    public boolean updateSelective(T t) {
        return this.baseDao.updateByPrimaryKeySelective(t);
    }

    @Override
    public T get(Long id) {
        return this.baseDao.selectByPrimaryKey(this.getModelClass(), id);
    }

    @Override
    public boolean save(T t) {
        return this.baseDao.insert(t);
    }

    @Override
    public boolean update(T t) {
        return this.baseDao.updateByPrimaryKey(t);
    }

    @Override
    public boolean delete(Long id) {
        return this.baseDao.deleteByPrimaryKey(this.getModelClass(), id);
    }

    @Override
    public List<T> list(Q query) {
        return this.baseDao.selectByExample(query);
    }

    @Override
    public PageVO<T> pageList(Q query, int pageNum, int pageSize) {
        return this.baseDao.selectByExample(query, pageNum, pageSize);
    }

    @Override
    public int updateByExample(Q example) {
        return this.baseDao.updateByExample(example);
    }

    @Override
    public int deleteByExample(Q example) {
        return this.baseDao.deleteByExample(example);
    }
}
