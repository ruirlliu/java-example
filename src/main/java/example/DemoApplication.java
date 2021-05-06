package example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

@SpringBootApplication
@MapperScan(basePackages = "example.framework.mybatis.dao")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
//        springApplication.setApplicationStartup(new BufferingApplicationStartup(1000));
        springApplication.run(args);
//        SpringApplication.run(DemoApplication.class, args);
    }

}
