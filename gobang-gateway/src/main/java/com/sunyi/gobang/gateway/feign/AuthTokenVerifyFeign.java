package com.sunyi.gobang.gateway.feign;

import com.sunyi.gobang.api.auth.dto.TokenDTO;
import com.sunyi.gobang.common.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Sunyi
 * @date 2023/4/8
 */
@FeignClient(value = "gobang-auth")
public interface AuthTokenVerifyFeign
{
    @PostMapping("/auth/tokenVerify")
    ServerResponseEntity<Boolean> tokenVerify(@RequestBody TokenDTO param);
}
