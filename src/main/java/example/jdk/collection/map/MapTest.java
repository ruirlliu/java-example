package example.jdk.collection.map;

/**
 * @author lr
 * @date 2021/3/23
 */
public class MapTest {

    public static void main(String[] args) {
        System.out.println(5.1 % 4.8);
        testIndex(1);
        System.out.println("**********");
        testIndex(10);
        System.out.println("**********");
        testIndex(25);
        System.out.println("**********");
        testIndex(19);
        System.out.println("**********");
    }

    static void testIndex(Object o) {

        int length = 1 << 4;
        System.out.println("and: " + (o.hashCode() & (length - 1)));
        System.out.println("mod: " + (o.hashCode() % length));
    }

}
