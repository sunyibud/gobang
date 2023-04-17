//package com.sunyi.gobang.gateway.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.RequestPath;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.util.UriComponentsBuilder;
//import reactor.core.publisher.Mono;
//
//import java.net.URI;
//
///**
// * @author Sunyi
// * @date 2023/4/9
// */
//@Component
//public class MyGlobalFilter implements GlobalFilter, Ordered
//{
//    private static final String API_PREFIX = "/api";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
//    {
//        ServerHttpRequest request = exchange.getRequest();
//        String path = request.getPath().toString();
//        URI newUri = UriComponentsBuilder.fromUri(request.getURI())
//                .replacePath(path.replaceFirst(API_PREFIX,""))
//                .build(true).toUri();
//        ServerHttpRequest newRequest = request.mutate().uri(newUri).build();
//        return chain.filter(exchange.mutate().request(newRequest).build());
//    }
//
//    /**
//     * 越小越先执行
//     * @return int
//     */
//    @Override
//    public int getOrder()
//    {
//        return 0;
//    }
//}
