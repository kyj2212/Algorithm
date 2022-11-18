package search.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=1;
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			/////////////////////////////////////////////////////////////////////////////////////////////
			
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int[] buildings = new int[N];
			for(int i =0;i<N;i++) {
				buildings[i]=Integer.parseInt(st.nextToken());
			}
			
			int answer =0;
			for(int i =0;i<N;i++) {
				answer+=isViewable(N,buildings,i);
			}
			
			System.out.println("#"+test_case+" "+answer);
			/////////////////////////////////////////////////////////////////////////////////////////////

		}
	}
	
	public static int isViewable(int N, int[] buildings, int i) {

		int left = Math.max(0,buildings[i]-(Math.max(i<1?0:buildings[i-1], i<2?0:buildings[i-2])));
		int right = Math.max(0,buildings[i]-(Math.max(i>=N-1?0:buildings[i+1], i>=N-2?0:buildings[i+2])));
		return Math.min(left, right);
	}

}
