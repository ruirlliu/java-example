package com.example.demo.suanfa;

/**
 * 替换空格
 * @author lr
 * @date 2020/11/9
 */
public class EmptyReplace {

    public static void main(String[] args) {
        String str = "nihao ag asg ";
        int length = str.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                result.append("%20");
            } else {
                result.append(c);
            }
        }
        System.out.println(result.toString());
    }

}
