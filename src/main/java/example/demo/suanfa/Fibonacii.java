package example.demo.suanfa;

/**
 * @author lr
 * @date 2020/11/9
 */
public class Fibonacii {

    public static void main(String[] args) {
        System.out.println(computeFibonacci(3));
        System.out.println(computeFibonacci(4));
        System.out.println(computeFibonacci(5));
        System.out.println(computeFibonacci(6));
        System.out.println(computeFibonacci(7));
    }

    private static int computeFibonacci(int n) {
        if (n < 1) {
            return n;
        }
        int a = 1, b = 1, tmp;
        for (int i = 3; i <=n ; i++) {
            tmp = a;
            a = b;
            b += tmp;
        }
        return b;
    }

}
