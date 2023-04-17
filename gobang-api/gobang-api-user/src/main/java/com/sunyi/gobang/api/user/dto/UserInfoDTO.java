package com.sunyi.gobang.api.user.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Sunyi
 * @date 2023/4/9
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户名不能为空")
    @Length(message = "名称不能超过{max}字符",max = 8)
    private String username;

    @NotBlank(message = "性别不能为空")
    private String sex;
}
