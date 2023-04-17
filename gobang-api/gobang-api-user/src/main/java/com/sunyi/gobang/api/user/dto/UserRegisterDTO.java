package com.sunyi.gobang.api.user.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Sunyi
 * @date 2023/4/7
 */
@Data
@ToString
public class UserRegisterDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名记得填哦")
    @Length(message = "名称不能超过{max}字符",max = 8)
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码需在6-20字符之间")
    private String password;

    /**
     * 性别
     */
    @NotBlank(message = "选择一个性别吧")
    private String sex;

    /**
     * 邮箱地址
     */
    @NotBlank(message = "请填好邮箱吧")
    @Email(message = "邮箱格式错误")
    private String email;

    /**
     * 发送邮箱验证码时附带的key值
     */
    private String key;

    /**
     * 邮箱验证码
     */
    @NotBlank(message = "记得输入验证码噢")
    private String emailCode;
}
