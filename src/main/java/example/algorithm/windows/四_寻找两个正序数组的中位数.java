package example.algorithm.windows;

/**
 * 描述:4. 寻找两个正序数组的中位数
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2022/10/28 14:39
 */
class FindMedianSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int len1 = nums1.length, len2 = nums2.length;
		int totalLen = len1 + len2;
		if (totalLen % 2 == 1) {
			return findKthNum(nums1, nums2, totalLen / 2 + 1);
		} else {
			return (findKthNum(nums1, nums2, totalLen / 2) + findKthNum(nums1, nums2, totalLen / 2 + 1)) / 2d;
		}
	}

	// 找到第k大个数
	private int findKthNum(int[] nums1, int[] nums2, int k) {
		int len1 = nums1.length, len2 = nums2.length;
		int start1 = 0, start2 = 0;
		while (true) {
			if (start1 >= len1) {
				return nums2[start2 + k - 1];
			}
			if (start2 >= len2) {
				return nums1[start1 + k - 1];
			}
			if (k == 1) {
				return Math.min(nums1[start1], nums2[start2]);
			}
			int half = k / 2;
			int index1 = Math.min(len1, start1 + half) - 1;
			int index2 = Math.min(len1, start2 + half) - 1;
			if (nums1[len1] < nums2[len2]) {
				k -= (index1 - start1 + 1);
				start1 = index1 + 1;
			} else {
				k -= (index2 - start2 + 1);
				start2 = index2 + 1;
			}
		}
	}

	public static void main(String[] args) {

	}
}
