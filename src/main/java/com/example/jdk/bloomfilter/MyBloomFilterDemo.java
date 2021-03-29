package com.example.jdk.bloomfilter;

import java.util.BitSet;
import java.util.Objects;

/**
 * @author lr
 * @date 2020/11/4
 */
public class MyBloomFilterDemo {

    private int[] SEEDS = new int[]{3, 5, 7, 11, 13, 17};

    private int LEN = 2 << 25;

    private HashFunction[] func = new HashFunction[SEEDS.length];


    private BitSet set = new BitSet();

    public MyBloomFilterDemo() {
        for (int i = 0; i< SEEDS.length; i++) {
            func[i] = new HashFunction(LEN, SEEDS[i]);
        }
    }

    public void add(Object value) {
        for (int i = 0; i< SEEDS.length; i++) {
            set.set(func[i].hash(value));
        }
    }

    public boolean contain(Object value) {
        for (int i = 0; i< SEEDS.length; i++) {
            if (!set.get(func[i].hash(value))) {
                return false;
            }
        }
        return true;
    }



    static class HashFunction {

        private final int len;

        private final int seed;

        public HashFunction(int len, int seed) {
            this.len = len;
            this.seed = seed;
        }

        public int hash(Object value) {
            return Objects.hash(len, seed, value);
        }

    }


}
