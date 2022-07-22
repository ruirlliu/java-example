package example.algorithm.bcktrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 力扣第 46 题「 全排列」
 * https://leetcode-cn.com/problems/permutations/
 */
public class Permute {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new LinkedList<>();
		List<Integer> output = new ArrayList<>(nums.length);
		backTrace(nums, output, res, 0);
		return res;
	}

	public void backTrace(int[] nums, List<Integer> output, List<List<Integer>> res, int i) {
		if (i == nums.length) {
			res.add(new ArrayList<>(output));
			return;
		}
		output.add(i);
		for (int j = i + 1; j < nums.length; j++) {
			swap(nums, i, j);
			System.out.println(Arrays.toString(nums));
			backTrace(nums, output, res, i + 1);
			swap(nums, j, i);
		}
	}

	private void swap(int[] nums, int i, int j ){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		Permute permute = new Permute();
		int[] nums = { 1, 2, 3 };
		permute.permute(nums);
		System.out.println(Arrays.toString(nums));
	}
}

