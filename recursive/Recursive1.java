package study.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Recursive1 {
	
	public static int cnt; // recursion Ƚ�� ������ ���� �������� cnt ����
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		
		for(int i =0 ; i<T ; i++)
		{
			String str = br.readLine();
			cnt =0; // �� str �� ���� recursion �� Ƚ�� �ʱ�ȭ
			sb.append(isPalindrome(str)).append(" ").append(cnt).append("\n"); // stirngbuilder ���
		}
		
		
		br.close();
		System.out.print(sb);
		
	}
	// �־��� ������ ���� �縰��� �Լ� -> �Ű������� string Ŭ������ ����
	public static int isPalindrome(String str) {
		return recursion(str, 0, str.length()-1);
	}
	public static int recursion(String str, int l, int r) {
		cnt++;
	    if(l >= r) return 1;
	    else if(str.charAt(l) != str.charAt(r)) return 0; // string Ŭ������ charAt() ��� vs char[] ��� �ӵ� ���� �ñ���
	    else return recursion(str, l+1, r-1);
	}
}
