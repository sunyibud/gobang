package com.sunyi.gobang.api.user.vo;

import lombok.*;

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
public class UserInfoVO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private long userId;

    private String username;

    private String sex;

    private String email;

    private String headPath;

    private int level;
}
