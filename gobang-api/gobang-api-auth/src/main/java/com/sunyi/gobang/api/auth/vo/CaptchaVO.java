package com.sunyi.gobang.api.auth.vo;

import com.sunyi.gobang.common.vo.BaseVO;
import lombok.*;

import java.io.Serializable;

/**
 * @author Sunyi
 * @date 2023/4/7
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaptchaVO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 该条验证码的key
     */
    private String key;
}
