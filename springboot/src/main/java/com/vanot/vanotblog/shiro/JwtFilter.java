package com.vanot.vanotblog.shiro;

import cn.hutool.json.JSONUtil;
import com.vanot.vanotblog.common.lang.*;
import com.vanot.vanotblog.util.*;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends AuthenticatingFilter {

    @Autowired
    JwtUtils jwtUtils;


    /**
     * 根据请求头部的权限信息生成 Token，这里的 Token 是指 shiro 框架内的 token，用于进行 subject 的登录验证
     * 在 executeLogin 方法中被调用。
     * @return AuthenticationToken
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        System.out.println("CREATE TOKEN");
        // 根据请求中的 Authorization 字段查找 JWT TOKEN 信息
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");

        if(StringUtils.isEmpty(jwt)) {
            return null;
        }

        // 生成 JwtToken，用于 shiro 登录认证。在 subject.login(token) 中被引用验证。
        return new JwtToken(jwt);
    }

    /**
     * 在未登录的状态下通过该方法拦截请求。
     * 检查
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        System.out.println("ON ACCESS DENIED");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");

        if(StringUtils.isEmpty(jwt)) {
            return true;
        } else {
            // 获取 token 中的主体信息 claim。
            // 若 token 过期失效，则返回 null
            Claims claim = jwtUtils.getClaimByToken(jwt);

            if(claim == null) {
                throw new ExpiredCredentialsException("token 已失效，请重新登录");
            }
        }
            // 执行登录
            return executeLogin(servletRequest, servletResponse);
        }

    /**
     * 登录失败执行该方法
     * 在 executeLogin 方法中被调用，在 login 操作失败后 catch 异常并执行此方法。
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 获取登录异常信息
        Throwable throwable = e.getCause() == null ? e : e.getCause();
        Result result = Result.fail(throwable.getMessage());

        // 打包成 json 回传
        String json = JSONUtil.toJsonStr(result);
        try {
            httpServletResponse.getWriter().print(json);
        }
        catch (IOException ignored) {
        }

        return false;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
//        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
//        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
//
//        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
//        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
//            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
//            return false;
//        }

        return super.preHandle(request, response);
    }
}
