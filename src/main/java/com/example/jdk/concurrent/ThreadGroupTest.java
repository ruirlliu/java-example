package com.example.jdk.concurrent;

/**
 * @author liurui
 * @date 2020/9/17
 */
public class ThreadGroupTest {


    // 线程组统一异常处理
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("test");
        ThreadGroup threadGroup1 = new ThreadGroup("group1") {
            // 继承ThreadGroup并重新定义以下方法
            // 在线程成员抛出unchecked exception
            // 会执行此方法
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + ": " + e.getMessage());
            }
        };

        // 这个线程是threadGroup1的一员
        Thread thread1 = new Thread(threadGroup1, () -> {
            // 抛出unchecked异常
            throw new RuntimeException("测试异常");
        });

        thread1.start();
    }

}
