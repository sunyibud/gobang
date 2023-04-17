package com.sunyi.gobang.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Sunyi
 * @date 2023/4/6
 */
@SpringBootApplication(scanBasePackages = { "com.sunyi.gobang" })
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.sunyi.gobang.common.database.mapper")
public class UserApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(UserApplication.class, args);
    }
}
