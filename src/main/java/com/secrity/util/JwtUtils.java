package com.secrity.util;

import java.util.Date;
import java.util.UUID;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {
    //过期时间：单位ms
    private static final long EXPIRED_TIME =30*60*1000;
    //密匙
    private static String secretyKey = "secrityProject";

    /**
     * 生成token
     * @return
     */
    public static String getToken(String userId) {
        JwtBuilder jwtBuilder = Jwts.builder()
                /**
                 * header
                 */
                .setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256")
                /**
                 * payload
                 */
                .setId(UUID.randomUUID().toString())  //{"jti":""}  jwtid:用于标识该jwt
                .setSubject("JJWT")         //{"sub":""}  主题
                .setIssuedAt(new Date())    //{"iat":""}  发布时间
                //当时间过期时，解析token会抛出ExpiredJwtException 异常
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED_TIME)) //{"exp":""} 过期时间
                .claim("userId", userId)  //自定义参数
                /**
                 * signature
                 */
                .signWith(SignatureAlgorithm.HS256, secretyKey);

        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * Token解析
     * @param token
     * @return
     */
    public static Claims parseToken(
        String token) {
        if (StrUtil.isEmpty(token))
            return null;
        try {
            JwtParser parser = Jwts.parser();
            Jws<Claims> claims = parser.setSigningKey(secretyKey).parseClaimsJws(token);
            Claims body = claims.getBody();
            return body;
        }
        catch (Exception e) {
            //当时间过期时，解析token会抛出ExpiredJwtException 异常
            System.out.println(e.toString());
            return null;
        }
    }
}
