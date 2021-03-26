package com.example.demo.jdk8.proxy.cglib;

/**
 * @author liurui
 * @date 2020/11/3
 */
public class SmsService {

    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }

}
