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

    static int sameSum(int[] a, int[] b){ // 시간 초과
        long aSum=0;
        long bSum=0;            // sameSum04 보다 메모리 줄임. 배열 없앰
        int cnt=0;

        for (int i = 0; i < N; i++) { // N*N 안됨

            for (int j = i; j < N; j++) {

                aSum+=a[j];
                bSum+=b[j];
                if(aSum==bSum)
                    cnt++;
            }
        }

        return cnt;
    }


    static int sameSum__(int[] a, int[] b){ // 중도포기
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


    static int sameSum04(int[] a, int[] b){ // 시간 초과
        int [] aSum = new int[N];
        int [] bSum = new int[N];

        int cnt=0;

        for (int i = 0; i < N; i++) { // N*N 안됨

            for (int j = i; j < N; j++) {

                aSum[i]+=a[j];
                bSum[i]+=b[j];
                if(aSum[i]==bSum[i])
                    cnt++;
            }
        }

        return cnt;
    }

    static int sameSum03(int[] a, int[] b){ // 메모리 초과
        int [][] aSum = new int[N][N]; // 2차 배열 메모리 초과
        int [][] bSum = new int[N][N];

        int cnt=0;

        for (int i = 0; i < N; i++) { // 시간도 초과 N*N 임

            aSum[i][i]=a[i];
            bSum[i][i]=b[i];

            if(a[i]==b[i]){
                cnt++;
            }
            for (int j = 1; j < N; j++) {

                aSum[i][j]=aSum[i][j-1]+a[j];
                bSum[i][j]=bSum[i][j-1]+b[j];

                if(aSum[i][j]==bSum[i][j])
                    cnt++;


            }
        }

        return cnt;
    }



    static int sameSum02(int[] a, int[] b){ // 메모리 초과
        int [][] aSum = new int[N][2*N]; // 2차 배열 메모리 무조건 초과
        int [][] bSum = new int[N][2*N];

        int cnt=0;

        for (int i = 0; i < N; i++) { // N*N 이라서 시간도 초과했을 듯

            aSum[i][i]=a[i];
            bSum[i][i]=b[i];

            if(a[i]==b[i]){
                cnt++;
            }
            for (int j = i+1; j < N; j++) {

                aSum[i][j]=aSum[i][j-1]+a[j];
                bSum[i][j]=bSum[i][j-1]+b[j];

                if(aSum[i][j]==bSum[i][j])
                    cnt++;


            }
        }

        return cnt;
    }


    static int sameSum01(int[] a, int[] b){ // 시간초과, 메모리도 초과했을 것 같음
        int [] aSum = new int[2*N];
        int [] bSum = new int[2*N];

        int cnt=0; // 불필요하게 for 3번, N*N*(logN)
        for (int i = 0; i < N; i++) {
            if(a[i]==b[i])
                cnt++;
            for (int j = i+1; j < N; j++) {
                for (int k = i; k < j+1; k++) {
                    aSum[i+j]+=a[k];
                    bSum[i+j]+=b[k];
                }
                if(aSum[i+j]==bSum[i+j])
                    cnt++;
            }
        }
        return cnt;
    }
}


public class showme01 {

}