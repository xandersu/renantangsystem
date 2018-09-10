package com.sufu.renantangsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lenovo on 2018/6/15.
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test")
    public String test(Model model) {
        model.addAttribute("title", "Test Title");
        return "test";
    }
}
