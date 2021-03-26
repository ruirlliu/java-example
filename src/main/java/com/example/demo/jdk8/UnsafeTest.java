package com.example.demo.jdk8;

import sun.misc.VM;

import java.util.HashMap;

/**
 * @author liurui
 * @date 2020/9/16
 */
public class UnsafeTest {
//    private static final sun.misc.Unsafe U = Unsafe.getUnsafe();

    public static void main(String[] args) {
        ClassLoader classLoader = UnsafeTest.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(VM.isSystemDomainLoader(classLoader));
        ClassLoader classLoader1 = HashMap.class.getClassLoader();
        System.out.println(classLoader1);
        System.out.println(VM.isSystemDomainLoader(classLoader1));

        System.out.println(Thread.currentThread().toString());
        Thread.dumpStack();

        System.out.println(1 + 2 + "3" + null);
            System.out.println((int)'a');

            int i =10;
            int j = i;
            --i;
        System.out.println(i);
        System.out.println(j);
    }
}
