package example.rpc.dubbo.test02.service.impl;

import example.rpc.dubbo.test02.service.DemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

/**
 * 实现Dubbo 服务提供方
 * @author lr
 * @date 2021/3/31
 */
@DubboService(version = "1.0.0")
public class DemoServiceImpl implements DemoService {

    @Value("${dubbo.application.name}")
    private String serviceName;

    public String sayHello(String name) {
        return String.format("[%s] : Hello, %s", serviceName, name);
    }

}
