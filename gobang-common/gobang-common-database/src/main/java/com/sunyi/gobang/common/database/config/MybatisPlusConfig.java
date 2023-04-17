package com.sunyi.gobang.common.database.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.sunyi.gobang.common.database.handler.MyMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sunyi
 * @date 2023/4/8
 */
@Configuration
@MapperScan("com.sunyi.gobang.api")
public class MybatisPlusConfig
{
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor()
    {
        //分页插件
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //防全表更新插件
//        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return new MybatisPlusInterceptor();
    }

//    /**
//     * 自动填充功能（createTime和updateTime)
//     * @return 自定义的实现类
//     */
//    @Bean
//    public MetaObjectHandler metaObjectHandler() {
//        return new MyMetaObjectHandler();
//    }
}
