package com.cheersson.qrcode.controlller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cheguangai
 * @date 2019/3/17 0017
 */
@Controller
@RequestMapping("/admin/login")
public class LoginController {

    @RequestMapping("/")
    public String index(){
        return "login";
    }

}
