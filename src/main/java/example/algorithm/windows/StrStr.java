package example.algorithm.windows;

/**
 * 实现strStr<br>
 *
 * @author LR<br>
 * @date 2022/9/29 17:02
 */
public class StrStr {
	public int strStr(String haystack, String needle) {
		int len1 = haystack.length();
		int len2 = needle.length();
		if (len1 < len2) {
			return -1;
		}
		int MOD = 1658598167;
		int R = 26;
		int RL = 1;
		for (int i = 1, l = len2 - 1; i <= l; i++) {
			// 计算过程中不断求模，避免溢出
			RL = (RL * R) % MOD;
		}

		int needHash = 0;
		for (int i = 0; i < len2; i++) {
			needHash = (needHash * R + needle.charAt(i)) % MOD;
		}

		int left = 0, right = 0;
		int haveHash = 0;
		while (right < len1) {
			char r = haystack.charAt(right++);
			haveHash = (haveHash * R + r) % MOD;
			if (right - left == len2) {
				if (haveHash == needHash) {
					// 存在Hash冲突导致Hash相等，但是结果不同，所以需要再对比
					if (haystack.substring(left, right).equals(needle)) {
						return left;
					}
				}
				int l = haystack.charAt(left++);
				haveHash = (haveHash - l * RL) % MOD;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		StrStr strStr = new StrStr();
		System.out.println(strStr.strStr("sadbutsad", "sad"));
		System.out.println(strStr.strStr("leetcode", "leeto"));
	}
}
