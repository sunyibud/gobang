package com.sunyi.gobang.api.auth.dto;

import lombok.*;

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
public class TokenDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String token;
}
