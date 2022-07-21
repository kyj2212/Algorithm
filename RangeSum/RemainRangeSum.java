package RangeSum;

import java.io.*;
import java.util.StringTokenizer;

public class RemainRangeSum {

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        // 구간의 누적합 을 구하고
        int [] prefixSum = new int[N];
        int [] input = new int[N];
        st = new StringTokenizer(br.readLine()," ");

        //prefixSum[0]=Integer.parseInt(st.nextToken());
        input[0]=Integer.parseInt(st.nextToken())%M;
        prefixSum[0]=input[0];

        int [] remain = new int [M];
        remain[input[0]]++;


        for (int i = 0; i < N-1; i++) {
            input[i+1]=Integer.parseInt(st.nextToken())%M;
            remain[input[i+1]]+=1;
            prefixSum[i+1]=(input[i+1]%M+prefixSum[i])%M;
            //prefixSum[i+1]=Integer.parseInt(st.nextToken())+prefixSum[i];
        }
        // 누적합이 0이 아니지만, 구간합이 0인경우가 있는가?
        // (i,j) si=2, sj=1 sj-si=-1=(3-1)=2
        // s(i,j)=sj-si
        // s(i,j)%M = M()+r
        // M()+r - M()+r ??

        // x+y=n 일때, (x%M + y%M)%M = n 인가? X
        // x=M*()+r, y=M*()+r x+y (r+r) ...
        // r==0 이라면?
        // x+y=M(()) ..!
        // r==0 인경우는 무조건 0,아닌경우는 체크.
        for(int i =0;i<N;i++){
            System.out.printf(input[i]%M+" ");
        }
        System.out.println();
        for(int i : prefixSum)
            System.out.printf(i+" ");
        System.out.println();
        // 모든 구간은 O(n*n)
        // prefixSum[i]-prefixSum[j] (i,j)
        // 나머지?

        int cnt=0;
        // 구간, (i,j)에 대해
        for (int i=0;i<M;i++){
            System.out.printf(remain[i]+" ");
        }

        // 모든 구간합의 조합 N (1,1) (N,N)

        for(int s=0;s<N;s++){
            if(prefixSum[s]==0){
                for (int e = s+1; e < N; e++) {
                    cnt+=prefixSum[e]==0?1:0;
                }
            }else{
                for (int e = s+1; e < N; e++) {
                        if((prefixSum[e]-(s<1?0:prefixSum[s-1]))%M==0)
                            cnt++;
                }
            }
        }

        System.out.println(cnt);
        bw.write(String.valueOf(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
}
