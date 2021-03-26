package com.example.jdk.proxy.jdk;

/**
 * @author liurui
 * @date 2020/11/3
 */
public class MyHello implements IHello {
    @Override
    public String say() {
        return "Hello World!";
    }
}
