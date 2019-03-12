package com.cheersson.qrcode.controlller;

import com.cheersson.qrcode.vo.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler({Throwable.class})
    public ApiResult<String> exception(Throwable throwable) {
        return ApiResult.fail(-1, "系统发生错误：" + throwable.getMessage());
    }


}
