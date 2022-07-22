package example.algorithm.dp;

/**
 * 斐波那契数列
 * 力扣第 509 题「斐波那契数」
 * https://leetcode-cn.com/problems/fibonacci-number/
 */
public class Fibonacci {

	public int fib(int n) {
		if (n <= 1) {
			return n;
		}
		int first = 0;
		int second = 1;
		for (int i = 0; i < n - 1; i++) {
			int sum = first + second;
			first = second;
			second = sum;
		}
		return second;
	}
}
