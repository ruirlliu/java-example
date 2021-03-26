package com;

/**
 * @author lr
 * @date 2021/3/17
 */
public class StringTest {

    public static void main(String[] args) {

        String str = "classpath*:/mapper/**";

        String substring = str.substring("classpath*:".length());
        System.out.println(substring);

    }

}
