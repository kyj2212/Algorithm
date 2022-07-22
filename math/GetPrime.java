package math;

import java.io.*;
import java.util.StringTokenizer;

public class GetPrime {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb= new StringBuilder();
        int M=Integer.parseInt(st.nextToken());
        int N=Integer.parseInt(st.nextToken());

        int [] prime = new int[N+1];
        for (int i = (M>1?M:2); i <=N ; i++) { // i=M 부터 했더니 실패, 왜? M=1 일때, 1이 들어가는구나
            prime[i]=i;
        }

        for(int i=2;i<=Math.sqrt(N);i++){  //  x < Math.sqrt(N) <x+1 일때, x로 하면된다. 어짜피 마지막 값 N < (x+1)*(x+1) 이므로, (x+1) 의 순서를 볼필요가 없음
            for (int j = i; i*j<N+1 ; j++) {
                if(i*j<M)
                    continue;
                prime[i*j]=0;
            }
        }

        for(int i : prime){
            if(i!=0&&i>=M) {
                sb.append(i).append("\n");
            }
        }

        br.close();
        System.out.println(sb.toString());
    }
}
