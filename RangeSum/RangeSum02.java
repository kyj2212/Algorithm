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



        int[][] prefixSum=new int[N+1][N+1];

        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine()," ");
            for (int j = 1; j < N+1; j++) {
                //prefixSum[i][j]=(j<1?0:prefixSum[i][j-1])+(i<1?0:prefixSum[i-1][j])-((i<1||j<1)?0:prefixSum[i-1][j-1])+Integer.parseInt(st.nextToken());
                prefixSum[i][j]=(prefixSum[i][j-1])+(prefixSum[i-1][j])-(prefixSum[i-1][j-1])+Integer.parseInt(st.nextToken());
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
            int qsx=Integer.parseInt(st.nextToken());
            int qsy=Integer.parseInt(st.nextToken());
            int qex=Integer.parseInt(st.nextToken());
            int qey=Integer.parseInt(st.nextToken());
            int sum=prefixSum[qex][qey] - (prefixSum[qsx-1][qey]) -(prefixSum[qex][qsy-1]) +(prefixSum[qsx-1][qsy-1]);
            sb.append(sum).append("\n");
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();


    }
}
