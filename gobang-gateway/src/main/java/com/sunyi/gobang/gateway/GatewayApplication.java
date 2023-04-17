package com.sunyi.gobang.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Sunyi
 * @date 2023/4/8
 */
@SpringBootApplication(scanBasePackages = { "com.sunyi.gobang" })
@EnableDiscoveryClient
@EnableFeignClients
public class GatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
