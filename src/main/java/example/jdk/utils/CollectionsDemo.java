package example.jdk.utils;

import java.util.Arrays;

public class CollectionsDemo {

	public void rotate(int[][] matrix) {
		int len = matrix.length;
		int distance = len - 1;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][distance - i];
				matrix[j][distance - i] = temp;
			}
		}
	}

	public static void main(String[] args) {
		CollectionsDemo solution = new CollectionsDemo();
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		solution.rotate(matrix);
		System.out.println(Arrays.deepToString(matrix));
	}

}
