package com.example.demo.suanfa;

/**
 * @author lr
 * @date 2020/11/18
 */
public class Test {

    static {
        System.out.println("test11");
    }

    public Test(String s) throws InterruptedException {
        this.wait();
    }

    {
        System.out.println(this.toString());
        System.out.println("test");
    }

    @Override
    public String toString() {
        return "toString()";
    }
}
