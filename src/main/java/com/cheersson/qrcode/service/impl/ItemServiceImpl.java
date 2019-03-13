package com.cheersson.qrcode.service.impl;

import com.cheersson.qrcode.model.Item;
import com.cheersson.qrcode.model.ItemExample;
import com.cheersson.qrcode.service.ItemService;
import org.springframework.stereotype.Service;

@Service("itemService")
public class ItemServiceImpl extends BaseServiceImpl<Item, ItemExample> implements ItemService<Item, ItemExample> {
    @Override
    protected Class<Item> getModelClass() {
        return Item.class;
    }
}
