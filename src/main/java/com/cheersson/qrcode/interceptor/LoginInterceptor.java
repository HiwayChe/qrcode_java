package com.cheersson.qrcode.interceptor;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session == null || !BooleanUtils.toBoolean((Boolean)session.getAttribute("isLogin"))){
            response.sendRedirect("/admin/login");
        }
        return super.preHandle(request, response, handler);
    }
}
