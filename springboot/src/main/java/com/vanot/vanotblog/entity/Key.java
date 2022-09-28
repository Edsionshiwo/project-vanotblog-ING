package com.vanot.vanotblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_key")
public class Key implements Serializable {
    // 序列化与反序列化时用于核验的版本号
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    @NotBlank(message = "密钥不为空")
    String keyvalue;
    Integer type;
}