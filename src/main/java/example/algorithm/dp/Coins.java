package example.algorithm.dp;

import java.util.Arrays;
import java.util.Stack;

/**
 * 力扣第 322 题「零钱兑换」
 * https://leetcode-cn.com/problems/coin-change/
 */
public class Coins {

	/**
	 * 作者：LeetCode-Solution
	 * 链接：https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/
	 * 来源：力扣（LeetCode）
	 */
	public int coinChange(int[] coins, int amount) {
		int max = amount + 1;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, max);
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i) {
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}


	static class Coins_3 {

		int coinChange(int[] coins, int amount) {
			int[] dp = new int[amount + 1];
			// 数组大小为 amount + 1，初始值也为 amount + 1
			Arrays.fill(dp, amount + 1);

			// base case
			dp[0] = 0;
			// 外层 for 循环在遍历所有状态的所有取值
			for (int i = 0; i < dp.length; i++) {
				// 内层 for 循环在求所有选择的最小值
				for (int coin : coins) {
					// 子问题无解，跳过
					if (i < coin) {
						continue;
					}
					dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
				}
			}
			return dp[amount] > amount ? -1 : dp[amount];
		}
	}

	/**
	 * 每次都只使用最大值，则肯定使用硬币最少。
	 * 如果最大值加上后大于目标紧急，则换次大，以此类推
	 * 错误: 每次最大，当最后剩余的小于给定硬币，会无解。如 [5, 4, 3] 7
	 */
	@Deprecated
	static class Coins_2 {
		// coins 中是可选硬币面值，amount 是目标金额
		public int coinChange(int[] coins, int amount) {
			Arrays.sort(coins);
			return doCoinChange(coins, amount, new Stack<>());
		}

		public int doCoinChange(int[] coins, int amount, Stack<Integer> stack) {
			if (amount == 0) {
				return 0;
			}
			if (amount < 0) {
				return -1;
			}
			for (int i = coins.length - 1; i >= 0; i--) {
				if (amount - coins[i] < 0) {
					continue;
				}
				stack.push(coins[i]);
				int subNum = doCoinChange(coins, amount - coins[i], stack);
				if (subNum == -1) {
					stack.pop();
				}
				return stack.size();
			}
			return -1;
		}
	}

	/**
	 * 带备忘录的递归方式
	 */
	static class Coins_1 {
		// coins 中是可选硬币面值，amount 是目标金额
		public int coinChange(int[] coins, int amount) {
			return doCoinChange(coins, amount, new int[amount]);
		}

		public int doCoinChange(int[] coins, int amount, int[] arr) {
			if (amount < 0) {
				return -1;
			}
			if (amount == 0) {
				return 0;
			}
			// 先搜索备忘录
			if (arr[amount - 1] != 0) {
				return arr[amount - 1];
			}
			int num = Integer.MAX_VALUE;
			for (int coin : coins) {
				int subCoins = doCoinChange(coins, amount - coin, arr);
				if (subCoins == -1) {
					continue;
				}
				// 子问题的最优解
				num = Math.min(num, subCoins + 1);
			}
			int value = num == Integer.MAX_VALUE ? -1 : num;
			arr[amount - 1] = value;
			return value;
		}
	}

	public static void main(String[] args) {
//		int amount = 26;
//		Coins.Coins_1 coins = new Coins.Coins_1();
//		int i = coins.coinChange(new int[] { 5, 5, 1 }, amount);
//		System.out.println(i);
//		Coins.Coins_2 coins2 = new Coins.Coins_2();
//		int j = coins2.coinChange(new int[] { 5, 5, 1 }, amount);
//		System.out.println(j);
		System.out.println(6249 % 408);
	}
}
