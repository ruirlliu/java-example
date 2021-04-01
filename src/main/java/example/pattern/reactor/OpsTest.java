package example.pattern.reactor;

/**
 * @author lr
 * @date 2021/3/19
 */
public class OpsTest {

    private static final int one = 1;
    private static final int two = 2;
    private static final int three = 4;

    private static final int sum = one | two | three;


    public static void main(String[] args) {

        System.out.println(Long.numberOfLeadingZeros(0));

        System.out.println(8 & ~7);

        System.out.println("valid: " + valid(8));
        System.out.println("valid: " + valid(2));
    }

    public static boolean valid(int op) {
        int cal = op & ~sum;
        System.out.println(cal);
        return cal == 0;
    }

}
