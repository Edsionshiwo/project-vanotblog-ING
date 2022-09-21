package com.vanot.vanotblog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author vanot313
 * @since 2021-07-08
 */

/**
 * EqualsAndHashCode 注解其实包含于 Data 注解中。
 * 这里特别声明 callSuper = false 让 equals 等函数比较时不考虑父类情况
 */

/**
 * Accessors 注解控制 getter setter 的生成方式
 * chain = true 使 setter 方法返回当前实例
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    Long id;
    Long userId;
    String title;
    String description;
    String content;
    LocalDateTime created;
    Integer status;
}
