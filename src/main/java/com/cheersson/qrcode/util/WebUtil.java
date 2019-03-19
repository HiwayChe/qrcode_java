package com.cheersson.qrcode.util;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WebUtil {

    public static HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes != null){
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    public static boolean isAjaxRequest(){
        HttpServletRequest request = getRequest();
        if(request == null){
            return false;
        }
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"));
    }

    public static  boolean hasLoggedIn(){
        HttpServletRequest request = getRequest();
        if(request == null){
            return false;
        }
        HttpSession session = request.getSession(false);
        return session != null && BooleanUtils.toBoolean((Boolean) session.getAttribute("hasLoggedIn"));
    }

    public static boolean isLoginRequest(){
        HttpServletRequest request = getRequest();
        if(request == null){
            return false;
        }
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        if((method.equalsIgnoreCase("get") || method.equalsIgnoreCase("post"))
                && "/admin/login".equalsIgnoreCase(requestURI)){
            return true;
        }
        return false;
    }
}
