package com.cheersson.qrcode.controlller.admin;

import com.cheersson.qrcode.model.Category;
import com.cheersson.qrcode.model.CategoryExample;
import com.cheersson.qrcode.model.Item;
import com.cheersson.qrcode.model.ItemExample;
import com.cheersson.qrcode.service.CategoryService;
import com.cheersson.qrcode.service.ItemService;
import com.cheersson.qrcode.vo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryAdminController {

    @Autowired
    private CategoryService<Category, CategoryExample> categoryService;
    @Autowired
    private ItemService<Item, ItemExample> itemService;

    @RequestMapping("/get")
    public ApiResult<Category> get( @RequestParam(required = true) Long id) {
        return ApiResult.success(this.categoryService.get(id));
    }

    @RequestMapping("/list")
    public ApiResult<List<Category>> list() {
        return ApiResult.success(this.categoryService.list(new CategoryExample()));
    }

    @RequestMapping("/save")
    public ApiResult save(@RequestBody Category category) {
        if (category.getId() == null) {
            this.categoryService.save(category);
        } else {
            this.categoryService.update(category);
        }
        return ApiResult.success(category);
    }

    @RequestMapping("/delete")
    public ApiResult<Boolean> delete(@RequestParam("id") Long id) {
        ItemExample itemExample = new ItemExample();
        itemExample.createCriteria().andCategoryIdEqualTo(id);
        this.itemService.deleteByExample(itemExample);
        return ApiResult.success(this.categoryService.delete(id));
    }

}
