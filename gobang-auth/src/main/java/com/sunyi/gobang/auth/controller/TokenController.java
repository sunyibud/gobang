package com.sunyi.gobang.auth.controller;

import com.sunyi.gobang.api.auth.dto.TokenDTO;
import com.sunyi.gobang.common.util.JwtUtil;
import com.sunyi.gobang.common.response.ServerResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Sunyi
 * @date 2023/4/8
 */
@RestController
public class TokenController
{
    @PostMapping("/auth/tokenVerify")
    public ServerResponseEntity<Boolean> tokenVerify(@RequestBody TokenDTO param)
    {
        String token = param.getToken();
        if(StringUtils.hasText(token))
        {
            //约定好的有前缀的 bearer token
            String realToken = token.replaceFirst("bearer ", "");
            if(JwtUtil.getClaimByToken(realToken)!=null
                    &&!JwtUtil.isTokenExpired(Objects.requireNonNull(JwtUtil.getClaimByToken(realToken))))
            {
                return ServerResponseEntity.success(true);
            }
        }
        return ServerResponseEntity.success(false);
    }
}
