package com.hnustlgy.securitystudy.utils;

import io.jsonwebtoken.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JWTUtils {

    private static String secretKey= "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9"; //加密解密秘钥

    /**
     *
     * @param iss 用户id
     * @param claims 用户信息
     * @param time 过期时间（ms）
     * @return
     */
    public static String create(String iss, Map<String,Object> claims ,long time){
        if (claims==null){
            claims=new HashMap<>();
        }

        JwtBuilder jwtBuilder= Jwts.builder();
        String token= jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .setClaims(claims)  //其它非隐私信息，比如userID、username
                .setSubject(iss) //签发人，也就是JWT是给谁的（逻辑上一般都是username或者userId）
                .setExpiration(new Date(System.currentTimeMillis()+time)) //过期时间
                .setId(UUID.randomUUID().toString())  //token唯一标识
                //signature
                .signWith(SignatureAlgorithm.HS256,secretKey)
                //用“.”拼接
                .compact();

        return token;
    }

    public static Claims parse(String token){
        JwtParser parser= Jwts.parser();
        Jws<Claims> claimsJws = null;
        try {
            claimsJws = parser.setSigningKey(secretKey).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        return claimsJws.getBody();
    }
}


