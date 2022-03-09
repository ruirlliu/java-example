package example.jdk.map;

import example.jdk.collection.map.HashMap_1_7;

import java.util.HashMap;

/**
 * @author lr
 * @date 2021/3/30
 */
class HashMap_1_7Test {

    public static void main(String[] args) {

        testGet();

    }

    /**
     * HashMap 如果hash冲突，会采用链表结构存储（jdk8增加红黑树）
     * 但是jdk7采用前插法，jdk8采用后插法
     */
    static void testGet() {
        HashMap_1_7<Object, Object> map7 = new HashMap_1_7<>(8);
        map7.put(1, 1);
        map7.put(9, 1);
        map7.put(17, 1);
        map7.put(25, 1);

        System.out.println("jdk7...");
        map7.forEach((x, y) -> System.out.println(x));

        HashMap<Object, Object> map8 = new HashMap<>(8);
        map8.put(1, 1);
        map8.put(9, 1);
        map8.put(17, 1);
        map8.put(25, 1);
        System.out.println("jdk8...");
        map8.forEach((x, y) -> System.out.println(x));
    }

}