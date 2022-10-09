package com.vanot.vanotblog.util;

import com.vanot.vanotblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;


/**
 * shiro 工具
 * 用于获取当前 Subject 标识
 */
public class ShiroUtil {
    public static AccountProfile getProfile() {
        /*
         * SecurityUtils
         * 抽象方法，定义一个静态属性，三个静态方法
         * 静态属性:
         *      @securityManager
         *          securityManager 负责管理所有的 Subject
         * 静态方法:
         *      @getSubject 获取 Subject
         *      ...
         */

        /*
         * 获取 Subject 的唯一标识，即 Key
         */
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}