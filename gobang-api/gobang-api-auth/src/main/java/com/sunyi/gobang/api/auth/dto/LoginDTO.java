package com.sunyi.gobang.api.auth.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Sunyi
 * @date 2023/4/8
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
