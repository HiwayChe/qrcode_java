package com.cheersson.qrcode.controlller.admin;

import com.cheersson.qrcode.model.User;
import com.cheersson.qrcode.model.UserExample;
import com.cheersson.qrcode.service.UserService;
import com.cheersson.qrcode.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cheguangai
 * @date 2019/3/17 0017
 */
@Controller
@RequestMapping("/admin")
public class LoginAdminController {
    private final String LoggedInKey = "hasLoggedIn";

    @Autowired
    private UserService<User, UserExample> userService;

    @GetMapping("/login")
    public String index() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        User user = this.userService.one(userExample);
        if (user == null || !StringUtils.equalsIgnoreCase(user.getPassword(), password)) {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
        if (!user.getAvailable()) {
            model.addAttribute("error", "用户被锁定");
            return "login";
        }

        WebUtil.getRequest().getSession(true).setAttribute(LoggedInKey, true);
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(LoggedInKey);
        request.getSession().invalidate();
        return "redirect:/admin/login";
    }
}
