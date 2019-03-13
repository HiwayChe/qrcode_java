package com.cheersson.qrcode.controlller;

import com.cheersson.qrcode.model.Item;
import com.cheersson.qrcode.model.ItemExample;
import com.cheersson.qrcode.service.ItemService;
import com.cheersson.qrcode.vo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService<Item, ItemExample> itemService;

    @RequestMapping("/{id}")
    public ApiResult<Item> get(@PathVariable("id") @RequestParam(required = true) Long id) {
        return ApiResult.success(this.itemService.get(id));
    }

    @RequestMapping("/list")
    public ApiResult<List<Item>> list() {
        return ApiResult.success(this.itemService.list(null));
    }

    @RequestMapping("/save")
    public ApiResult save(@RequestBody Item item) {
        if (item.getId() == null) {
            this.itemService.save(item);
        } else {
            this.itemService.update(item);
        }
        return ApiResult.success(item);
    }

    @RequestMapping("/delete/{id}")
    public ApiResult<Boolean> delete(@PathVariable("id") Long id) {
        return ApiResult.success(this.itemService.delete(id));
    }
}
