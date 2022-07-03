package dp;


/*
* Dynamic Programming (동적계획법)
*
* 큰 문제를 작은 문제로 나누어 푸는 문제
* 작은 부분의 문제들이 반복됨(답이 바뀌지 않음)
* ## Dive and Conquer (분할 정복) : 작은 문제가 반복되지 않음
*
* 1. 풀이방법
* 작은 문제를 한번만 풀어야 함
* * 작은 문제 먼저 풀고 정답 메모 -> 큰문제에서 그 결과값 이용
*
* 2. 조건
* 작은 문제가 반복, 같은 문제는 구할때마다 정답이 같다
*
* 3. Memoization
* 한번 계산한 작은 문제를 적어놓고 다시 사용하는 것
*
* ex) 피보나치 : 점화식 : fn= fn-1 + fn-2
* * f0=0, f1=1, f2=1, f3=f1+f2, f4=f2+f3
* * f[0] =0 f[1]=1 f[2] =1 f[3] = f[1]+f[2], f[i] = f[i-1] +f[i-2]
*
*
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacci {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        br.close();


       System.out.println(FibSeq(n));
       /*
        * BufferedWriter 로 출력하기
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));   //할당된 버퍼에 값 넣어주기
        bw.write(FibSeq(n)+"\n");   //버퍼에 있는 값 전부 출력
        bw.flush();   //남아있는 데이터를 모두 출력시킴
        bw.close();
        */

    }

    static long FibSeq(int n) {
        long[] f = new long[n+1]; // 0 ~ n 까지의 배열 생성
        f[0]=0; f[1]=1;
        if(n<2)
            return f[n]; // 입력값이 0,1 일 경우 바로 return
        else {
            for(int i=2;i<n+1;i++)
                f[i]=f[i-1]+f[i-2]; // i 번째의 피보나치 수열의 값을 f[i] 에 저장
        }
        return f[n];
    }
}
