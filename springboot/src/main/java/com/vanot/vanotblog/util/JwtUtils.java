package com.vanot.vanotblog.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * jwt 工具
 ** 生成 jwt 字段，保存用户 ID 信息用于验证用户是否登录
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "vanotblog.jwt")
public class JwtUtils {

    private String secret;
    private long expire;
    private String header;

    /**
     * 生成jwt token
     */
    public String generateToken(long id) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        // 设置 JWT 的 Header、Payload、Signature
        return Jwts.builder()
                // Header
                .setHeaderParam("typ", "JWT")
                // Payload
                .setSubject(id+"")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                // Signature
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 获取 Claim，即 JWT 中 Payload 的内容。也是 JWT TOKEN 所携带的有效信息部分
     */
    public Claims getClaimByToken(String token) {
        try {
            // 若 token 有效期过了，将抛出异常
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.debug("validate is token error ", e);
            return null;
        }
    }
}
