package com.befiring.controller;

import com.befiring.entity.User;
import com.befiring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/")
    public String login() {
        return "login";
    }
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/login")
    @ResponseBody
    public boolean login(@RequestParam(value = "username")String username,@RequestParam(value = "password")String password){
        return userRepository.findByNameAndPassword(username,password)!=null;
    }
}
