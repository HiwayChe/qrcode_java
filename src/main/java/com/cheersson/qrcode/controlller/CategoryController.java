package com.cheersson.qrcode.controlller;

import com.cheersson.qrcode.model.Category;
import com.cheersson.qrcode.service.CategoryService;
import com.cheersson.qrcode.vo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/{id}")
    public Category get(@PathVariable("id") @RequestParam(required = true) Long id){

        return this.categoryService.get(id);
    }
//    public function get($id)
//    {
//        $error = ValidateUtil::isLogin();
//        if(!$error){
//            return ApiResult::fail(401, "请先登录");
//        }
//        $error = ValidateUtil::isNumber($id);
//        if(!$error){
//            return ApiResult::fail(500, "参数不合法");
//        }
//        $result = DB::selectOne("select * from category where id=?", [$id]);
//        return ApiResult::success($result);
//    }
//
//    public function list()
//    {
//        $error = ValidateUtil::isLogin();
//        if(!$error){
//            return ApiResult::fail(401, "请先登录");
//        }
//        return ApiResult::success(DB::select("select * from category"));
//    }
//
//    public function save(Request $request)
//    {
//        $error = ValidateUtil::isLogin();
//        if(!$error){
//            return ApiResult::fail(401, "请先登录");
//        }
//        if(ValidateUtil::isBlank($request['id'])){
//        $result = DB::insert("insert into category(code, name, rule, available) values (?, ?, ?, ?)", [$request['code'], $request['name'], $request['rule'], $request['available']]);
//        if ($result) {
//            return ApiResult::success();
//        } else {
//            return ApiResult::fail(500, "分类保存失败");
//        }
//    }else{
//        $count = DB::update("update category set code=?, name=?, rule=?, available=? where id=?", [$request['code'], $request['name'], $request['rule'], $request['available'], $request['id']]);
//        if ($count > 0) {
//            return ApiResult::success();
//        } else {
//            return ApiResult::fail(500, "分类保存失败");
//        }
//    }
//    }
//
//    public function delete($id)
//    {
//        $error = ValidateUtil::isLogin();
//        if(!$error){
//            return ApiResult::fail(401, "请先登录");
//        }
//        $error = ValidateUtil::isNumber($id);
//        if(!$error){
//            return ApiResult::fail(500, '参数不合法');
//        }
//        //先删除分类下的基础资料
//        DB::delete("delete from item where categoryId = ?", [$id]);
//        $count = DB::delete("delete from category where id = ?", [$id]);
//        if ($count > 0) {
//            return ApiResult::success();
//        } else {
//            return ApiResult::fail(500, "删除分类失败");
//        }
//    }
}
