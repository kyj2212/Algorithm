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

        // prime[i] , isPrime : false, noPrime : true
        boolean [] prime = new boolean[N+1];
        prime[0]=prime[1]=true;
        for(int i=2;i<=N;i++){  //  x < Math.sqrt(N) <x+1 일때, x로 하면된다. 어짜피 마지막 값 N < (x+1)*(x+1) 이므로, (x+1) 의 순서를 볼필요가 없음
            if(prime[i]) // 이걸 모르겠어. i 가 true면, i 는 소수가 아닌거잖아. 4 야. 2때문에 4는 true 잖아. 4의 배수는 안해? 아.. 할필요없구나. 4의 배수는 2의 배수니까.
                continue;
            if(i>=M)
                sb.append(i).append("\n");
            if(i>Math.sqrt(N))
                continue;
            for (int j = i*i; j<N+1 ; j+=i) {
                prime[j]=true;
            }
        }


        System.out.println(sb.toString());
        br.close();
    }
}
