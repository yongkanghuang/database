package com.test.database.utils;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private static Integer expireSeconds;

    private static String jwtSecret;

    //token过期秒数
    @Value("${jwt.token.expiresSecond}")
    public void setExpireSeconds(Integer expireSeconds) {
        TokenUtils.expireSeconds = expireSeconds;
    }
    //私钥
    @Value("${jwt.token.jwtSecret}")
    public void setJwtSecret(String jwtSecret) {
        TokenUtils.jwtSecret = jwtSecret;
    }

   /* public static String createJwtToken(String username) {
        Map<String, Object> claims = new HashMap<>(16);
        claims.put("sub", username);
        claims.put("created", new Date());
        return generateToken((claims));
    }*/

    public static String createJwtToken(String id ,String subject) throws Base64DecodingException {
        Date now = new Date(System.currentTimeMillis());
        Long currentTime = System.currentTimeMillis();
        Date expData = new Date(currentTime + expireSeconds);
        SecretKey secretKey = generalKey();
        return Jwts.builder().
                setId(id).
                setIssuer(subject).
                setIssuedAt(now)
               //setClaims(claims)        //payload
                .setExpiration(expData)  //过期时间
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();  //// 签名算法以及密匙
    }

    public static SecretKey generalKey() throws Base64DecodingException {
        byte[] encodedKey = Base64.decode(jwtSecret);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


}
