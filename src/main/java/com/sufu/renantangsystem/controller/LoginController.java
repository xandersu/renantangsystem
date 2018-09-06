package com.sufu.renantangsystem.controller;

import com.sufu.renantangsystem.Service.IUserService;
import com.sufu.renantangsystem.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by lenovo on 2018/6/15.
 */

@Controller
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(UserEntity userEntity, HttpSession session, Model model) {
        String userName = userEntity.getUserName();
        String password = userEntity.getPassword();
        log.info("userName=" + userName + ",password=" + password);
        UserEntity loginUser = userService.login(userEntity);
        if (loginUser == null) {
            log.error("登录失败");
            model.addAttribute("msg", "登录失败,账号密码不正确");
            return "login";
        } else {
            log.info("登录成功！！id= " + loginUser.getUserId());
            session.setAttribute("loginUser", loginUser);
//            model.addAttribute("loginUserName",loginUser.getUserName());
            System.out.println(session.getAttribute("loginUser"));
        }
        return "redirect:/showList";
    }

}
