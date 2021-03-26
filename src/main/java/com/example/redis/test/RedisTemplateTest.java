package com.example.redis.test;

import com.example.DemoApplication;
import com.example.redis.config.RedisTemplateConfig;
import io.netty.util.internal.SuppressJava6Requirement;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.io.Serializable;
import java.util.List;

/**
 * @author lr
 * @date 2021/3/3
 */
public class RedisTemplateTest {

    public static void main(String[] args) {

        try (AnnotationConfigServletWebServerApplicationContext context
                     = new AnnotationConfigServletWebServerApplicationContext(DemoApplication.class)) {
//        context.register(DemoApplication.class);

//        context.refresh();
//        context.setServletContext(new ApplicationContext());
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoApplication.class);
            @SuppressWarnings("unchecked")
            RedisTemplate<Object, Object> redisTemplate = (RedisTemplate<Object, Object>) context.getBean(RedisTemplate.class);
            BoundListOperations<Object, Object> listOps = redisTemplate.boundListOps("friends");
            List<Object> range = listOps.range(0, -1);
            for (Object obj : range) {
                System.out.println(obj);
            }
            context.close();
        }
    }
}
