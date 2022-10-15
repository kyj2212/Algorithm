package search.hikingtrail;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
	static int N,K;
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

/////////////////////////////////////////////////////////////////////////////////////////////

			N = sc.nextInt();
			K = sc.nextInt();
			
			int [][] height = new int[N][N];
			for(int i =0;i<N;i++) {
				for(int j=0;j<N;j++) {
					height[i][j]=sc.nextInt();
				}
			}
			int result = getTrail(height);
			for(int i =0;i<N;i++) {
				for(int j=0;j<N;j++) {
					for(int k =1;k<=K;k++) {
						int tmp = height[i][j];
						height[i][j]=height[i][j]-k;
						result = Math.max(result,getTrail(height));
						height[i][j]=tmp;
					}
					
				}
			}
			
			System.out.println("#"+test_case+" "+result);
			
/////////////////////////////////////////////////////////////////////////////////////////////

		}
	}
	
	private static int getTrail(int[][] height) {
		int max = findMax(height);
		
		int maxLen=0;
		Queue<int[]> queue = new LinkedList<>();
		for(int i =0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(height[i][j]==max) {
					int len=1;
					addQueue(i,j,height,queue,len+1);
					while(!queue.isEmpty()) {
						int[] node = queue.poll();
						len=node[3];
						addQueue(node[0],node[1],height,queue,len+1);
					}
					maxLen=Math.max(maxLen, len);
				}
			}
		}
		return maxLen;
	}

	private static void addQueue(int i, int j, int[][] height, Queue<int[]> queue,int len) {
		int h = height[i][j];
		if(j+1<N && height[i][j+1]<h) {
			queue.add(new int[]{i,j+1,height[i][j+1],len});
		}
		if(i+1<N && height[i+1][j]<h) {
			queue.add(new int[]{i+1,j,height[i+1][j],len});
		}
		if(j-1>=0 && height[i][j-1]<h) {
			queue.add(new int[]{i,j-1,height[i][j-1],len});
		}
		if(i-1>=0 && height[i-1][j]<h) {
			queue.add(new int[]{i-1,j,height[i-1][j],len});
		}
	}
	private static int findMax(int[][] height) {
		
		int max =0;
		for(int i =0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(height[i][j]>max) {
					max=height[i][j];
				}
			}
		}
		return max;
	}
	
}