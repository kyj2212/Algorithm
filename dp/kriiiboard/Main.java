package dp.kriiiboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;




public class Main {

	static int N;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		
		System.out.println(printA());

	}
	
	public static long printA() {
		long [] dp = new long[N];
		if(N<=6) {
			return N;
		}

		dp[0]=1;
		dp[1]=2;
		dp[2]=3;
		dp[3]=4;
		dp[4]=5;
		dp[5]=6;
		for(int i =0;i<N-3;i++) {// N==17, N-3==14, i==13
			for(int j =0;j<i;j++) { // j==12까지, j==11 일때, dp[16] , dp[11] + dp[11] *(13-11+1) = dp[11] + dp[11] *(3) = 36*4 = 144
				dp[i+3]=Math.max(dp[i+3], dp[j]+dp[j]*(i-j+1));
			}
			
		}

//		Arrays.stream(dp).forEach(i -> System.out.printf(i+" "));
	//	System.out.println();

		return dp[N-1];
	}

}
