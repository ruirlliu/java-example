package example.jdk.bloomfilter;

import java.util.BitSet;
import java.util.Random;

/**
 * @author lr
 * @date 2020/11/2
 */
public class BitSetDemo {

    public static void main(String[] args) {
        BitSet bitSet = new BitSet(10);
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            bitSet.set(random.nextInt(9999));
        }
        for (int i = 0; i< bitSet.size(); i++) {
            if (bitSet.get(i)) {
                System.out.print(i);
            }
        }
    }

}
