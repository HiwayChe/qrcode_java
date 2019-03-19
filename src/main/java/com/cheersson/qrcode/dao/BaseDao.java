package com.cheersson.qrcode.dao;

import com.cheersson.qrcode.util.AssertUtil;
import com.cheersson.qrcode.vo.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BaseDao {
    private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);

    private final String namespacePrefix = "com.cheersson.qrcode.mapper.";

    private SqlSession sqlSession;

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
    }

    public SqlSession getSqlSession() {
        return this.sqlSession;
    }


    private String getModelMapperName(String clsName) {
        if (clsName.contains(".")) {
            clsName = clsName.substring(clsName.lastIndexOf(".") + 1);
        }
        if (StringUtils.endsWith(clsName, "Example")) {
            return namespacePrefix + clsName.substring(0, clsName.length() - 7) + "Mapper";
        } else {
            return namespacePrefix + clsName + "Mapper";
        }
    }

    public <E> long countByExample(E example) {
        AssertUtil.notNull(example, "example could not be null");
        return this.getSqlSession().selectOne(this.getModelMapperName(example.getClass().getSimpleName()) + ".countByExample", example);
    }

    public boolean deleteByPrimaryKey(Class cls, Long id) {
        AssertUtil.notNull(cls);
        AssertUtil.notNull(id);
        return this.getSqlSession().delete(this.getModelMapperName(cls.getSimpleName()) + ".deleteByPrimaryKey", id) == 1;
    }

    public <T> boolean insert(T record) {
        AssertUtil.notNull(record);
        return this.getSqlSession().insert(this.getModelMapperName(record.getClass().getSimpleName()) + ".insert", record) == 1;
    }

    public <T> boolean insertSelective(T record) {
        AssertUtil.notNull(record);
        return this.getSqlSession().insert(this.getModelMapperName(record.getClass().getSimpleName()) + ".insert", record) == 1;
    }

    public <T, E> List<T> selectByExample(E example) {
        AssertUtil.notNull(example);
        return this.getSqlSession().selectList(this.getModelMapperName(example.getClass().getSimpleName()) + ".selectByExample", example);
    }

    public <T, E> PageVO<T> selectByExample(E example, int pageNum, int pageSize) {
        AssertUtil.notNull(example);
        AssertUtil.isTrue(pageNum >= 0 && pageSize > 0);
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<T> data = this.getSqlSession().selectList(this.getModelMapperName(example.getClass().getSimpleName()) + ".selectByExample", example);
        return new PageVO<>(page.getTotal(), data);
    }

    public <T> T selectByPrimaryKey(Class<T> cls, Long id) {
        AssertUtil.notNull(cls);
        AssertUtil.notNull(id);
        return this.getSqlSession().selectOne(this.getModelMapperName(cls.getSimpleName()) + ".selectByPrimaryKey", id);
    }

    public <T> boolean updateByPrimaryKeySelective(T record) {
        AssertUtil.notNull(record);
        return this.getSqlSession().update(this.getModelMapperName(record.getClass().getSimpleName()) + ".updateByPrimaryKeySelective", record) == 1;
    }

    public <T> boolean updateByPrimaryKey(T record) {
        AssertUtil.notNull(record);
        return this.getSqlSession().update(this.getModelMapperName(record.getClass().getSimpleName()) + ".updateByPrimaryKey", record) == 1;
    }

    public <E> int updateByExample(E example) {
        AssertUtil.notNull(example);
        return this.getSqlSession().update(this.getModelMapperName(example.getClass().getSimpleName()) + ".updateByExample", example);
    }

    public <E> int deleteByExample(E example) {
        AssertUtil.notNull(example);
        return this.getSqlSession().delete(this.getModelMapperName(example.getClass().getSimpleName()) + ".deleteByExample", example);
    }

    public <T, E> T one(E example) {
        AssertUtil.notNull(example);
        return this.getSqlSession().selectOne(this.getModelMapperName(example.getClass().getSimpleName()) + ".selectByExample", example);
    }

    public <T, E> List<T> list(E example, int top){
        AssertUtil.notNull(example);
        AssertUtil.isTrue(top > 0);
        return this.getSqlSession().selectList(this.getModelMapperName(example.getClass().getSimpleName()) + ".selectByExample", example, new RowBounds(0, top));
    }
}
