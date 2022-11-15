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
		int [][] dp = new int[N][M+1];
		System.out.println(play(dp,V));

	}

	private static int play(int[][] dp, int[] V) {
		if(S-V[0]>=0) {
			dp[0][S-V[0]]=1;
		}
		if(S+V[0]<=M) {
			dp[0][S+V[0]]=1;
		}
		if(S-V[0]<0 && S+V[0]>M) {
//			System.out.println("P:"+ P+" V :"+V[0]+" ");
			return -1;
		}
		for(int n=1;n<N;n++) {
			int cnt =0;
			for(int m=0;m<=M;m++) {
				if(dp[n-1][m]!=n) {
					cnt++;
					continue;
				}
				if(m-V[n]>=0) {
					dp[n][m-V[n]]=n+1;
				}
				if(m+V[n]<=M) {
					dp[n][m+V[n]]=n+1;
				}
				if(m-V[n]<0 && m+V[n]>M) {
//					System.out.println("P:"+ P+" V :"+V[n]+" ");
					dp[n][m]=-1;
				}
			}
			if(cnt>M) {
				return -1;
			}
//			System.out.println("V="+V[n]+" ");
//			for(int i =0;i<N;i++) {
//				for(int j=0;j<=M;j++) {
//					System.out.printf(dp[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		
		for(int j=M;j>=0;j--) {
			if(dp[N-1][j]==N) {
				return j;
			}
		}
		return -1;
	}
}
