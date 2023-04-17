package com.sunyi.gobang.api.auth.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author Sunyi
 * @date 2023/4/9
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TokenVO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String Authorization;
}
