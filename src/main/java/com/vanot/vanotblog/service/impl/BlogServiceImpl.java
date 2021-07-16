package com.vanot.vanotblog.service.impl;

import com.vanot.vanotblog.entity.Blog;
import com.vanot.vanotblog.mapper.BlogMapper;
import com.vanot.vanotblog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vanot313
 * @since 2021-07-08
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
