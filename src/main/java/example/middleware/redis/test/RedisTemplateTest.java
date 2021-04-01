package example.middleware.redis.test;

import example.DemoApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

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
