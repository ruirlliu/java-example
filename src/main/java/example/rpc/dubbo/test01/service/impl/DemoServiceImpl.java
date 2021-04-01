package example.rpc.dubbo.test01.service.impl;

import example.rpc.dubbo.test01.service.DemoService;

/**
 * @author lr
 * @date 2021/3/31
 */
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
