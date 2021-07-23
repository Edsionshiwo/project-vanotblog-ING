package com.vanot.vanotblog.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Data 注解
 * 免去大量 get set 的函数
 * 提供 equal == 等方法
 */
@Data
public class LoginDto implements Serializable {

    /**
     * Validation 验证包中的注解
     * 注解的变量（具体可以用来注解什么可以看源码）不能为空，否则抛出异常
     */
    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
