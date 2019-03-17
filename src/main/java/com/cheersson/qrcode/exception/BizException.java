package com.cheersson.qrcode.exception;

import com.cheersson.qrcode.util.MessageUtil;

public class BizException extends RuntimeException{
    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String code, Object... args){
        super(MessageUtil.getMessage(code, args));
    }
}
