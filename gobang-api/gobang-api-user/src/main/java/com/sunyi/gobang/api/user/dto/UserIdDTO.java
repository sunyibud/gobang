package com.sunyi.gobang.api.user.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Sunyi
 * @date 2023/4/9
 */
@Data
@ToString
public class UserIdDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private long userId;
}
