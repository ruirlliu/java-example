package example.algorithm.search;

import java.util.Arrays;

public class BinarySearchDemo {

	public static void main(String[] args) {
		int[] nums = new int[] {8,7,6,5,4,3,2,1};
		int i = Arrays.binarySearch(nums, 6);
		System.out.println(i);

		int[] bound = new BinarySearchDemo().bound(new int[] { 5, 7, 7, 8, 8, 10 }, 8);
		System.out.println(Arrays.toString(bound));
	}

	public int[] bound(int[] nums, int target) {
		int[] res = new int[2];
		int low = 0;
		int high = nums.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] == target) {
				high = mid - 1;
			} else if (nums[mid] > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		res[0] = nums[low] == target ? low : -1;

		low = 0;
		high = nums.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] == target) {
				low = mid + 1;
			} else if (nums[mid] > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		res[1] = nums[high] == target ? high : -1;
		return res;
	}

}
