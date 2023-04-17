package com.sunyi.gobang.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunyi.gobang.api.auth.dto.TokenDTO;
import com.sunyi.gobang.common.response.ServerResponseEntity;
import com.sunyi.gobang.gateway.feign.AuthTokenVerifyFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 判断请求是否需要授权，如果要就校验请求携带的token
 * @author Sunyi
 * @date 2023/4/8
 */
@Component
public class TokenCheckFilter implements GlobalFilter, Ordered
{
    @Autowired
    AuthTokenVerifyFeign authTokenVerifyFeign;

    public static final List<String> ALLOW_URL = Arrays.asList(
        "/auth/emailCaptcha","/user/register","/auth/login"
    );
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        if(ALLOW_URL.contains(path)||path.contains("/ws"))
            return chain.filter(exchange);
        HttpHeaders headers = request.getHeaders();
        List<String> authorization = headers.get("Authorization");
        //Authorization可能有多个(充分为前端搽屁股~前端竟是我自己^_^)
        if(!CollectionUtils.isEmpty(authorization))
        {
            String token = authorization.get(0);
            ServerResponseEntity<Boolean> res = authTokenVerifyFeign.
                    tokenVerify(TokenDTO.builder().token(token).build());
            Boolean access = res.getData();
            if (access)
                return chain.filter(exchange);
        }
        //拦截
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("content-type", "application/json;charset=utf-8");
        HashMap<String, Object> map = new HashMap<>(4);
        //返回401
        map.put("code", 4);
        map.put("msg", "Unauthorized");
        map.put("data", null);
        map.put("success", false);
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = new byte[0];
        try
        {
            bytes = objectMapper.writeValueAsBytes(map);
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        DataBuffer wrap = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(wrap));
    }

    @Override
    public int getOrder()
    {
        return 1;
    }
}
