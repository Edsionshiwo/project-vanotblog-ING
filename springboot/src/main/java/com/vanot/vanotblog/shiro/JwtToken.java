package com.vanot.vanotblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * shiro 框架内的 Token 实例。
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String jwt) {
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
