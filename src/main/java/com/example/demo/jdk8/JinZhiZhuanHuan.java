package com.example.demo.jdk8;

/**
 * 进制转换：十进制 转 二进制
 * @author liuruiZ
 * @date 2020/11/2
 */
public class JinZhiZhuanHuan {

    public static void main(String[] args) {

        int num = 46164891;
        System.out.println("正整数");
        System.out.println(Integer.toBinaryString(num));
        String s = positiveNum(num);
        System.out.println(s);

        System.out.println("负整数");
        System.out.println(Integer.toBinaryString(-num));
        String s1 = negativeNum(-num);
        System.out.println(s1);

        System.out.println("**********");
        int test = -99;
        int move = 1;
        System.out.println(Integer.toBinaryString(test));
        System.out.println(Integer.toBinaryString(test >> move) + "  " + (test >> move));
        System.out.println(Integer.toBinaryString(test << move)  + "  " + (test << move));
        System.out.println(Integer.toBinaryString(test >>> move) +  "  " + (test >>> move));
    }

    public static String negativeNum(int num) {
        String positiveNum = positiveNum(-num);
        char[] chars = positiveNum.toCharArray();
        int len = Integer.SIZE;
        int[] nums = new int[len];
        for (int i = 0; i < chars.length; i++) {
            nums[len - chars.length  + i] = chars[i] - '0';
        }
        for (int i = 0; i < len; i++) {
            nums[i] = Math.abs(nums[i] - 1);
        }
        int bit = 1;
        for (int i = len - 1; i >= 0; i--) {
            nums[i] = nums[i] + bit;
            bit = nums[i] >> 1;
            nums[i] = nums[i] % 2;
        }
        StringBuilder builder = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            builder.append(nums[i]);
        }
        return builder.toString();
    }

    public static String positiveNum(int num) {
        StringBuilder builder = new StringBuilder();
        while (true) {
            builder.append(num % 2);
            num = num >> 1;
            if (num < 2) {
                builder.append(num);
                break;
            }
        }
        StringBuilder reverse = builder.reverse();
        return reverse.toString();
    }

}
