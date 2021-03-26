package com.example.jdk.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liurui
 * @date 2020/9/15
 */
public class LinkedHashMapTest {

    private static final Logger logger = LoggerFactory.getLogger(LinkedHashMapTest.class);

    public static void main(String[] args) {
        logger.info("test{}", "info");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "e");
        //new add
        map.get("1");
        map.get("2");

        for (Iterator<String> iterator = map.values().iterator(); iterator.hasNext();) {
            String name = iterator.next();
            System.out.print(name);
        }

        Map<Test, String> map1 = new HashMap<>();
//        map1.put(new Test(0), "0");
//        map1.put(new Test(1), "1");
//        map1.put(new Test(2), "2");
//        map1.put(new Test(3), "3");
        Iterator<Map.Entry<Test, String>> iterator = map1.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Test, String> next = iterator.next();
            System.out.println(next.getValue());
        }
    }

    static class Test {
        int i ;
        Test(int i) {
            this.i = i;
        }

        @Override
        public int hashCode() {
            return i + 1;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }


}
