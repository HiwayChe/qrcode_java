package com.cheersson.qrcode.dao;

import com.cheersson.qrcode.model.Category;
import com.cheersson.qrcode.model.CategoryExample;
import com.cheersson.qrcode.util.ReflectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BaseDao extends SqlSessionDaoSupport{
    private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);

    private final String Namespace_Prefix = "com.cheersson.qrcode.mapper.";

    private Class getClassFromExampleName(String example){
        if(StringUtils.endsWith(example, "Example")){
            return ReflectionUtil.loadClass(example.substring(0, example.length() - 7));
        }
        return null;
    }

    public long countByExample(CategoryExample example) {
        Class cls = this.getClassFromExampleName(example.getClass().getName());
        return this.getSqlSession().selectOne(Namespace_Prefix + cls.getSimpleName() + "Mapper.countByExample", example);
    }

    int deleteByPrimaryKey(Class cls, Long id) {
        return this.getSqlSession().delete(Namespace_Prefix + cls.getSimpleName() + "Mapper.deleteByPrimaryKey", id);
    }

    int insert(Category record) {
        return 0;
    }

    int insertSelective(Category record) {
        return 0;
    }

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}
