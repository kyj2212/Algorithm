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
			// 분할하기
			m = (s+e)/2;
			mergeSort(num,s,m);
			mergeSort(num,m+1,e);
			// 정복+정합
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
		
		while(left<=m && right <=e) // 왼쪽 배열, 오른쪽 배열의 끝까지 다 정렬하면 끝
		{	// 왼쪽배열, 오른쪽 배열의 첫번째 값 비교
			if(num[left] <= num[right]) { // 왼쪽 값이 더 작은 경우
				sorted[idx++] = num[left++]; // 왼쪽 값 정렬 idx++
			}
			else { // 오른쪽 값 정렬 idx++
				sorted[idx++]=num[right++];
			}
		}
		
		if(left>m) { // && right <=e 왼쪽만 먼저 병합됨, 오른쪽 추가
			while(right<=e) {
				sorted[idx++]=num[right++];
			}
		}
		else { // right > e && left<=m 오른쪽만 먼저 병합됨, 왼쪽 추가
			while(left<=m) {
				sorted[idx++]=num[left++];
			}
		}

//		System.out.println("s : "+s +" e : "+e);
//		printarr(sorted);
		// sorted를 기존에 덮어씌워
		for(int i = s;i<=e;i++) {
			
//			if(++cnt > K) {
//				return;
//			}
			num[i]=sorted[i-s];
//			System.out.println(" 정렬 실행 횟수 : "+ cnt);
//			printarr(num);

			if(++cnt == K ) {
				//System.out.println(num[i]);
				result=num[i];
				return;
			}

		}
	
		
	}
	public static void printarr(int[] num) {
		System.out.print("배열 : ");
		for(int i : num) {
			System.out.print(i +" ");
		}
		System.out.println();
		
	}


}
