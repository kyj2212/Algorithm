package search.swim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.*;


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

		// 1년 이용권 사용 가격
		int price = prices[3];

		// 3달 이용권 4
		price = Math.min(price, 4 * prices[2]);


		int[] sums = get3sum(plan);
		
		// 3달 이용권 3
		price = Math.min(price, 3 * prices[2] + getSubPrice(prices, 3, plan, sums));
		
		// 3달 이용권 2
		price = Math.min(price, 2 * prices[2] + getSubPrice(prices, 2, plan, sums));
		
		// 3달 이용권 1
		price = Math.min(price, 1 * prices[2] + getSubPrice(prices, 1, plan, sums));
		
		// 3달 이용권 0
		price = Math.min(price, getSubPrice(prices, 0, plan, sums));
		
		return price;

	}

	private static int getSubPrice(int[] prices, int m3, int[] plan, int[] sums) {
		int[] visited = new int[12];
		// m3개의 3달 이용권 방문
		visitM3(visited, plan, m3, sums);

		// 1달 이용권의 최대 개수
		int m2 = 0;
		for (int i = 0; i < 12; i++) {
			if (visited[i] == 0 && plan[i] != 0) {
				m2++;
			}
		}
		// 1달 이용권 최대 개수의 가격
		int price = prices[1] * m2;
		for (int i = m2 - 1; i >= 0; i--) {
			// m2 개의 1달 이용권 사용
			int d = visitM2(visited, plan, i);

			// 가격 계산
			price = Math.min(price, prices[1] * i + prices[0] * d);
		}

		return price;
	}

	private static int visitM2(int[] visited, int[] plan, int m2) {

		for (int n = 0; n < m2; n++) {
			int max = 0;
			int maxIdx = 0;
			for (int i = 0; i < 12; i++) {
				if (visited[i] == 0) {
					int tmp = plan[i];
					if (tmp > max) {
						max = tmp;
						maxIdx = i;
					}
				}
			}
			visited[maxIdx] = 2;
		}

		int d = 0;
		for (int i = 0; i < 12; i++) {
			if (plan[i] != 0 && visited[i] == 0) {
				d += plan[i];
			}
			// 다시 되돌려
			if (visited[i] == 2) {
				visited[i] = 0;
			}
		}
		return d;
	}

	private static void visitM3(int[] visited, int[] plan, int m3, int[] sums) {

		if (m3 == 0) {
			return;
		}

		int cnt = 0;
		int sumall = 0;
		int max = 0;
		int[] maxIdx = new int[m3];
		int[] idxs = new int[m3];
		List<Integer> maxs = new ArrayList<>();

		for (int i = 0; i < 12; i++) {
			sumall = sums[i];
			int n = 0;
			idxs[n++] = i;
			for (int j = i + 3; j < 12; j += 3) {
				if (n > m3 - 1) {
					break;
				}
				sumall += sums[j];
				idxs[n++] = j;

			}
			if (sumall > max) {
				max = sumall;
				for (int x = 0; x < m3; x++) {
					maxIdx[x] = idxs[x];
				}
			}
		}
		if(max==0) {
			return;
		}

		for (int x = 0; x < m3; x++) {
			visited[maxIdx[x]] = visited[maxIdx[x] + 1] = visited[maxIdx[x] + 2] = 3;
		}


	}

	private static int[] get3sum(int[] plan) {
		int[] sums = new int[12];
		for (int i = 0; i < 12; i++) {

			if (i < 10) {
				int tmp = plan[i] + plan[i + 1] + plan[i + 2];
				sums[i] = tmp;
			}
			if (i == 10) {
				int tmp = plan[i] + plan[i + 1];
				sums[i] = tmp;
			}
			if (i == 11) {
				int tmp = plan[i];
				sums[i] = tmp;
			}

		}
		return sums;
	}
}
