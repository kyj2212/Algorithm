package RangeSum;

import java.io.*;
import java.util.StringTokenizer;

public class RangeSumEasy {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [] input=new int[N];
        st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < N; i++) {
            input[i]=Integer.parseInt(st.nextToken());
        }

        int [] prefixSum = prefixSum(input);

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int qs = Integer.parseInt(st.nextToken())-1;
            int qe = Integer.parseInt(st.nextToken())-1;
            bw.write(String.valueOf(rangeSum(qs,qe,input,prefixSum)));
            bw.newLine();
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static int[] prefixSum(int[] input) {
        int [] sum= new int[input.length];
        sum[0]=input[0];
        for(int i=0;i<input.length-1;i++){
            sum[i+1]=sum[i]+input[i+1];
        }
        return sum;
    }


    static int rangeSum(int qs, int qe, int[] input, int[] prefixSum){
        return prefixSum[qe]-(qs>0?prefixSum[qs-1]:0);
    }

}
