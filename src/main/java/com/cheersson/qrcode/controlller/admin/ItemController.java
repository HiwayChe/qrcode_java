package com.cheersson.qrcode.controlller.admin;

import com.cheersson.qrcode.model.Item;
import com.cheersson.qrcode.model.ItemExample;
import com.cheersson.qrcode.service.ItemService;
import com.cheersson.qrcode.vo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/item")
public class ItemController {
    @Autowired
    private ItemService<Item, ItemExample> itemService;

    @GetMapping("/index")
    public String index(){
        return "item";
    }

    @ResponseBody
    @RequestMapping("/get")
    public ApiResult<Item> get( @RequestParam(required = true) Long id) {
        return ApiResult.success(this.itemService.get(id));
    }

    @ResponseBody
    @RequestMapping("/list")
    public ApiResult<List<Item>> list(@RequestParam("cid") Long cid) {
        ItemExample itemExample = new ItemExample();
        itemExample.createCriteria().andCategoryIdEqualTo(cid);
        return ApiResult.success(this.itemService.list(itemExample));
    }

    @ResponseBody
    @RequestMapping("/save")
    public ApiResult save(@RequestBody Item item) {
        if (item.getId() == null) {
            this.itemService.save(item);
        } else {
            this.itemService.update(item);
        }
        return ApiResult.success(item);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ApiResult<Boolean> delete(@RequestParam("id") Long id) {
        return ApiResult.success(this.itemService.delete(id));
    }
}
