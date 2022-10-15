package search.swim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
/////////////////////////////////////////////////////////////////////////////////////////////
// �⺻ �����ڵ�� ���� �����ص� ���� �����ϴ�. ��, ����� ���� ����
// �Ʒ� ǥ�� ����� ���� �ʿ�� �����ϼ���.
// ǥ�� �Է� ����
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int ���� 1�� �Է¹޴� ����
// b = sc.nextDouble();                        // double ���� 1�� �Է¹޴� ����
// g = sc.nextByte();                          // char ���� 1�� �Է¹޴� ����
// var = sc.next();                            // ���ڿ� 1�� �Է¹޴� ����
// AB = sc.nextLong();                         // long ���� 1�� �Է¹޴� ����
/////////////////////////////////////////////////////////////////////////////////////////////
// ǥ�� ��� ����
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int ���� 1�� ����ϴ� ����
//System.out.println(b); 		       						 // double ���� 1�� ����ϴ� ����
//System.out.println(g);		       						 // char ���� 1�� ����ϴ� ����
//System.out.println(var);		       				   // ���ڿ� 1�� ����ϴ� ����
//System.out.println(AB);		       				     // long ���� 1�� ����ϴ� ����
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.*;

/*
   ����ϴ� Ŭ�������� Solution �̾�� �ϹǷ�, ������ Solution.java �� ����� ���� �����մϴ�.
   �̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� �����غ� �� �ֽ��ϴ�.
 */
class Solution {
	public static void main(String args[]) throws Exception {
		/*
		 * �Ʒ��� �޼ҵ� ȣ���� ������ ǥ�� �Է�(Ű����) ��� input.txt ���Ϸκ��� �о���ڴٴ� �ǹ��� �ڵ��Դϴ�. �������� �ۼ��� �ڵ带
		 * �׽�Ʈ �� ��, ���Ǹ� ���ؼ� input.txt�� �Է��� ������ ��, �� �ڵ带 ���α׷��� ó�� �κп� �߰��ϸ� ���� �Է��� ������ ��
		 * ǥ�� �Է� ��� ���Ϸκ��� �Է��� �޾ƿ� �� �ֽ��ϴ�. ���� �׽�Ʈ�� ������ ������ �Ʒ� �ּ��� ����� �� �޼ҵ带 ����ϼŵ� �����ϴ�.
		 * ��, ä���� ���� �ڵ带 �����Ͻ� ������ �ݵ�� �� �޼ҵ带 ����ų� �ּ� ó�� �ϼž� �մϴ�.
		 */
		// System.setIn(new FileInputStream("res/input.txt"));

		/*
		 * ǥ���Է� System.in ���κ��� ��ĳ�ʸ� ����� �����͸� �о�ɴϴ�.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		/*
		 * ���� ���� �׽�Ʈ ���̽��� �־����Ƿ�, ������ ó���մϴ�.
		 */

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

		// 1�� �̿�� ��� ����
		int price = prices[3];

		// 3�� �̿�� 4
		price = Math.min(price, 4 * prices[2]);

		// 3�� �̿�� 3
		int result = 0;
		int[] sums = get3sum(plan);
		int subPrice3 = getSubPrice(prices, 3, plan, sums);
		result = (3 * prices[2] + subPrice3);
//		System.out.println("3 : price : "+result);
		price = Math.min(price, 3 * prices[2] + subPrice3);
		// 3�� �̿�� 2
		int subPrice2 = getSubPrice(prices, 2, plan, sums);
		result = (2 * prices[2] + subPrice2);
//		System.out.println("2 : price : "+result);
		price = Math.min(price, 2 * prices[2] + subPrice2);
		// 3�� �̿�� 1
		int subPrice1 = getSubPrice(prices, 1, plan, sums);
		result = (1 * prices[2] + subPrice1);
//		System.out.println("1 : price : "+result);
		price = Math.min(price, 1 * prices[2] + subPrice1);
		// 3�� �̿�� 0
		int subPrice0 = getSubPrice(prices, 0, plan, sums);
//		System.out.println("0 : price : "+ subPrice0);
		price = Math.min(price, subPrice0);
		return price;

	}

	private static int getSubPrice(int[] prices, int m3, int[] plan, int[] sums) {
		int[] visited = new int[12];
		// m3���� 3�� �̿�� �湮
		visitM3(visited, plan, m3, sums);

		// 1�� �̿���� �ִ� ����
		int m2 = 0;
		for (int i = 0; i < 12; i++) {
			if (visited[i] == 0 && plan[i] != 0) {
				m2++;
			}
		}
		// 1�� �̿�� �ִ� ������ ����
		int price = prices[1] * m2;
		for (int i = m2 - 1; i >= 0; i--) {
			// m2 ���� 1�� �̿�� ���
			int d = visitM2(visited, plan, i);

			// ���� ���
//			System.out.println("m2 : "+i + " d : "+d);
			int result = (prices[1] * i + prices[0] * d);
//			System.out.println("subprice : "+ price +" resutsub : "+result);
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

		// Arrays.stream(visited).forEach(num -> System.out.printf(num+", "));
		// System.out.println();

		int d = 0;
		for (int i = 0; i < 12; i++) {
			if (plan[i] != 0 && visited[i] == 0) {
				d += plan[i];
			}
			// �ٽ� �ǵ���
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

//		System.out.println("1 : max sum : "+ max+ " max idx : " + maxIdx[0] );

		for (int x = 0; x < m3; x++) {
			visited[maxIdx[x]] = visited[maxIdx[x] + 1] = visited[maxIdx[x] + 2] = 3;
		}

//		Arrays.stream(visited).forEach(num -> System.out.printf(num+", "));
//		System.out.println();

	}

	private static int[] get3sum(int[] plan) {
		int[] sums = new int[12];
		for (int i = 0; i < 12; i++) {

			if (i < 10) {
				int tmp = plan[i] + plan[i + 1] + plan[i + 2];
				// sums.add(new int[] {i,tmp});
				sums[i] = tmp;
			}
			if (i == 10) {
				int tmp = plan[i] + plan[i + 1];
				// sums.add(new int[] {i,tmp});
				sums[i] = tmp;
			}
			if (i == 11) {
				int tmp = plan[i];
				// sums.add(new int[] {i,tmp});
				sums[i] = tmp;
			}

		}
//		System.out.printf("sums : ");
//		for(int i : sums) {
//			System.out.printf(i+", ");
//		}
//		System.out.println();
		return sums;
	}
}
