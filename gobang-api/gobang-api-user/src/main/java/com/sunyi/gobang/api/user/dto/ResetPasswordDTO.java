package com.sunyi.gobang.api.user.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Sunyi
 * @date 2023/4/9
 */

@Data
@ToString
public class ResetPasswordDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "字段不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    @NotBlank(message = "字段不能为空")
    private String key;

    @NotBlank(message = "字段不能为空")
    private String emailCode;

    @NotBlank(message = "字段不能为空")
    private String newPassword;
}
