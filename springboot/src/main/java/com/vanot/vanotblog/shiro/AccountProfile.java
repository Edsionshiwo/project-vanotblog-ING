package com.vanot.vanotblog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * Shiro 框架概念中的 Subject
 * 将通过 ShiroUtil 获取
 * 在 AccountRealm 中被注册
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;
    private String username;
    private String avatar;
    private String email;
}
