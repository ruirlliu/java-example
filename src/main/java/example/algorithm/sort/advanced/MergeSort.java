package example.algorithm.sort.advanced;

import example.algorithm.sort.SortUtils;

import java.util.Arrays;

/**
 * 归并排序
 * 空间复杂度 O(1)
 * 时间复杂度 O(logN)
 * 稳定的排序
 */
public class MergeSort {

	public static void sort(int[] a) {
		doSort(a, 0, a.length - 1);
	}

	public static void doSort(int[] a, int low, int high) {
		if (low < high) {
			int mid = (low + high) >> 1;
			doSort(a, low, mid);
			doSort(a, mid + 1, high);
			merge(a, low, mid, high);
		}
	}

	public static void merge(int[] a, int low, int mid, int high) {
		int i = low;
		int j = mid + 1;
		int t = 0;
		int length = high - low + 1;
		int[] temp = new int[length];
		while (i <= mid && j <= high) {
			if (a[i] <= a[j]) {
				temp[t++] = a[i++];
			} else {
				temp[t++] = a[j++];
			}
		}
		while (i <= mid) {
			temp[t++] = a[i++];
		}
		while (j <= high) {
			temp[t++] = a[j++];
		}
		System.arraycopy(temp, 0, a, low, length);
	}

	public static void main(String[] args) {
		int[] ints = SortUtils.randomNumber(20);
		int[] copy = Arrays.copyOf(ints, ints.length);
		System.out.println(Arrays.toString(ints));
		sort(ints);
		System.out.println("***********");
		System.out.println(Arrays.toString(ints));
		Arrays.sort(copy);
		System.out.println(Arrays.toString(copy));
	}
}
