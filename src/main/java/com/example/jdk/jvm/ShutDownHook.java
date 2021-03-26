package com.example.jdk.jvm;

/**
 * @author liurui
 * @date 2021/1/28
 */
public class ShutDownHook {


    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread(() -> System.out.println("shutdown")));

        System.out.println("test");

    }


}
