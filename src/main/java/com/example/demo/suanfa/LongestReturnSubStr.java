package com.example.demo.suanfa;

/**
 * 最长回文子串
 * @author lr
 * @date 2020/11/9
 */
public class LongestReturnSubStr {

    public static void main(String[] args) {
        System.out.println(even("babad"));
    }

    private int index, len;

    public String longestPalindrome(String s) {
        if (s.length() < 2)
            return s;
        for (int i = 0; i < s.length() - 1; i++) {
            PalindromeHelper(s, i, i);
            PalindromeHelper(s, i, i + 1);
        }
        return s.substring(index, index + len);
    }

    public void PalindromeHelper(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        if (len < r - l - 1) {
            index = l + 1;
            len = r - l - 1;
        }
    }
















    public static String even(String s) {
        int len = s.length(), count, low, high, max=0;
        String str = "";
        for (int i = 1; i < len; i++) {
            count = 1;
            low = high = i;
            while (low >= 0 && high < len && s.charAt(low) == s.charAt(high)) {
                if (count > max) {
                    max = count;
                    str = s.substring(low, high + 1);
                }
                count += 2;
                low--;
                high++;
            }
        }
        return str;
    }






}
