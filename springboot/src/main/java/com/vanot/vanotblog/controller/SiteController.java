package com.vanot.vanotblog.controller;

import com.vanot.vanotblog.common.lang.Result;
import com.vanot.vanotblog.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {
    @Autowired
    SiteService siteService;

    @GetMapping("/hello")
    public Result hello() {
        return Result.succ("Hello");
    }

    @GetMapping("/site")
    public Result siteInfo() {
        return Result.succ(siteService.getById(1));
    }
}