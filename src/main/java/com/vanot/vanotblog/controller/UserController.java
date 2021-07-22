package com.vanot.vanotblog.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.vanot.vanotblog.common.lang.Result;
import com.vanot.vanotblog.entity.User;
import com.vanot.vanotblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vanot313
 * @since 2021-07-08
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/addOne")
    public void addOne(){
        System.out.println("1111111");

        String username = "root";
        String password = "123456";
        Integer status = 0;

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setStatus(status);

        userService.save(user);
    }

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        User user = userService.getById(1L);
        return Result.succ(user);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user) {
        return Result.succ(user);
    }

    @GetMapping("/{id}")
    public Object test(@PathVariable("id") Long id){
        return userService.getById(id);
    }
}
