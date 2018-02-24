package com.befiring.controller;

import com.befiring.annotations.IgnoreSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @IgnoreSecurity
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @IgnoreSecurity
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
