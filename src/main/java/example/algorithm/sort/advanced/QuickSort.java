package example.algorithm.sort.advanced;

import java.util.Arrays;

import static example.algorithm.sort.SortUtils.swap;

/**
 * 快速排序
 * 空间复杂度 O(1)
 * 时间复杂度 O(logN); 如果枢纽选择不好，划分的区域为 0 和 length，则蜕化 O(n²)
 * 不稳定的排序
 */
public class QuickSort {

	public static void sort(int[] a) {
		doSort(a, 0, a.length - 1);
	}

	public static void doSort(int[] a, int low, int high) {
		if (low <= high) {
			int key = partition(a, low, high);
			System.out.println(Arrays.toString(a));
			doSort(a, low, key - 1);
			doSort(a, key + 1, high);
		}
	}

	private static int partition(int[] a, int low, int high) {
		int pivot = a[low];
		while (low < high) {
			while (low < high && a[high] >= pivot) {
				high--;
			}
			if (low < high) {
				a[low++] = a[high];
			}
			while (low < high && a[low] <= pivot) {
				low++;
			}
			if (low < high) {
				a[high--] = a[low];
			}
		}
		a[low] = pivot;
		return low;
	}


	public static void main(String[] args) {
		int[] a = {8, 5, 1, 2, 4};
		quickSort(a, 0 , a.length - 1);
		System.out.println(Arrays.toString(a));
	}



	/**
	 * @param arr
	 * @param left  左指针
	 * @param right 右指针
	 */
	public static void quickSort(int[] arr, int left, int right) {
		if (left < right) {
			//获取枢纽值，并将其放在当前待处理序列末尾
			dealPivot(arr, left, right);
			//枢纽值被放在序列末尾
			int pivot = right - 1;
			//左指针
			int i = left;
			//右指针
			int j = right - 1;
			while (true) {
				while (arr[++i] < arr[pivot]) {
				}
				while (j > left && arr[--j] > arr[pivot]) {
				}
				if (i < j) {
					swap(arr, i, j);
				} else {
					break;
				}
			}
			if (i < right) {
				swap(arr, i, right - 1);
			}
			quickSort(arr, left, i - 1);
			quickSort(arr, i + 1, right);
		}

	}

	/**
	 * 处理枢纽值
	 *
	 * @param arr
	 * @param left
	 * @param right
	 */
	public static void dealPivot(int[] arr, int left, int right) {
		int mid = (left + right) / 2;
		if (arr[left] > arr[mid]) {
			swap(arr, left, mid);
		}
		if (arr[left] > arr[right]) {
			swap(arr, left, right);
		}
		if (arr[right] < arr[mid]) {
			swap(arr, right, mid);
		}
		swap(arr, right - 1, mid);
	}

}

