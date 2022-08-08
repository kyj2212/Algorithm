package RangeSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RangeSum04 {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int [] prefixSum= new int[N];
        st= new StringTokenizer(br.readLine(), " ");
        for(int i =0;i<N;i++){

            int num= Integer.parseInt(st.nextToken());
            int prevSum = i>0?prefixSum[i-1]:0;
            prefixSum[i]=prevSum+num;
//            prefixSum[i]=(i>0?prefixSum[i-1]:0)+num;
//            prefixSum[i]=(i>0?prefixSum[i-1]:0)+Integer.parseInt(st.nextToken());
        }

        int cnt=0;
        for (int i = 0; i < N; i++) {
            for(int j=i;j<N;j++){
                int sum = prefixSum[j]-(i>0?prefixSum[i-1] :0 );
                if(sum==K)
                    cnt++;
            }

        }

        System.out.println(cnt);
    }




}
