package study.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Recursive1 {
	
	public static int cnt; // recursion 횟수 저장을 위한 전역변수 cnt 지정
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		
		for(int i =0 ; i<T ; i++)
		{
			String str = br.readLine();
			cnt =0; // 각 str 에 따라 recursion 의 횟수 초기화
			sb.append(isPalindrome(str)).append(" ").append(cnt).append("\n"); // stirngbuilder 사용
		}
		
		
		br.close();
		System.out.print(sb);
		
	}
	// 주어진 문제에 나온 펠린드론 함수 -> 매개변수는 string 클래스로 지정
	public static int isPalindrome(String str) {
		return recursion(str, 0, str.length()-1);
	}
	public static int recursion(String str, int l, int r) {
		cnt++;
	    if(l >= r) return 1;
	    else if(str.charAt(l) != str.charAt(r)) return 0; // string 클래스의 charAt() 사용 vs char[] 사용 속도 차이 궁금함
	    else return recursion(str, l+1, r-1);
	}
}
