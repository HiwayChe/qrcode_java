package com.cheersson.qrcode.controlller;

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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService<Category, CategoryExample> categoryService;
    @Autowired
    private ItemService<Item, ItemExample> itemService;

    @RequestMapping("/{id}")
    public ApiResult<Category> get(@PathVariable("id") @RequestParam(required = true) Long id) {
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

    @RequestMapping("/delete/{id}")
    public ApiResult<Boolean> delete(@PathVariable("id") Long id) {
        ItemExample itemExample = new ItemExample();
        itemExample.createCriteria().andCategoryidEqualTo(id);
        this.itemService.deleteByExample(itemExample);
        return ApiResult.success(this.categoryService.delete(id));
    }

}
