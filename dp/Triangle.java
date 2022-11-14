package dp;

import java.util.Arrays;

public class Triangle {
    public static void main(String[] args) {

        int[][] triangle = {{7}, {3, 8}, {8, 1, 0},{2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int[] dp = new int[triangle.length];
        int[] prev = new int[triangle.length];
        dp[0]=triangle[0][0];
        for(int i=0;i< triangle.length;i++){
            prev = dp.clone();
            for(int j =0;j< i;j++){
                dp[j]=Math.max(dp[j],prev[j]+triangle[i][j]);
                dp[j+1]=Math.max(dp[j+1],prev[j]+triangle[i][j+1]);
            }
            Arrays.stream(dp).forEach(x -> System.out.printf(x+","));
            System.out.println();
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }

}
