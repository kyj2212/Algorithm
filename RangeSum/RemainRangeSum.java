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


        long cnt=0;
        int [] prefixSum = new int[N];
        int [] remain = new int[M];


        st = new StringTokenizer(br.readLine()," ");
        prefixSum[0]=Integer.parseInt(st.nextToken())%M;
        remain[prefixSum[0]]++;

        for (int i = 1; i < N; i++) {
            prefixSum[i]=(Integer.parseInt(st.nextToken())%M+prefixSum[i-1])%M;
            remain[prefixSum[i]]++;
        }

        for(int i=0;i<M;i++){
            if(remain[i]>1)  // prefix 가 같은경우
                cnt+=(((long)remain[i]*(remain[i]-1L))/2L);
        }
        bw.write(String.valueOf(cnt+(long)remain[0]));

        br.close();
        bw.flush();
        bw.close();

    }
}
