package search.swim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution {
	public static void main(String args[]) throws Exception {
		/*
		 * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이 작성한 코드를
		 * 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때
		 * 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		 * 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		// System.setIn(new FileInputStream("res/input.txt"));

		/*
		 * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		/*
		 * 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
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

		// 1년 이용권 사용 가격
		int price = prices[3];

		// 3달 이용권 4
		price = Math.min(price, 4 * prices[2]);

		// 3달 이용권 3
		int result = 0;
		int[] sums = get3sum(plan);
		int subPrice3 = getSubPrice(prices, 3, plan, sums);
		result = (3 * prices[2] + subPrice3);
//		System.out.println("3 : price : "+result);
		price = Math.min(price, 3 * prices[2] + subPrice3);
		// 3달 이용권 2
		int subPrice2 = getSubPrice(prices, 2, plan, sums);
		result = (2 * prices[2] + subPrice2);
//		System.out.println("2 : price : "+result);
		price = Math.min(price, 2 * prices[2] + subPrice2);
		// 3달 이용권 1
		int subPrice1 = getSubPrice(prices, 1, plan, sums);
		result = (1 * prices[2] + subPrice1);
//		System.out.println("1 : price : "+result);
		price = Math.min(price, 1 * prices[2] + subPrice1);
		// 3달 이용권 0
		int subPrice0 = getSubPrice(prices, 0, plan, sums);
//		System.out.println("0 : price : "+ subPrice0);
		price = Math.min(price, subPrice0);
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
