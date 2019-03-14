package com.order.web.util.impl;

import com.order.web.util.TokenGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtils implements TokenGenerator {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    // 角色的key
    private static final String ROLE_CLAIMS = "rol";
    private static final SecretKey SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    private static final String ISS = "echisan";

    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION = 3600L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;

    // 创建token
    public static String createToken(String username, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        String s = Jwts.builder()
                //.signWith(SECRET,SignatureAlgorithm.HS512)
                .signWith(SECRET,SignatureAlgorithm.HS512)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 10000))
                .compact();
        return s;
    }
    // 从token中获取用户名
    public static String getUsername(String token){
        return getTokenBody(token).getSubject();
    }
    // 获取用户角色
    public static String getUserRole(String token){

        return (String) getTokenBody(token).get(ROLE_CLAIMS);
    }
    // 是否已过期
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }
    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String generate(String username) {
        return JwtTokenUtils.createToken(username, true);
    }
}
