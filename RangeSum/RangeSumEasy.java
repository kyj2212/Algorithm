package RangeSum;

import java.io.*;
import java.util.StringTokenizer;

public class RangeSumEasy {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb=new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [] prefixSum = new int[N];
        st = new StringTokenizer(br.readLine()," ");
        prefixSum[0]=Integer.parseInt(st.nextToken());
        for (int i = 0; i < N-1; i++) {
            prefixSum[i+1]=prefixSum(Integer.parseInt(st.nextToken()),prefixSum[i]);
        }



        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int qs = Integer.parseInt(st.nextToken())-1;
            int qe = Integer.parseInt(st.nextToken())-1;
            sb.append(rangeSum(qs,qe, prefixSum)).append("\n");

           // bw.write(String.valueOf(rangeSum(qs,qe, prefixSum)));
            //bw.newLine();
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    private static int prefixSum(int input,int sum) {
        return sum+input;
    }


    static int rangeSum(int qs, int qe, int[] prefixSum){
        return prefixSum[qe]-(qs>0?prefixSum[qs-1]:0);
    }

}
