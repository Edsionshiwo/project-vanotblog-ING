package com.vanot.vanotblog.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.vanot.vanotblog.entity.Key;
import com.vanot.vanotblog.service.KeyService;
import com.vanot.vanotblog.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Realm 根据 Shiro SecurityManager 中的身份信息 Token 获取认证
 * 自定义 Realm 配置认证规则
 */
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    KeyService keyService;

    /**
     * 权限认证
     * 执行 Subject 权限验证时被调用  Security.getSubject().isPermitted()
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 登录认证
     * 执行 Subject 登录操作时被调用  subject.login()
     * 登录成功后通过 Security.getSubject().isAuthenticated() 查询登陆状态
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("DO GET AUT");

        // 获取 JwtToken 中的有效信息，提取 keyId
        JwtToken jwtToken = (JwtToken) token;
        String keyId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        Key key = keyService.getById(Integer.valueOf(keyId));
        if (key == null) {
            throw new UnknownAccountException("密钥不能被识别");
        }

        // 将 user 中的数据复制迁移到 profile
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(key, profile);

        // getName() 传递当前 Realm 名称
        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }

    /**
     * supports 方法检测该 Realm 是否支持此 Token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

}
