package com.example.demo.jdk8.util;

import java.time.ZoneId;

/**
 * @author liurui
 * @date 2020/10/30
 */
public class ZoneIdDemo {

    public static void main(String[] args) {
        for (String availableZoneId : ZoneId.getAvailableZoneIds()) {
            System.out.println(availableZoneId);
        }
        System.out.println(ZoneId.systemDefault());
    }
}
