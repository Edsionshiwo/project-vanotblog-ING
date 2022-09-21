package com.vanot.vanotblog.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.vanot.vanotblog.entity.User;
import com.vanot.vanotblog.service.UserService;
import com.vanot.vanotblog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 自定义 Realm 配置认证规则
 */
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    /**
     * 权限验证重载
     * 执行 Subject 权限验证时被调用  Security.getSubject().isPermitted()
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 登录验证重载
     * 执行 Subject 登录操作时被调用  Security.getSubject().login()
     * 登录成功后通过 Security.getSubject().isAuthenticated() 查询登陆状态
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 获取 JwtToken 中的有效信息，提取 userId
        JwtToken jwtToken = (JwtToken) token;
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();


        User user = userService.getById(Long.valueOf(userId));
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }
        if (user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定");
        }

        // 将 user 中的数据复制迁移到 profile
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user, profile);

        // getName() 传递当前 Realm 名称
        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}
