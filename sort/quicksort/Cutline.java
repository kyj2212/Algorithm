package study.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Cutline {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] score = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i =0 ; i<N ; i++)
		{
			score[i] = Integer.parseInt(st.nextToken()); 
		}
		
		quickSort(score, 0, N-1);
		int cut = score[N-k];
		
		br.close();
		System.out.println(cut);
		
	}
	
	static void quickSort(int[] score, int start, int end) 
	{
		// start와 end 사이가 1개 미만이면 종료
		if(end-start<1) 
		{
			return;
		}
		
		// 피벗 정하기
		int pivot = start;
		int left = start+1;
		int right = end;
		
		while(left<=right) 
		{
			// 왼쪽에서 피벗보다 큰수 찾기
			while(score[pivot]>=score[left] && left<end) 
			{
				left++; // pivot보다 작으면 한칸이동
			}

			// 오른쪽에서 피벗보다 작은수 찾기	
			while(score[pivot]<=score[right] && right>start) 
			{
				right--; // pivot보다 크면 한칸이동
			}
			
			// 왼쪽 오른쪽 엇갈렸을 때 피벗과 오른쪽에서 찾은 작은 수를 교체
			if(left>= right) 
			{
				int tmp = score[pivot];
				score[pivot]=score[right];
				score[right]=tmp;
				
				// 피벗 기준 왼쪽, 오른쪽 각각 퀵솔트
				quickSort(score, start, right-1);
				quickSort(score, right+1, end);
				break; // 엇갈린 경우 피벗위치 고정으로 while 종료
			
			// left와 right 가 엇갈리지 않았을 때, 둘을 교환	
			}else 
			{ 
				int tmp = score[left];
				score[left]=score[right];
				score[right]=tmp;
			}
		}
	}
}
