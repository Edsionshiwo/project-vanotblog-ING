package com.vanot.vanotblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vanot.vanotblog.entity.Site;
import com.vanot.vanotblog.mapper.SiteMapper;
import com.vanot.vanotblog.service.SiteService;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements SiteService {
}
