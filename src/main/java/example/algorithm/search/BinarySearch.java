package example.algorithm.search;

/**
 * 描述: 二分查找<br>
 *
 * @author LR<br>
 * @date 2022/10/9 17:45
 */
public class BinarySearch {

	/**
	 * 二分查找。当有重复时候，返回最左侧角标
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public int left_bound(int[] nums, int target) {
		int low = 0, high = nums.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			int midVal = nums[mid];
			if (midVal < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
//			System.out.println("low: " + low + ", high: " + high);
		}
		return nums[low] == target ? low : -1;
	}

	/**
	 * 二分查找。当有重复时候，返回最右侧角标
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public int right_bound(int[] nums, int target) {
		int low = 0, high = nums.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			int midVal = nums[mid];
			if (midVal > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
//			System.out.println("low: " + low + ", high: " + high);
		}
		if (high < 0) {
			return -1;
		}
		return nums[high] == target ? high : -1;
	}

	public static void main(String[] args) {
		BinarySearch binarySearch = new BinarySearch();
		int[] nums = { 5, 7, 7, 8, 8, 8, 8, 10 };
		int target = 8;
		System.out.println(binarySearch.left_bound(nums, target));
		System.out.println(binarySearch.right_bound(nums, target));
	}

}
