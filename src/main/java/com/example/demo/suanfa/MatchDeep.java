package com.example.demo.suanfa;

/**
 * 括号匹配深度
 * <p>
 * 空串""是一个合法的括号匹配序列
 * 如果"X"和"Y"都是合法的括号匹配序列,"XY"也是一个合法的括号匹配序列
 * 如果"X"是一个合法的括号匹配序列,那么"(X)"也是一个合法的括号匹配序列
 * 每个合法的括号序列都可以由以上规则生成。
 *
 * @author liurui
 * @date 2020/11/9
 */
public class MatchDeep {


    public static void main(String[] args) {
        System.out.println(count("(())"));
        System.out.println(count( "()()()"));
        System.out.println(count( "((()))"));
    }

    public static int count(String args) {
        int max = 1, count = 0;
        for (int i = 0; i < args.length(); i++) {
            if (args.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }
}
