package com.cheersson.qrcode.interceptor;

import com.alibaba.druid.support.json.JSONUtils;
import com.cheersson.qrcode.util.WebUtil;
import com.cheersson.qrcode.vo.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (WebUtil.hasLoggedIn() || WebUtil.isLoginRequest()) {
            return true;
        }
        if (WebUtil.isAjaxRequest()) {
            ApiResult apiResult = ApiResult.fail(401, "请先登录");
            String s = JSONUtils.toJSONString(apiResult);
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentLength(s.length());
            response.getWriter().write(s);
            response.getWriter().flush();
        } else {
            response.sendRedirect("/admin/login");
        }
        return false;
    }
}
