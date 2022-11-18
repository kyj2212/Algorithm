package simulation.lunchtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main (String args[]) throws IOException {
		
		
		//Scanner sc = new Scanner(System.in);
		//int T;
		//T=sc.nextInt();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T  = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int N  = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for(int i =0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				for(int j =0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			List<int[]> pl = new ArrayList<>(); 
			int[][] sl = new int[2][3]; 
			for(int i =0;i<N;i++) {
				for(int j =0;j<N;j++) {
					if(map[i][j]==1) {
						pl.add(new int[]{i,j});
					}
					if(map[i][j]>1) {
						if(sl[0][2]==0) {
							sl[0]=new int[] {i,j,map[i][j]};
							continue;
						}
						sl[1]=new int[] {i,j,map[i][j]};
					}
				}
				
			}
//			Arrays.stream(sl[0]).forEach(i -> System.out.printf(i+" "));
//			System.out.println();
//			Arrays.stream(sl[1]).forEach(i -> System.out.printf(i+" "));
//			System.out.println();
			int[][] p = new int[pl.size()][2];

			System.out.println("#"+test_case+" "+findMin(N,0,pl,p,sl));
		}
		
	
	}
	
	public static int findMin(int N, int i, List<int[]> pl, int[][] p, int[][] sl) {
		int answer = 999999;
		if(i==pl.size()) {
			return getTime(N,p,sl);
		}
		
		int s1 = Math.abs(pl.get(i)[0]-sl[0][0])+Math.abs(pl.get(i)[1]-sl[0][1]);
		int s2 = Math.abs(pl.get(i)[0]-sl[1][0])+Math.abs(pl.get(i)[1]-sl[1][1]);
		
		
			
//			System.out.println("i "+i+" s1 : "+s1+" s2 : "+s2);
		
			p[i][0]=s1;
			p[i][1]=1;
			
			answer = Math.min(answer,findMin(N,i+1,pl,p,sl));
			
			
			p[i][0]=s2;
			p[i][1]=2;
			
			answer = Math.min(answer,findMin(N,i+1,pl,p,sl));
			return answer;
//		for(int j =0;j<p.length;j++) {
//			s1 = Math.abs(pl.get(j)[0]-sl[0][0])+Math.abs(pl.get(j)[1]-sl[0][1]);
//			s2 = Math.abs(pl.get(j)[0]-sl[1][0])+Math.abs(pl.get(j)[1]-sl[1][1]);
//			if(j==2 ||j==5 ||j==6) {
//				p[j][0]=s1;
//				p[j][1]=1;
//			}else {
//				p[j][0]=s2;
//				p[j][1]=2;
//			}
//		}
//		return getTime(N,p,sl);
		

	}
	
	
	public static int getTime(int N,int[][] p, int[][] sl) {
		
		int maxTime=N*2+11;
		int[][] time = new int[p.length][maxTime];

//		for(int j=0;j<p.length;j++) {
//			System.out.printf(p[j][1]+" ");
//		}
//		System.out.println();
//
//		for(int j=0;j<p.length;j++) {
//			System.out.printf(p[j][0]+" ");
//		}
//		System.out.println();

//	for(int i =1;i<maxTime;i++) {
//		for(int j=0;j<pl.size();j++) {
//			time[j][i]=-1;
//		}
//	}
		for(int j=0;j<p.length;j++) {
			time[j][0]=p[j][0];
		}
		
		int [] isStair = new int[p.length];

			for(int i =1;i<maxTime;i++) {
				int cnt =0;
//				int[] s = new int[2];
//				for(int j =0;j<pl.size();j++) {
//					if(isStair[j]==1) {
//						s[0]++;
//					}
//					if(isStair[j]==2) {
//						s[1]++;
//					}
//				}
//				System.out.println("1번 입구 "+s[0]+" 2번입구 "+s[1]);
				for(int j=0;j<p.length;j++) {

					if(time[j][i-1]>0) {
						time[j][i]=time[j][i-1]-1;
					}
					if(time[j][i]==0 && isStair[j]!=0) {
						cnt++;
						isStair[j]=-1;
					}
					
					if(cnt>=p.length) {
//						for(int k=0;k<pl.size();k++) {
//							System.out.printf(isStair[k]+" ");
//						}
//						System.out.println();
						
//						for(int k=0;k<p.length;k++) {
//							Arrays.stream(time[k]).forEach(x -> System.out.printf("%2d ",x));
//							System.out.println();
//						}
//						System.out.println("time : "+(i));
//							
						
						return i;
//						System.out.println("#"+T+" "+(i-1));
//						break L1;
					}
					
				}
				
				for(int j =0;j<p.length;j++) {
					if(time[j][i-1]==0 && isStair[j]==0) {
						int ent = p[j][1];
//						System.out.println(j+"번사람 : "+s[ent-1]+" 이라서 못들어가");
						if(cnt(ent,isStair)<3) {
							time[j][i]=sl[ent-1][2];
							isStair[j]=ent;
//							s[ent-1]++;
							
						}else {
							time[j][i]=0;
						}
						continue;
					}
					
				}
				
			}

		
		return maxTime;
	}
	
	public static int cnt(int ent, int[] isStair) {
		int cnt =0;
		for(int i =0;i<isStair.length;i++) {
			if(isStair[i]==ent) {
				cnt++;
			}
		}
//		System.out.println(ent+"번 계단 사용자 : "+cnt);
		return cnt;
	}
	
}
