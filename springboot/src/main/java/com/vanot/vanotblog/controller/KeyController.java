package com.vanot.vanotblog.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vanot.vanotblog.common.dto.KeyDto;
import com.vanot.vanotblog.common.lang.Result;
import com.vanot.vanotblog.entity.Key;
import com.vanot.vanotblog.service.KeyService;
import com.vanot.vanotblog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static org.apache.shiro.SecurityUtils.getSubject;

@RestController()
public class KeyController {
    /**
     * Autowired 注解
     * 自动从工厂中获取需要的实例
     */
    @Autowired
    KeyService keyService;
    @Autowired
    JwtUtils jwtUtils;


//    @PostMapping("/key")
//    public Result key(@Validated @RequestBody KeyDto keyDto, HttpServletResponse response){
//        String key = keyDto.getKey();
//        Key keySubject = new Key();
//        keySubject.setKeyvalue(SecureUtil.md5(key));
//        keySubject.setType(0);
//
//        keyService.save(keySubject);
//
//        return Result.succ(MapUtil.builder().put("id", keySubject.getId()).map());
//    }



    @PostMapping("/unlock")
    public Result unlock(@Validated @RequestBody KeyDto keyDto, HttpServletResponse response){

        Key key = keyService.getOne(new QueryWrapper<Key>().eq("keyvalue", SecureUtil.md5(keyDto.getKey())));
        Assert.notNull(key, "密钥不存在");
        // 生成 JWT TOKEN 携带密钥信息
        String jwt = jwtUtils.generateToken(key.getId());
        // JWT TOKEN 送回给前端
        response.setHeader("Authorization", jwt);
        // 跨域暴露 JWT TOKEN
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        System.out.println(jwt);

        return Result.succ(MapUtil.builder().put("id", key.getId()).map());
    }

    /**
     * RequiresAuthentication 注解
     * 来自 Shiro 表示该接口需要登陆验证
     */
    @RequiresAuthentication
    @GetMapping("/lock")
    public Result lock() {
        getSubject().logout();
        return Result.succ(null);
    }

    @RequiresAuthentication
    @GetMapping("getkey")
    public Result getkey(){
        Subject subject = getSubject();
        return Result.succ(subject.getPrincipal());
    }
}
