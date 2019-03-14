package com.order.web.util.impl;

import com.order.web.util.TokenGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class Md5TokenGenerator implements TokenGenerator {
    @Override
    public String generate(String username) {
        long   timestamp = System.currentTimeMillis();
        String tokenMeta = ""+username;
//        for (String s : strings) {
//            tokenMeta = tokenMeta + s;
//        }
        tokenMeta = tokenMeta + timestamp;
        String token = DigestUtils.md5DigestAsHex(tokenMeta.getBytes());
        return token;
    }
}
