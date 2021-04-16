package com.spring.boot.start;

import org.springframework.core.io.ResourceLoader;

/**
 * @author lr
 * @date 2021/4/6
 * @see org.springframework.boot.SpringApplication#SpringApplication(ResourceLoader, Class[])
 */
public class Exception {

    public static void main(String[] args) {

        StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            System.out.println("*** " + stackTraceElement.toString());
            if ("main".equals(stackTraceElement.getMethodName())) {
                System.out.println(stackTraceElement.getClassName());
            }
        }
    }

}
