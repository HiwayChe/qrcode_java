package com.cheersson.qrcode.controlller;

import com.cheersson.qrcode.vo.ApiResult;
import com.cheersson.qrcode.vo.CodeVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validate")
public class ValidateController {

    @PostMapping("")
    public ApiResult<Boolean> validate(@RequestBody CodeVO codeVO) {
        return null;
    }
}
