package dp;

import java.util.Arrays;

public class Triangle {
    public static void main(String[] args) {

        int[][] triangle = {{7}, {3, 8}, {8, 1, 0},{2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int size = triangle.length;
        int [][][] dp = new int[size+1][(int)Math.pow(2,size-1)][2];

        dp[0][0][0]=triangle[0][0];  // 0 + 0
        dp[0][0][1]=0;
        for(int i =0;i<size-1;i++){
            for(int j =0;j<(int)Math.pow(2,i+1);j+=2){
                // i+1=2 일때, 2^2=4 4개, dp[1] 은 = 0,1 2개 있고, t[2] = 0,1,2 3개 있고
                // dp[2][0] = dp[1][0] + t[2][0]
                // dp[2][1] = dp[1][0] + t[2][1]
                // dp[2][2] = dp[1][1] + t[2][1]
                // dp[2][3] = dp[1][1] + t[2][2]
                int k = dp[i][j/2][1]; // dp[2][4/2][1] = dp[2][2][1] = 1
                dp[i+1][j][0]=dp[i][j/2][0]+triangle[i+1][k];
                dp[i+1][j+1][0]=dp[i][j/2][0]+triangle[i+1][k+1];
                dp[i+1][j][1]=k;
                dp[i+1][j+1][1]=k+1;
                // i+1=3 일때, 2^3=8 8개, dp[2] 은 = 0,1,2,3  4개 있고, t[3] = 0,1,2,3  4개 있고
                // dp[3][0] = dp[2][0=j/2] + t[3][0=0/2] // 0
                // dp[3][1] = dp[2][0=1/2] + t[3][1=0/2+1] // 1
                // dp[3][2] = dp[2][1=2/2] + t[3][1]
                // dp[3][3] = dp[2][1=2/2] + t[3][2]
                // dp[3][4] = dp[2][2=4/2] + t[3][1]
                // dp[3][5] = dp[2][2=5/2] + t[3][2]
                // dp[3][6] = dp[2][6/2] + t[3][2]
                // dp[3][7] = dp[2][6/2] + t[3][3]

                //i+1 = 4 dp[4] => 16개
                // dp[3] = 0,1,2,3,4,5,6,7 8개
                // t[4] = 0,1,2,3,4 5개 있고
                // t[4][0]
                // t[4][1]
                // t[4][1]
                // t[4][2]
                // t[4][2]
                // t[4][3]
                // t[4][3]
                // t[4][4]
            }
        }
        Arrays.stream(dp[size-1]).forEach(i -> System.out.printf(i[0] + ","));
        System.out.println();
        int max =0;
        for(int [] i : dp[size-1]){
            max=Math.max(max,i[0]);
        }
        System.out.println(max);

    }

}
