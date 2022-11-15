package dp.bojstreet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int N;
	public static void main(String args[]) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		char[] blocks = new char[N];
		// B O J : 1 14 9
		for(int i =0;i<N;i++) {
			blocks[i]=str.charAt(i);
		}
		
		int[] dp = new int[N];
		for(int i =0;i<N;i++) {
			dp[i]=1000000;
		}
		Queue<Integer> queue = new LinkedList<>();
		dp[0]=0;
		queue.add(0);
		while(!queue.isEmpty()) {
			
			int prev = queue.poll();
			int cnt =0;
			for(int i =prev+1;i<N;i++) {
				
				if(blocks[i]!=next(blocks[prev])) {
					cnt++;

					continue;
				}
//				System.out.println("prev : "+prev +" next : "+i);
				dp[i]=Math.min(dp[i], dp[prev]+(i-prev)*(i-prev));
				if(!queue.contains(i)) {
					queue.add(i);
				}
				
			}

//			Arrays.stream(dp).forEach(i-> System.out.printf(i+ " "));
//			System.out.println();
		}
		System.out.println(dp[N-1]==1000000 ? -1 : dp[N-1]);
//		System.out.println(dp[N-1]);
		
	}
	public static char next(char block) {
		if(block=='B') {
			return 'O';
		}
		if(block=='O') {
			return 'J';
		}
		else {
			return 'B';
		}
	}
}
