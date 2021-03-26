package com.example.jdk.proxy.cglib;

/**
 * @author liurui
 * @date 2020/11/3
 */
public class ProxyDemo {

    public static void main(String[] args) {

        SmsService aliSmsService = (SmsService) CglibProxyFactory.getProxy(SmsService.class);
        aliSmsService.send("java");

    }

}
