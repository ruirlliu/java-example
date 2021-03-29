package com.example.demo.suanfa;

/**
 * 验证回文串
 *
 * 注意 "0P"
 *
 * @author lr
 * @date 2020/11/9
 */
public class VerifyReturnStr {

    public static void main(String[] args) {
//        System.out.println(Character.toUpperCase('0'));
//        System.out.println(Character.toUpperCase('A'));
//        System.out.println(Character.toUpperCase('a'));
//        System.out.println(verify("A man, a plan, a canal: Panama"));
//        System.out.println(verify("race a car"));
        System.out.println(verify("0P"));
    }
    public static boolean verify(String s) {
        int i=0, j = s.length() - 1;
        while (i < j) {
            if (!isLetterOrNumber(s.charAt(i))) {
                i++;
                continue;
            }
            if (!isLetterOrNumber(s.charAt(j))) {
                j--;
                continue;
            }
            if (Character.toUpperCase(s.charAt(i++)) != Character.toUpperCase(s.charAt(j--))) {
                return false;
            }
        }
        return true;
    }
    public static boolean isLetterOrNumber(char c) {
        return  (c >= '0' && c <= '9') ||
                (c >= 'A' && c <= 'Z') ||
                (c >= 'a' && c <= 'z');
    }

}
