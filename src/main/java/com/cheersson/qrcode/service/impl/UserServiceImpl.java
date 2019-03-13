package com.cheersson.qrcode.service.impl;

import com.cheersson.qrcode.model.User;
import com.cheersson.qrcode.model.UserExample;
import com.cheersson.qrcode.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, UserExample> implements UserService<User, UserExample> {

    @Override
    protected Class<User> getModelClass() {
        return User.class;
    }
}
