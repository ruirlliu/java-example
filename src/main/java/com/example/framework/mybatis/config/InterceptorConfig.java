package com.example.framework.mybatis.config;

import com.example.framework.mybatis.interceptor.TestInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lr
 * @date 2021/3/19
 */
@Configuration(proxyBeanMethods = false)
public class InterceptorConfig {

    @Bean
    public Interceptor interceptor() {
        return new TestInterceptor();
    }

}
