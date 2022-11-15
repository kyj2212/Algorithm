package dp.guitarlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N,S,M;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] V = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i =0;i<N;i++) {
			V[i]=Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N+1][M+1];
		
		dp[0][S]=1;
		for(int i =0;i<N;i++) {
//			for(int n=M;n>=0;n--) {
//				System.out.printf(dp[i][n] +" ");
//			}
//			System.out.println();
			int cnt =0;
			for(int j =0;j<=M;j++) {
				if(dp[i][j]==0 || dp[i][j]== -1) {
					cnt++;
					continue;
				}
//				System.out.println("j : " + j + " V[i] : "+ V[i]);
				if(j-V[i]>=0) {
					dp[i+1][j-V[i]]=1;
				}
				if(j+V[i]<=M) {
					dp[i+1][j+V[i]]=1;
				}
				if(j-V[i]<0 && j+V[i]>M) {
					dp[i+1][j]=-1;
				}
			}
			if(cnt==M+1) {
				System.out.println(-1);
				return;
			}
//			for(int n=M;n>=0;n--) {
//				System.out.printf(dp[i+1][n] +" ");
//			}
//			System.out.println();
		}
		for(int i=M;i>=0;i--) {
			
			if(dp[N][i]!=0) {
				System.out.println(i);
				break;
			}
		}
	}

}
