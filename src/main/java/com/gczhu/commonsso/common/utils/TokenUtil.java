package com.gczhu.commonsso.common.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    @Autowired
    private Environment env;
    public String generateJwtToken(String secret, String issuer, String id, String subject, Map<String,Object> map){
        byte[] encode = Base64.getEncoder().encode(secret.getBytes());
        long currentTime = System.currentTimeMillis();
        long keepTime = Long.parseLong(env.getProperty("login.token.timeout"))*1000*60;


        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuer(issuer)
                .setSubject(subject)
                .setExpiration(new Date(currentTime + keepTime))
                .signWith(SignatureAlgorithm.HS256, encode);

        if(map == null){
            map = new HashMap<>();
        }
        map.put("createTime",new Date(currentTime));
        builder.setClaims(map);



        return builder.compact();
    }
    public String generateJwtToken(String secret, String issuer, String id, String subject){
        return generateJwtToken(secret,issuer,id,subject,null);
    }
}
