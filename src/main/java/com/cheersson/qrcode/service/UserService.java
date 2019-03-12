package com.cheersson.qrcode.service;

import com.cheersson.qrcode.model.User;

public interface UserService {

    User get(Long id);

    User get(String username);

}
