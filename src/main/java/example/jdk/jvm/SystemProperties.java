package example.jdk.jvm;

/**
 * @author lr
 * @date 2021/1/29
 */
public class SystemProperties {

    public static void main(String[] args) {
        System.getProperties().forEach((x,y) -> System.out.println(x + " = " + y));
    }

}
