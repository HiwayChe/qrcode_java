package com.cheersson.qrcode.controlller;

import com.cheersson.qrcode.exception.BizException;
import com.cheersson.qrcode.exception.LoginException;
import com.cheersson.qrcode.vo.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<String> sysException(Throwable throwable, Model model) {
        logger.error(throwable.getMessage(), throwable);
        return ApiResult.fail(-1, "系统发生错误，请联系管理员");
    }

    @ExceptionHandler({BizException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<String> bizException(BizException bizException, Model model){
        logger.error(bizException.getMessage(), bizException);
        model.addAttribute("error", bizException.getMessage());
        return ApiResult.fail(bizException.getMessage());
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String loginException(LoginException loginException, Model model){
        logger.error(loginException.getMessage(), loginException);
        return "redirect:/admin/login";
        //response.sendRedirect("/admin/login");
    }
}
