package example.algorithm;

import java.util.Arrays;

public class Rotate {

	public int[] rotate(int[] nums, int distance) {
		int len = nums.length;
		if (len == 0) {
			return nums;
		}
		distance = distance % len;
		if (distance < 0) {
			distance += len;
		} else if (distance == 0) {
			return nums;
		}
		for (int cycleStart = 0, moved = 0; moved != len; cycleStart++) {
			int num = nums[cycleStart];
			int i = cycleStart;
			do {
				i += distance;
				if (i >= len) {
					i -= len;
				}
				int temp = nums[i];
				nums[i] = num;
				num = temp;
				moved++;
			} while (i != cycleStart);
		}
		return nums;
	}

	public static void main(String[] args) {
		Rotate solution = new Rotate();
		System.out.println(Arrays.toString(solution.rotate(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 1)));
		System.out.println(Arrays.toString(solution.rotate(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 2)));
		System.out.println(Arrays.toString(solution.rotate(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3)));
		System.out.println(Arrays.toString(solution.rotate(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 8)));
		System.out.println(Arrays.toString(solution.rotate(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 9)));
		System.out.println(Arrays.toString(solution.rotate(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 15)));
		System.out.println(Arrays.toString(solution.rotate(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, -1)));
		System.out.println(Arrays.toString(solution.rotate(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, -5)));
		System.out.println(Arrays.toString(solution.rotate(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, -10)));
	}
}
