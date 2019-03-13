package com.cheersson.qrcode.controlller;

import com.cheersson.qrcode.vo.ApiResult;
import com.cheersson.qrcode.vo.CodeVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
public class ValidateController {

    @RequestMapping("/")
    public ApiResult<Boolean> validate(@RequestBody CodeVO codeVO){

        return null;
    }
}
