package example;

import example.rpc.dubbo.test02.service.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = "example.framework.mybatis.dao")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // 实现 Dubbo 服务消费方
    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private DemoService demoService;


    @Bean
    public ApplicationRunner runner() {
        return args -> System.out.println(demoService.sayHello("mercyblitz"));
    }
}
