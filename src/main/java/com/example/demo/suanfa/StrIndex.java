package com.example.demo.suanfa;

/**
 * 查找字符串
 * @author liurui
 * @date 2020/11/6
 */
public class StrIndex {

    public static void main(String[] args) {
        String str = "test0awgaweahgbashaweryweryh464er89ry49asdgawegahdbasdbashshdasdawewehdfnb ade5rh1e65rh116";
        String test = "shawe";
        char[] source = str.toCharArray();
        char[] target = test.toCharArray();

        System.out.println(str.indexOf(test));
        System.out.println(index1(source, target));
        System.out.println(index2(source, target));
    }

    public static int index1(char[] source, char[] target) {
        int cursor;
        flag:
        for (int i = 0; i < source.length; i++) {
            cursor = i;
            for (int j = 0; j < target.length; j++) {
                if (source[cursor++] != target[j]) {
                    continue flag;
                }
            }
            return i;
        }
        return -1;
    }

    public static int index2(char[] source, char[] target) {
        int i = 0,j = 0;
        while (i < source.length && j < target.length) {
            if (source[i++] != target[j++]) {
                i = i - j + 1;
                j = 0;
            }
        }
        return j == target.length ? i - j : -1;
    }
}
