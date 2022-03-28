package example.algorithm.sort.simple;

import example.algorithm.sort.SortUtils;

import java.util.Arrays;

import static example.algorithm.sort.SortUtils.swap;

/**
 * 直接插入排序
 * 空间复杂度 O(1)
 * 时间复杂度 O(n²)
 * 稳定的排序
 */
public class InsertionSort {

	public static void sort(int[] a) {
		doSort(a, 0, a.length - 1);
	}

	public static void doSort(int[] a, int low, int high) {
		for (int i = low + 1; i <= high; i++ ) {
			for (int j = i; j > 0 && a[j] < a[j-1]; j--) {
				swap(a, j, j - 1);
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
