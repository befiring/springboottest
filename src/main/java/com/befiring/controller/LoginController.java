package com.befiring.controller;

import com.befiring.annotations.IgnoreSecurity;
import com.befiring.entity.ReturnValue;
import com.befiring.entity.Token;
import com.befiring.entity.User;
import com.befiring.exception.ErrorCode;
import com.befiring.exception.ErrorMessage;
import com.befiring.repository.UserRepository;
import com.befiring.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private UserRepository userRepository;


    @IgnoreSecurity
    @PostMapping("/login")
    @ResponseBody
    public ReturnValue login(@RequestParam(value = "username")String username, @RequestParam(value = "password")String password){
        User user=userRepository.findByNameAndPassword(username,password);
        if(user!=null){
            Token token= TokenUtils.GenerateToken(user);
            HashMap<Object,Object> dataMap=new HashMap<>();
            dataMap.put("token",token);
            return new ReturnValue.Builder(true).data(dataMap).build();
        }
        return new ReturnValue.Builder(false).code(ErrorCode.ACCOUNT_PASSWORD_WRONG).message(ErrorMessage.ACCOUNT_PASSWORD_WRONG).build();
    }
}
