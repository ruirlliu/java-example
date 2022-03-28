package example.algorithm.sort.simple;

import example.algorithm.sort.SortUtils;

import java.util.Arrays;

import static example.algorithm.sort.SortUtils.swap;

/**
 * 冒泡排序
 * 空间复杂度 O(1)
 * 时间复杂度 如果列表有序，为O(n)，否则 O(n²)
 * 稳定的排序
 */
public class BubbleSort {

	public static void sort(int[] a) {
		doSort(a, 0, a.length - 1);
	}

	public static void doSort(int[] a, int low, int high) {
		boolean change = true;
		for (int i = low + 1; i < high && change; i++ ) {
			change = false;
			for (int j = low; j < high - low; j++) {
				if (a[j] > a[j + 1]) {
					swap(a, j, j + 1);
					change = true;
				}
			}
		}
	}


	public static void main(String[] args) {
		int[] ints = SortUtils.randomNumber(20);
		int[] copy = Arrays.copyOf(ints, ints.length);
		System.out.println(Arrays.toString(ints));
		System.out.println("***********");
		sort(ints);
		System.out.println(Arrays.toString(ints));
		Arrays.sort(copy);
		System.out.println(Arrays.toString(copy));
	}
}
