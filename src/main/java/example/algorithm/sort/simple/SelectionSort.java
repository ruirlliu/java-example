package example.algorithm.sort.simple;

import example.algorithm.sort.SortUtils;

import java.util.Arrays;

import static example.algorithm.sort.SortUtils.swap;

/**
 * 选择排序
 * 空间复杂度 O(1)
 * 时间复杂度 O(n²)
 * 不稳定的排序
 */
public class SelectionSort {

	public static void sort(int[] a) {
		doSort(a, 0, a.length - 1);
	}

	public static void doSort(int[] a, int low, int high) {
		for (int i = low; i < high; i++ ) {
			int min = i;
			for (int j = i + 1; j <= high; j++) {
				if (a[min] > a[j]) {
					min = j;
				}
			}
			if (min != i) {
				swap(a, min, i);
			}
		}
	}


	public static void main(String[] args) {
		int[] ints = SortUtils.randomNumber(20);
		int[] copy = Arrays.copyOf(ints, ints.length);
		System.out.println(Arrays.toString(ints));
		sort(ints);
		Arrays.sort(copy);

		System.out.println("***********");
		System.out.println(Arrays.toString(copy));
		System.out.println(Arrays.toString(ints));
	}
}
