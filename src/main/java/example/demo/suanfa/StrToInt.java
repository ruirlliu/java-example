package example.demo.suanfa;

/**
 * 把字符串转换成整数
 *
 * 剑指offer: 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
 * 要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
 *
 *
 * @author lr
 * @date 2020/11/9
 */
public class StrToInt {

    public static void main(String[] args) {
        System.out.println(parse("123"));
        System.out.println(parse("526"));
        System.out.println(parse("789131"));
        System.out.println(parse("798146465"));
        System.out.println(parse("-798146465"));
        System.out.println(parse("-79468"));
        System.out.println(parse("-78948954"));
    }

    public static int parse(String str) {
        int count = 0;
        char first = str.charAt(0);
        boolean negative = false;

        if (first < '0') {
            if (first == '-') {
                negative = true;
            } else if (first != '+') {
                return 0;
            }
        }
        for (int i = negative ? 1 : 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return 0;
            }
            count *= 10;
            count += c - '0';
        }
        return negative ? -count : count;
    }




}
