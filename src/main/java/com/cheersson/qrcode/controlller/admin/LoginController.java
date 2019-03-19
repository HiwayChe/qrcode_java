package com.cheersson.qrcode.controlller.admin;

import com.cheersson.qrcode.model.User;
import com.cheersson.qrcode.model.UserExample;
import com.cheersson.qrcode.service.UserService;
import com.cheersson.qrcode.util.AssertUtil;
import com.cheersson.qrcode.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author cheguangai
 * @date 2019/3/17 0017
 */
@Controller
@RequestMapping("/admin/login")
public class LoginController {

    @GetMapping("/")
    public String index(){
        return "login";
    }

    @Autowired
    private UserService<User, UserExample> userService;

    @PostMapping("/")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        User user = this.userService.one(userExample);
        AssertUtil.notNull(user, "用户名或密码错误");
        AssertUtil.contentEquals(user.getPassword(), password, false, "用户名或密码错误");
        WebUtil.getRequest().getSession().setAttribute("hasLoggedIn", true);
        return "redirect:/admin/index";
    }
}
