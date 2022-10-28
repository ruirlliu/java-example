package example.algorithm.windows;

import java.util.HashMap;
import java.util.Map;

/**
 * 最小覆盖子串
 *
 * @author LR
 * @date 2022/9/8 10:31
 */
public class MinWindow {

	public String minWindow(String s, String t) {
		int sLen = s.length(), tLen = t.length();
		if (sLen < tLen) {
			return "";
		}
		Map<Character, Integer> need = new HashMap<>();
		for (char c : t.toCharArray()) {
			need.put(c, need.getOrDefault(c, 0) + 1);
		}
		Map<Character, Integer> have = new HashMap<>();
		int left = 0, right = 0, valid = 0;
		int start = -1, length = 0;
		while (right < sLen) {
			char rChar = s.charAt(right);
			if (need.containsKey(rChar)) {
				have.put(rChar, have.getOrDefault(rChar, 0) + 1);
				if (have.get(rChar).equals(need.get(rChar))) {
					valid++;
				}
			}
			// 缩小窗口
			while (left <= right && valid == tLen) {
				int newLen = right - left + 1;
				if (newLen > length) {
					start = left;
					length = newLen;
				}
				char lChar = s.charAt(left);
				if (have.containsKey(lChar)) {
					int count = have.get(lChar);
					have.put(lChar, count - 1);
					if (count == need.get(lChar)) {
						valid--;
					}
				}
				left++;
			}
			right++;
		}
		return start >= 0 ? s.substring(start, start + length) : "";
	}

	public static void main(String[] args) {
		MinWindow minWindow = new MinWindow();
		System.out.println(minWindow.minWindow("AOBC", "ABC"));
	}
}
