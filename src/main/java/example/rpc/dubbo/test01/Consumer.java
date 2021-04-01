package example.rpc.dubbo.test01;

import example.rpc.dubbo.test01.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lr
 * @date 2021/3/31
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/dubbo-demo/consumer.xml");
        context.start();
        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
        String hello = demoService.sayHello("world"); // 执行远程方法
        System.out.println( hello ); // 显示调用结果
    }
}
