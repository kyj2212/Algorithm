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
            for (int j = 0; j < N; j++) {
                //int next =Integer.parseInt(st.nextToken());
                prefixSum[i][j]=(j<1?0:prefixSum[i][j-1])+(i<1?0:prefixSum[i-1][j])-((i<1||j<1)?0:prefixSum[i-1][j-1])+Integer.parseInt(st.nextToken());
            }
        }
/*
        for(int [] arr : prefixSum){
            for(int i : arr)
                System.out.printf(i+" ");
            System.out.println();
        }

*/
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int qsx=Integer.parseInt(st.nextToken())-1;
            int qsy=Integer.parseInt(st.nextToken())-1;
            int qex=Integer.parseInt(st.nextToken())-1;
            int qey=Integer.parseInt(st.nextToken())-1;
            // s(qex,qey) - s(qsx-1,qey) - s(qex,qsy-1) + s(qsx-1,qsy-1)

            int sum=prefixSum[qex][qey] - (qsx<1?0:prefixSum[qsx-1][qey]) -(qsy<1?0:prefixSum[qex][qsy-1]) +((qsx<1||qsy<1)?0:prefixSum[qsx-1][qsy-1]);

            sb.append(sum).append("\n");

        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();


    }
}
