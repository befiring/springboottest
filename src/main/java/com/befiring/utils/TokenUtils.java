package com.befiring.utils;

import com.befiring.Global;
import com.befiring.entity.Token;
import com.befiring.entity.User;
import com.befiring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class TokenUtils {
    @Autowired
    UserRepository userRepository;
    @Bean
    public static Token GenerateToken(User user){
        Token token=new Token();
        token.setValue(user.getName()+System.currentTimeMillis());
        token.setExpireTime(System.currentTimeMillis()+ Global.TOKEN_ACTIVE_TIME);
        return token;
    }

//   public static User analysisToken(Token token){
//
//   }
}
