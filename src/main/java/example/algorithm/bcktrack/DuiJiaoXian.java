package example.algorithm.bcktrack;

import java.util.Arrays;

public class DuiJiaoXian {




	public void prune(boolean[][] used, int i, int j, int size, boolean value) {
		for (int k = 0; k < size; k++) {
			used[i][k] = value;
			used[k][j] = value;
		}
		for (int m = i, n = j; m < size && n < size; m++, n++) {
			used[m][n] = value;
		}
		for (int m = i, n = j; m >= 0 && n < size; m--, n++) {
			used[m][n] = value;
		}
	}

	public static void main(String[] args) {
		boolean[][] used = new boolean[4][4];
		DuiJiaoXian duiJiaoXian = new DuiJiaoXian();
		duiJiaoXian.prune(used, 0, 1, 4, true);
		System.out.println(Arrays.deepToString(used));
	}
}
