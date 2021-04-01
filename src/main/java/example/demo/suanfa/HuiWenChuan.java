package example.demo.suanfa;

/**
 * 最长回文串
 *
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 在构造过程中，请注意区分大小写。比如"Aa"不能当做一个回文字符串。注 意:假设字符串的长度不会超过 1010。
 *
 * @author lr
 * @date 2020/11/9
 */
public class HuiWenChuan {

    public static void main(String[] args) {
        System.out.println(fun("abccccdd"));
    }

    /**
     * 遍历字符串，回文是成对的，因此查找这个字符后面的相等的字符，如果存在，把这两个字符置为特殊字符。
     * 后序遍历跳过。
     * 因为回文中间字符可以为单个，因此剩下的字符中有单个的可以
     * @param str
     * @return
     */
    public static int fun(String str) {
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                for (int j = i + 1; j < chars.length; j++) {
                    if (chars[i] == chars[j]) {
                        count++;
                        chars[i] = ' ';
                        chars[j] = ' ';
                        break;
                    }
                }
            }
        }
        count *= 2;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                count++;
                break;
            }
        }
        return count;
    }

}
