package com.cheersson.qrcode.dao;

import com.cheersson.qrcode.util.AssertUtil;
import com.cheersson.qrcode.util.ReflectionUtil;
import com.cheersson.qrcode.vo.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BaseDao extends SqlSessionDaoSupport {
    private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);

    private final String namespacePrefix = "com.cheersson.qrcode.mapper.";

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    private Class getClassFromExampleName(String example) {
        if (StringUtils.endsWith(example, "Example")) {
            return ReflectionUtil.loadClass(example.substring(0, example.length() - 7));
        }
        AssertUtil.notNull(null, "class not found:{0}", example);
        return null;
    }

    public <E> long countByExample(E example) {
        AssertUtil.notNull(example, "example could not be null");
        Class cls = this.getClassFromExampleName(example.getClass().getName());
        return this.getSqlSession().selectOne(namespacePrefix + cls.getSimpleName() + "Mapper.countByExample", example);
    }

    public boolean deleteByPrimaryKey(Class cls, Long id) {
        AssertUtil.notNull(cls);
        AssertUtil.notNull(id);
        return this.getSqlSession().delete(namespacePrefix + cls.getSimpleName() + "Mapper.deleteByPrimaryKey", id) == 1;
    }

    public <T> boolean insert(T record) {
        AssertUtil.notNull(record);
        return this.getSqlSession().insert(this.namespacePrefix + record.getClass().getSimpleName() + "Mapper.insert", record) == 1;
    }

    public <T> boolean insertSelective(T record) {
        AssertUtil.notNull(record);
        return this.getSqlSession().insert(this.namespacePrefix + record.getClass().getSimpleName() + "Mapper.insert", record) == 1;
    }

    public <T, E> List<T> selectByExample(E example) {
        AssertUtil.notNull(example);
        return this.getSqlSession().selectList(this.namespacePrefix + this.getClassFromExampleName(example.getClass().getSimpleName()) + "Mapper.selectByExample", example);
    }

    public <T, E> PageVO<T> selectByExample(E example, int pageNum, int pageSize) {
        AssertUtil.notNull(example);
        AssertUtil.isTrue(pageNum >= 0 && pageSize > 0);
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<T> data = this.getSqlSession().selectList(this.namespacePrefix + this.getClassFromExampleName(example.getClass().getSimpleName()) + "Mapper.selectByExample", example);
        return new PageVO<>(page.getTotal(), data);
    }

    public <T> T selectByPrimaryKey(Class<T> cls, Long id) {
        AssertUtil.notNull(cls);
        AssertUtil.notNull(id);
        return this.getSqlSession().selectOne(this.namespacePrefix + cls.getSimpleName() + "Mapper.selectByPrimaryKey", id);
    }

    public <T> boolean updateByPrimaryKeySelective(T record) {
        AssertUtil.notNull(record);
        return this.getSqlSession().update(this.namespacePrefix + record.getClass().getSimpleName() + "Mapper.updateByPrimaryKeySelective", record) == 1;
    }

    public <T> boolean updateByPrimaryKey(T record) {
        AssertUtil.notNull(record);
        return this.getSqlSession().update(this.namespacePrefix + record.getClass().getSimpleName() + "Mapper.updateByPrimaryKey", record) == 1;
    }

    public <E> int updateByExample(E example) {
        AssertUtil.notNull(example);
        return this.getSqlSession().update(this.namespacePrefix + this.getClassFromExampleName(example.getClass().getSimpleName()) + "Mapper.updateByExample", example);
    }

    public <E> int deleteByExample(E example) {
        AssertUtil.notNull(example);
        return this.getSqlSession().delete(this.namespacePrefix + this.getClassFromExampleName(example.getClass().getSimpleName()) + "Mapper.deleteByExample", example);
    }
}
