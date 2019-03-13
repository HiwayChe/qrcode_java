package com.cheersson.qrcode.service.impl;

import com.cheersson.qrcode.model.Category;
import com.cheersson.qrcode.model.CategoryExample;
import com.cheersson.qrcode.service.CategoryService;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryExample> implements CategoryService<Category, CategoryExample> {
    @Override
    protected Class<Category> getModelClass() {
        return Category.class;
    }
}
