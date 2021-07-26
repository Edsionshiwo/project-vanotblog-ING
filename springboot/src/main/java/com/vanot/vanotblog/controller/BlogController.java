package com.vanot.vanotblog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vanot.vanotblog.common.lang.Result;
import com.vanot.vanotblog.entity.Blog;
import com.vanot.vanotblog.service.BlogService;
import com.vanot.vanotblog.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vanot313
 * @since 2021-07-08
 */

@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage, 10);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.succ(pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        // Assert 断言， 抛出异常
        Assert.notNull(blog, "该博客已被删除");

        return Result.succ(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {
//        Assert.isTrue(false, "公开版不能任意编辑！");

        Blog temp = null;

        // blog 已存在
        if(blog.getId() != null) {
            // 获取已存在的 Blog 实例
            temp = blogService.getById(blog.getId());

            System.out.println(ShiroUtil.getProfile().getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");

        }
        // blog 不存在
        else {
            // 新建 Blog 实例
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        // 将 blog（更新或新建的 Blog 实例） 复制到 temp（真正用于操作的实例）
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");

        // 执行操作
        blogService.saveOrUpdate(temp);

        return Result.succ(null);
    }
}