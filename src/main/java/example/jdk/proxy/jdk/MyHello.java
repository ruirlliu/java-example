package example.jdk.proxy.jdk;

/**
 * @author lr
 * @date 2020/11/3
 */
public class MyHello implements IHello {
    @Override
    public String say() {
        return "Hello World!";
    }

    @Override
    public String originSay() {
        System.out.println(" this say :: " + say());
        return "Origin Hello World!";
    }
}
