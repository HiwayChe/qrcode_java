package com.cheersson.qrcode.controlller;

import com.cheersson.qrcode.exception.BizException;
import com.cheersson.qrcode.model.Category;
import com.cheersson.qrcode.model.CategoryExample;
import com.cheersson.qrcode.service.CategoryService;
import com.cheersson.qrcode.util.CodeUtil;
import com.cheersson.qrcode.vo.ApiResult;
import com.cheersson.qrcode.vo.CodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mini")
public class MiniController {

    @Autowired
    private CategoryService<Category, CategoryExample> categoryService;

    @PostMapping("/validate")
    public ApiResult<Boolean> validate(@RequestBody CodeVO codeVO) {
        Category category = this.categoryService.get(codeVO.getCategoryId());
        try{
            CodeUtil.validate(codeVO.getCode(), category.getRule(), codeVO.getCategoryId());
            return ApiResult.success(true);
        }catch(BizException e){
            return ApiResult.fail(500, e.getMessage());
        }
    }

    @PostMapping("/category/list")
    public ApiResult<List<Category>> listCategory(){
        CategoryExample categoryExample = new CategoryExample();
        List<Category> list = this.categoryService.list(categoryExample);
        return ApiResult.success(list);
    }
}
