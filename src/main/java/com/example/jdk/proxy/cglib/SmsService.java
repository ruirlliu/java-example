package com.example.jdk.proxy.cglib;

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
