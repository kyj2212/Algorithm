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
		long [] buf = new long[N];
		if(N<=3) {
			return N;
		}

		dp[0]=1;
		dp[1]=2;
		dp[2]=3;
		for(int i =3;i<N;i++) {
			if(dp[i-1]+1>dp[i]) {
				dp[i]=dp[i-1]+1;
				buf[i]=buf[i-1];
			}
			if(dp[i-1]+buf[i-1]>=dp[i]) {
				dp[i]=dp[i-1]+buf[i-1];
				buf[i]=buf[i-1];
			}
			if(dp[i-3]*2 >= dp[i]) {
				dp[i]=dp[i-3]*2;
				buf[i]=dp[i-3];
			}
		}

//		Arrays.stream(dp).forEach(i -> System.out.printf(i+" "));
//		System.out.println();
//		Arrays.stream(buf).forEach(i -> System.out.printf(i+" "));
//		System.out.println();
		return dp[N-1];
	}

}
