package showme.s01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
*
* C번 - 수들의 합 8 실패
시간 제한	메모리 제한
1 초	512 MB
문제
길이가 같은 정수 수열 $A = \{A_1, A_2, ..., A_N\}$와 $B = \{B_1, B_2, ..., B_N\}$가 주어진다.

 $A_i + A_{i+1} + ... + A_j = B_i + B_{i+1} + ... + B_j$를 만족하는 양의 정수 $i, j ~(i \leq j)$쌍의 개수를 구해보자.

입력
첫 번째 줄에 양의 정수 $N$이 주어진다.

두 번째 줄에 $A$를 나타내는 $N$개의 양의 정수 $A_i$가 $A_1$부터 $A_N$까지 순서대로, 공백으로 구분되어 주어진다.

세 번째 줄에 $B$를 나타내는 $N$개의 양의 정수 $B_i$가 $B_1$부터 $B_N$까지 순서대로, 공백으로 구분되어 주어진다.

출력
 $A_i + A_{i+1} + ... + A_j = B_i + B_{i+1} + ... + B_j$를 만족하는 양의 정수 $i, j ~(i \leq j)$쌍의 개수를 출력한다.

제한
 $1 \leq N \leq 2\cdot 10^5$ 
 $\lvert A \rvert = \lvert B \rvert = N$ 
 $1 \leq A_i,B_i \leq 10^4$ 
* */


class Main {

    static int N;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        int[] a = new int[N];
        int[] b = new int[N];
        st = new StringTokenizer(br.readLine()," ");
        StringTokenizer stb = new StringTokenizer(br.readLine()," ");

        for (int i = 0; i < N; i++) {
            a[i]=Integer.parseInt(st.nextToken());
            b[i]=Integer.parseInt(stb.nextToken());
        }



        br.close();

        System.out.println(sameSum(a,b));
    }

    static int sameSum(int[] a, int[] b){
        long[] aSum= new long[N*N];
        long[] bSum= new long[N*N];
        int cnt=0;

        for (int i = 0; i < N; i++) {
            aSum[i*(N-1)+i+1]=aSum[i*(N-1)+i]+a[i];
            bSum[i*(N-1)+i+1]=bSum[i*(N-1)+i]+b[i];


        }
        for (int i = 0; i < N*N; i++) {
            if(aSum[i]==bSum[i]&&aSum[i]!=0)
                cnt++;
        }

        for (long i :aSum) {
            System.out.printf(i+",");
        }
        System.out.println();
        for (long i :bSum) {
            System.out.printf(i+",");
        }
        System.out.println();

        return cnt;
    }


}


public class showme01 {

}