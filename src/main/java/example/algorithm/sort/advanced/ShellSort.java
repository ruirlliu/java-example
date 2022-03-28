package example.algorithm.sort.advanced;

import example.algorithm.sort.SortUtils;

import java.util.Arrays;

import static example.algorithm.sort.SortUtils.swap;

/**
 * 希尔排序，插入排序的优化版
 * 空间复杂度 O(1)
 * 时间复杂度 O(logN);
 * 不稳定的排序
 */
public class ShellSort {

	public static void sort(int[] a) {
		for (int gap = a.length >> 1; gap > 0; gap = gap >> 2) {
			for (int i = gap; i < a.length; i++) {
				for (int j = i; j >= gap && a[j] < a[j - gap]; j = j - gap) {
					swap(a, j, j - gap);
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] ints = SortUtils.randomNumber(1000);
		int[] copy = Arrays.copyOf(ints, ints.length);
		System.out.println("first : " + Arrays.equals(ints, copy));
		sort(ints);
		System.out.println("second : " + Arrays.equals(ints, copy));
		System.out.println(Arrays.toString(ints));
		Arrays.sort(copy);
		System.out.println("last : " + Arrays.equals(ints, copy));
	}
}
