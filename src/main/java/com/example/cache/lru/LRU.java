package com.example.cache.lru;

import java.util.LinkedHashMap;

/**
 * 使用LinkedHashMap实现一个符合LRU算法的数据结构，
 * 该结构最多可以缓存6个元素，但元素多余六个时，会自动删除最近最久没有被使用的元素
 * @author lr
 * @date 2021/3/11
 */
public class LRU<K,V> extends LinkedHashMap<K, V> {

    private static final long serialVersionUID = 1L;

    public LRU(int initialCapacity,
               float loadFactor,
               boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }


    /**
     * 重写LinkedHashMap中的removeEldestEntry方法，当LRU中元素多余6个时，
     *              删除最不经常使用的元素
     * @param eldest
     * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
     */
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return size() > 6;
    }

    public static void main(String[] args) {

        LRU<Character, Integer> lru = new LRU<Character, Integer>(
                16, 0.75f, true);

        String s = "abcdefghijkl";
        for (int i = 0; i < s.length(); i++) {
            lru.put(s.charAt(i), i);
        }
        System.out.println("LRU中key为h的Entry的值为： " + lru.get('h'));
        System.out.println("LRU的大小 ：" + lru.size());
        System.out.println("LRU ：" + lru);
        lru.get('g');
        System.out.println("LRU ：" + lru);

    }

}
