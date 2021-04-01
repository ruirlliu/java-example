package example.jdk;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author lr
 * @date 2020/9/18
 */
public class StreamTest {


    public static void main(String[] args) {

        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        ints.stream().forEach(i -> {
            if (i.intValue() % 2 == 0) {
                System.out.println("i is even");
            } else {
                System.out.println("i is old");
            }
        });

        Stream<Integer> evenIntegers = ints.stream().filter(i -> i.intValue() % 2 == 0);
        Stream<Integer> oddIntegers = ints.stream().filter(i -> i.intValue() % 2 != 0);

        evenIntegers.forEach(i -> System.out.println("i is even"));
        oddIntegers.forEach(i -> System.out.println("i is old"));


        System.out.println(ints);
        final int size = ints.size();
        //Collections.reverse();
        //反转数组
        for (int i = 0; i < size / 2 ; i++) {
            ints.set(i, ints.set(size - 1 - i, ints.get(i)));
        }
        System.out.println(ints);
    }


}
