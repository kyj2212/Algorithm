package study.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MergeSort1 {
	
	public static int cnt=0;
	public static int result=-1;
	public static int K;
	public static int[] sorted;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] num = new int[N];
		sorted = new int[N];
		
		for(int i =0 ; i<N ; i++)
		{
			num[i] = Integer.parseInt(st.nextToken());
		}

		mergeSort(num, 0, N-1);
		System.out.println(result);
		
		br.close();
		
	}
	
	public static void mergeSort(int[] num, int s, int e) {
		int m = 0;
		if(s<e) {
			// �����ϱ�
			m = (s+e)/2;
			mergeSort(num,s,m);
			mergeSort(num,m+1,e);
			// ����+����
			merge(num,s,m,e);

		}
		
	}
	
	public static void merge(int[] num, int s, int m, int e) {
		int left = s;
		int right = m+1;
		int idx = 0;

		if(cnt>=K) {
			return;
		}
		
		while(left<=m && right <=e) // ���� �迭, ������ �迭�� ������ �� �����ϸ� ��
		{	// ���ʹ迭, ������ �迭�� ù��° �� ��
			if(num[left] <= num[right]) { // ���� ���� �� ���� ���
				sorted[idx++] = num[left++]; // ���� �� ���� idx++
			}
			else { // ������ �� ���� idx++
				sorted[idx++]=num[right++];
			}
		}
		
		if(left>m) { // && right <=e ���ʸ� ���� ���յ�, ������ �߰�
			while(right<=e) {
				sorted[idx++]=num[right++];
			}
		}
		else { // right > e && left<=m �����ʸ� ���� ���յ�, ���� �߰�
			while(left<=m) {
				sorted[idx++]=num[left++];
			}
		}

//		System.out.println("s : "+s +" e : "+e);
//		printarr(sorted);
		// sorted�� ������ �����
		for(int i = s;i<=e;i++) {
			
//			if(++cnt > K) {
//				return;
//			}
			num[i]=sorted[i-s];
//			System.out.println(" ���� ���� Ƚ�� : "+ cnt);
//			printarr(num);

			if(++cnt == K ) {
				//System.out.println(num[i]);
				result=num[i];
				return;
			}

		}
	
		
	}
	public static void printarr(int[] num) {
		System.out.print("�迭 : ");
		for(int i : num) {
			System.out.print(i +" ");
		}
		System.out.println();
		
	}


}
