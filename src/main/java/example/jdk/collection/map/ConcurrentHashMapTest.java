package example.jdk.collection.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lr
 * @date 2020/9/16
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "1");
    }

}
