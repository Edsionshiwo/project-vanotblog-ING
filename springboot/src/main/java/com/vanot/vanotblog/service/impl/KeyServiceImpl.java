package com.vanot.vanotblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vanot.vanotblog.entity.Key;
import com.vanot.vanotblog.mapper.KeyMapper;
import com.vanot.vanotblog.service.KeyService;
import org.springframework.stereotype.Service;

@Service
public class KeyServiceImpl extends ServiceImpl<KeyMapper, Key> implements KeyService {

}
