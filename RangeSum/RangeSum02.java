package RangeSum;

import java.io.*;
import java.util.StringTokenizer;

public class RangeSum02 {

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb=new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] prefixSum=new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            prefixSum[i][0]=Integer.parseInt(st.nextToken());
            for (int j = 1; j < N; j++) {
                prefixSum[i][j]=prefixSum[i][j-1]+Integer.parseInt(st.nextToken());
            }
        }


        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int qsx=Integer.parseInt(st.nextToken())-1;
            int qsy=Integer.parseInt(st.nextToken())-1;
            int qex=Integer.parseInt(st.nextToken())-1;
            int qey=Integer.parseInt(st.nextToken())-1;
            // (qsx,qsy) ~ (qsx,qey) + (qex,qsy) ~ (qex,qey)
            int sum=0;
            for(int x=qsx;x<=qex;x++){
                sum+=prefixSum[x][qey]-(qsy>0?prefixSum[x][qsy-1]:0);
            }
            sb.append(sum).append("\n");

        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();


    }
}
