package example.jdk.jvm;

/**
 * @author lr
 * @date 2021/3/1
 */
public class SystemTest {

    public static void main(String[] args) {
        long l = System.nanoTime();
        long l1 = System.currentTimeMillis();
        System.out.println(l);
        System.out.println(l1);
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
    }

}
