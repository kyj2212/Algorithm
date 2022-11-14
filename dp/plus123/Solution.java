package dp.plus123;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0;i<T;i++) {
			int n = Integer.parseInt(br.readLine());
			
			int[][] dp = new int[3][n+1];
			dp[0][1]=1;
			if(n>1) {
				dp[1][2]=1;
			}
			if(n>2) {
				dp[2][3]=1;
			}
			
			for(int s=1;s<n;s++) { 
				dp[0][s+1]=dp[0][s] + dp[1][s] + dp[2][s];
				if(s-1>0) {
					dp[1][s+1]= dp[1][s-1] + dp[2][s-1];
				}
				if(s-2>0) {
					dp[2][s+1]= dp[2][s-2];
				}
			}
			bw.append(dp[0][n]+dp[1][n]+dp[2][n] + "\n");
			
		}
		
		bw.flush();
		bw.close();
		
	}

}
