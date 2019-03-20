package com.cheersson.qrcode.controlller.admin;

import com.cheersson.qrcode.model.User;
import com.cheersson.qrcode.model.UserExample;
import com.cheersson.qrcode.service.UserService;
import com.cheersson.qrcode.util.AssertUtil;
import com.cheersson.qrcode.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cheguangai
 * @date 2019/3/17 0017
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @GetMapping("/login")
    public String index(){
        return "login";
    }

    @Autowired
    private UserService<User, UserExample> userService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,  Model model){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        User user = this.userService.one(userExample);
        AssertUtil.notNull(user, "用户名或密码错误");
        AssertUtil.contentEquals(user.getPassword(), password, false, "用户名或密码错误");
        WebUtil.getRequest().getSession(true).setAttribute("hasLoggedIn", true);
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("hasLoggedIn");
        request.getSession().invalidate();
        return "redirect:/admin/login";
    }
}
