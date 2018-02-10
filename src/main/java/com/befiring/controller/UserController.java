package com.befiring.controller;

import com.befiring.annotations.IgnoreSecurity;
import com.befiring.entity.ReturnValue;
import com.befiring.entity.User;
import com.befiring.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/add")
    public @ResponseBody ReturnValue addNewUser(@RequestParam(value = "username") String name,@RequestParam(value = "password") String password){
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        if (userRepository.save(user)!=null){
            return new ReturnValue.Builder(true).build();
        }else {
            return new ReturnValue.Builder(false).message("写入数据库出错").build();
        }

    }

    @IgnoreSecurity
    @RequestMapping(path = "/all")
    public @ResponseBody ReturnValue getAllUsers(){
        Iterable<User> iterable = userRepository.findAll();
        List<User> list = new ArrayList<>();
        iterable.forEach(new Consumer<User>() {
            @Override
            public void accept(User single) {
                list.add(single);
            }
        });
        HashMap<Object,Object> dataMap=new HashMap<>();
        dataMap.put("total",list.size());
        dataMap.put("rows",list);
        return new ReturnValue.Builder(true).data(dataMap).build();
    }

    @RequestMapping(path = "/update")
    public @ResponseBody ReturnValue updateUser(@RequestParam(value = "user")String userJson){
        User user;
        try {
             user=new ObjectMapper().readValue(userJson,User.class);
        } catch (IOException e) {
            e.printStackTrace();
             return new ReturnValue.Builder(false).message("无法解析参数").build();
        }
        if (user != null) {
            if(userRepository.save(user)!=null){
                return new ReturnValue.Builder(true).build();
            }
        }
         return new ReturnValue.Builder(false).message("写入数据库出错").build();
    }

    @RequestMapping(path = "/delete")
    public @ResponseBody ReturnValue deleteUser(@RequestParam(value = "id")Long id){
        try{
            userRepository.delete(id);
        }catch (Exception e){
            return new ReturnValue.Builder(false).message(e.getMessage()).build();
        }

        return new ReturnValue.Builder(true).build();
    }
}
