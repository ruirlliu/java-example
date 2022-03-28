package example.algorithm.sort;

import java.util.Random;


public class SortUtils {

	private SortUtils() { }

	private static final Random RANDOM = new Random();

	public static int[] randomNumber(int size) {
		return RANDOM.ints(1, 1000)
				.limit(size)
				.toArray();
	}

	public static void swap(int[] a, int p1, int p2) {
		int tmp = a[p1];
		a[p1] = a[p2];
		a[p2] = tmp;
	}

}
