package com.sunyi.gobang.api.user.vo;

import com.sunyi.gobang.common.vo.BaseVO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户注册返回信息vo
 * @author Sunyi
 * @date 2023/4/7
 */
@Data
@Builder
@ToString
public class UserRegisterVO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String username;
}
