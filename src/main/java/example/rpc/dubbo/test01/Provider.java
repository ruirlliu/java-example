package example.rpc.dubbo.test01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lr
 * @date 2021/3/31
 */
public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/dubbo-demo/provider.xml");
        context.start();
        System.in.read(); // 按任意键退出
    }
}
