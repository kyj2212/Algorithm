package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class knapsack {

  static int n,W;
  static int wt[];
  static int val[];


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine()," ");

    n = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());

    //int wt[] = new int[n];
   // int val[] = new int[n];
    wt = new int[n];
    val = new int[n];

    for (int i = 0; i < n; i++) {

      st = new StringTokenizer(br.readLine()," ");
      wt[i]=Integer.parseInt(st.nextToken());
      val[i]=Integer.parseInt(st.nextToken());
    }


    System.out.print(BottomUp2array.knapSack(wt, val));

  }

}


class Memoization01 {

  static int [][] dp;

  static void initialize(int n, int W){

    dp = new int [n+1][W+1];
    for (int i = 0; i < n+1; i++) {
      for (int w = 0; w < W+1; w++) {
        dp[i][w]=-1;
      }
    }

  }

  static int knapsack (int n, int w){

    if(n==0||w==0)
      return dp[n][w]=0;

    return 0;
  }


}



class BottomUp2array {

  static int n= knapsack.n;
  static int W = knapsack.W;
  static int knapSack(int wt[], int val[]) {
    int i, w;
    int [][]K = new int[2][W + 1];
     

    for (i = 0; i <= n; i++) {
      for (w = 0; w <= W; w++) {

        K[0][w] = K[1][w]; // pre = current  , 이전 반복문의 current는 이제 prev 임

        if (i == 0 || w == 0) { // 1) 초기값 0 일때는 이전값이 없으니까 0
          K[0][w] = 0;
          continue; // 클린코드, continue로 시각화
        } else if (w - wt[i - 1] < 0) // w-wt[i-1] 이 0 보다 작으면 그 전값 그대로
          K[1][w] = K[0][w];

        else
          K[1][w] = Math.max(K[0][w - wt[i - 1]] + val[i - 1], K[0][w]);
      }
    }
    return K[1][W];
  }


}