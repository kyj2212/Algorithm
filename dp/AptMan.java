package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AptMan {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(br.readLine());
            int N = Integer.parseInt(br.readLine());

            int [][] dp = new int[K+1][N];
            for(int n=0;n<N;n++){
                dp[0][n]=n+1;
            }
            for(int k=1;k<=K;k++){

                for(int n=0;n<N;n++){
                    for(int j=0;j<=n;j++){
                        dp[k][n]+=dp[k-1][j];
                    }
                }
            }
            System.out.println(dp[K][N-1]);
        }
    }
}
