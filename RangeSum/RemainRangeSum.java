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


        int [] prefixSum = new int[N];
        st = new StringTokenizer(br.readLine()," ");
        prefixSum[0]=Integer.parseInt(st.nextToken())%M;
        for (int i = 0; i < N-1; i++) {
            prefixSum[i+1]=(Integer.parseInt(st.nextToken()))%M+(prefixSum[i])%M;
        }


        int cnt=0;



        bw.write(String.valueOf(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
}
