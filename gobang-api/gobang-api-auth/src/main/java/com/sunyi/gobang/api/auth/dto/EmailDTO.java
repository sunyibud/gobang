package com.sunyi.gobang.api.auth.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * @author Sunyi
 * @date 2023/4/7
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 需要发送验证码的邮箱地址
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;
}
