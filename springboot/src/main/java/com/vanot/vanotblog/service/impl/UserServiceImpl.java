package com.vanot.vanotblog.service.impl;

import com.vanot.vanotblog.entity.User;
import com.vanot.vanotblog.mapper.UserMapper;
import com.vanot.vanotblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  User 服务实现类
 * </p>
 *
 * @author vanot313
 * @since 2021-07-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
