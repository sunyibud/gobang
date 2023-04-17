package com.sunyi.gobang.gateway.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.reactive.socket.client.TomcatWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import org.springframework.web.reactive.socket.server.RequestUpgradeStrategy;
import org.springframework.web.reactive.socket.server.upgrade.TomcatRequestUpgradeStrategy;

import java.util.stream.Collectors;

/**
 * @author Sunyi
 * @date 2023/4/9
 */
@Configuration
public class GatewayConfig
{
    /**
     * 其实在spring-boot2.1.x版本是不用手动注入HttpMessageConverters的，因为可以自动配置的,
     * 见HttpMessageConvertersAutoConfiguration。
     * 但是在spring-boot2.2.x版本HttpMessageConvertersAutoConfiguration有所改动，
     * 加了个@Conditional(NotReactiveWebApplicationCondition.class) ，
     * 因为gateway是ReactiveWeb，所以针对HttpMessageConverters的自动配置就不生效了，
     * 故需要手动注入HttpMessageConverters
     * @param converters 1
     * @return 1
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }

    /**************解决网关转接websocket服务的问题*******************************/
    @Bean
    @Primary
    WebSocketClient tomcatWebSocketClient() {
        return new TomcatWebSocketClient();
    }
    @Bean
    @Primary
    public RequestUpgradeStrategy requestUpgradeStrategy() {
        return new TomcatRequestUpgradeStrategy();
    }
}
