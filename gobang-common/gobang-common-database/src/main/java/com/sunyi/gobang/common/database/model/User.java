package com.sunyi.gobang.common.database.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * 用户表
 * @author Sunyi
 * @date 2023/4/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User extends BaseModel implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像路径
     */
    private String headPath;

    /**
     * 段位等级
     */
    private int level;
}
