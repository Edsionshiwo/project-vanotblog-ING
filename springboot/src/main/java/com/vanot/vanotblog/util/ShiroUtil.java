package com.vanot.vanotblog.util;

import com.vanot.vanotblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;


public class ShiroUtil {
    public static AccountProfile getProfile() {
        /** 抽象方法，定义一个静态属性，三个静态方法
         * 静态属性:
         *      @securityManager
         *          securityManager 负责管理所有的 Subject
         * 静态方法:
         *      @getSubject 获取 Subject
         *      ...
         */
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}