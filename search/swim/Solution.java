package search.swim;

import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			/////////////////////////////////////////////////////////////////////////////////////////////

			int[] prices = new int[4];
			int[] plan = new int[12];
			for (int i = 0; i < 4; i++) {
				prices[i] = sc.nextInt();
			}
			for (int i = 0; i < 12; i++) {
				plan[i] = sc.nextInt();
			}

			int result = getPrice(prices, plan);
			System.out.println("#" + test_case + " " + result);

			/////////////////////////////////////////////////////////////////////////////////////////////

		}
	}

	private static int getPrice(int[] prices, int[] plan) {

		int price = prices[3];

		int[][] dp = new int[3][12];
		int[] visited = new int[12];

		dp[0][0] = plan[0] * prices[0];
		dp[1][0] = prices[1];
		dp[2][0] = prices[2];

		for (int m = 1; m < 12; m++) {

			dp[0][m] = Math.min(dp[0][m - 1] + plan[m] * prices[0], dp[1][m - 1] + plan[m] * prices[0]);
			dp[1][m] = Math.min(dp[0][m - 1] + prices[1], dp[1][m - 1] + prices[1]);
			dp[2][m] = Math.min(dp[0][m - 1] + prices[2], dp[1][m - 1] + prices[2]);

			if (m > 2) {
				dp[0][m] = Math.min(dp[0][m], dp[2][m - 3] + plan[m] * prices[0]);
				dp[1][m] = Math.min(dp[1][m], dp[2][m - 3] + prices[1]);
				dp[2][m] = Math.min(dp[2][m], dp[2][m - 3] + prices[2]);
			}

		}
		for (int i = 0; i < 3; i++) {
			price = Math.min(price, dp[i][11]);
		}

		return Math.min(price, dp[2][9]);
	}

}
