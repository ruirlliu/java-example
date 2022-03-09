package example.jdk.proxy.jdk;

/**
 * @author lr
 * @date 2020/11/3
 */
public class ProxyDemo {

    public static void main(String[] args) {
        IHello proxy = (IHello) JdkProxyFactory.getProxy(new MyHello());
        System.out.println(proxy.say());

        System.out.println(proxy.originSay());

        System.out.println(proxy.getClass());
        System.out.println("******************");
        IHello proxy1 = JdkProxyFactory.getProxyUsingInterface(IHello.class);
        String say1 = proxy1.say();
        System.out.println(say1);
        System.out.println(proxy1.getClass());
    }

}
