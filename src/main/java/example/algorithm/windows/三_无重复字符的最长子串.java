package example.algorithm.windows;

import java.util.Arrays;

/**
 * 3. 无重复字符的最长子串
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2022/10/27 20:11
 */

class Solution {
	public int lengthOfLongestSubstring(String s) {
		// 记录字符上一次出现的位置
		int[] last = new int[128];
		Arrays.fill(last, -1);

		int res = 0;
		int start = 0; // 窗口开始位置。初始为0，当遇到了一个重复的字符时，窗口的开始位置就要移到这个重复字符上次出现的位置的后一位，缩小窗口
		for (int i = 0, len = s.length(); i < len; i++) {
			int index = s.charAt(i);
			start = Math.max(start, last[index] + 1);
			res = Math.max(res, i - start + 1);
			last[index] = i;
		}

		return res;
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int res = solution.lengthOfLongestSubstring("abcabcbb");
		System.out.println(res);
	}
}


