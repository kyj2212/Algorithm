package dp;

import java.util.Arrays;

public class Triangle {
    public static void main(String[] args) {

        int[][] triangle = {{7}, {3, 8}, {8, 1, 0},{2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int size = triangle.length;
        int [][] dp = new int[3][(int)Math.pow(2,size-1)];
        dp[0][0]=triangle[0][0];

        for(int i =0;i<size-1;i++){

            for(int j =0;j<(int)Math.pow(2,i+1);j+=2){
                int k = dp[2][j/2];
                dp[1][j]=dp[0][j/2]+triangle[i+1][k];
                dp[1][j+1]=dp[0][j/2]+triangle[i+1][k+1];
                dp[2][j]=k;
                dp[2][j+1]=k+1;
            }
            for(int j =0;j<(int)Math.pow(2,i);j++){
                dp[0][j]=dp[1][j];
            }

//            Arrays.stream(dp[1]).forEach(x -> System.out.printf(x + ","));
//            System.out.println();

        }

        System.out.println(Arrays.stream(dp[1]).max().getAsInt());

    }

}
