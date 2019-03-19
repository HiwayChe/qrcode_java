package com.cheersson.qrcode.interceptor;

import com.cheersson.qrcode.exception.LoginException;
import com.cheersson.qrcode.util.WebUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(WebUtil.hasLoggedIn() || WebUtil.isLoginRequest()){
            return true;
        }
        throw new LoginException("请先登录");
    }
}
