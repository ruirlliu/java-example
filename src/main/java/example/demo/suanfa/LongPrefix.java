package example.demo.suanfa;

import java.util.Arrays;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * @author lr
 * @date 2020/11/9
 */
public class LongPrefix {

    public static void main(String[] args) {
        Arrays.sort(args);
        String str1 = "hflower";
        String str2 = "flow";
        String str3 = "fflight";
        int i = 0;
        while (i < str1.length() && i < str2.length() && i < str3.length()) {
            if (str1.charAt(i) == str2.charAt(i) && str3.charAt(i) == str2.charAt(i) ) {
                i++;
            } else break;
        }
        System.out.println(str1.substring(0, i));
    }
}
