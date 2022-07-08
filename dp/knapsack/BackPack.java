package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BackPack {
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken()); // 물품 수
        K = Integer.parseInt(st.nextToken()); // 무게
        int[] W = new int[N];
        int[] V = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            W[i]=Integer.parseInt(st.nextToken());
            V[i]=Integer.parseInt(st.nextToken());
        }

        br.close();
        System.out.println(maxItem(W,V));

    }

    static int maxItem (int [] W, int [] V){
        int[][] bag = new int[N][K+1];

        for (int i = 0; i < N; i++) {

            int prev,next;
            prev=i>0?i-1:0;

            for (int j = 0; j <=K; j++) {
                next=j+W[i];
                if(next<=K)
                    bag[i][next]=i>0?Math.max(bag[prev][next],bag[prev][j]+V[i]):V[i];
                if(i<N-1)
                    bag[i+1][j]=bag[i][j];
            }
        }

        // debugging
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K+1; j++) {
                System.out.printf(bag[i][j]+",");
            }
            System.out.println();
        }
        return bag[N-1][K];
    }

}
