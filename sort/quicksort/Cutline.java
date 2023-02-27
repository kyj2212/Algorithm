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
		// start�� end ���̰� 1�� �̸��̸� ����
		if(end-start<1) 
		{
			return;
		}
		
		// �ǹ� ���ϱ�
		int pivot = start;
		int left = start+1;
		int right = end;
		
		while(left<=right) 
		{
			// ���ʿ��� �ǹ����� ū�� ã��
			while(score[pivot]>=score[left] && left<end) 
			{
				left++; // pivot���� ������ ��ĭ�̵�
			}

			// �����ʿ��� �ǹ����� ������ ã��	
			while(score[pivot]<=score[right] && right>start) 
			{
				right--; // pivot���� ũ�� ��ĭ�̵�
			}
			
			// ���� ������ �������� �� �ǹ��� �����ʿ��� ã�� ���� ���� ��ü
			if(left>= right) 
			{
				int tmp = score[pivot];
				score[pivot]=score[right];
				score[right]=tmp;
				
				// �ǹ� ���� ����, ������ ���� ����Ʈ
				quickSort(score, start, right-1);
				quickSort(score, right+1, end);
				break; // ������ ��� �ǹ���ġ �������� while ����
			
			// left�� right �� �������� �ʾ��� ��, ���� ��ȯ	
			}else 
			{ 
				int tmp = score[left];
				score[left]=score[right];
				score[right]=tmp;
			}
		}
	}
}
