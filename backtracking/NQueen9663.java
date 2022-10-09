package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NQueen9663 {
    static int N;
    static int cnt =0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 모든 경우의 수 이기 때문에 dfs 로 간다.

        dfs(0,new int[N][2] );
        System.out.println(cnt);
    }
    static int[][] dfs(int x,int[][] queens){
        if(x==N){
/*            System.out.println("cnt : "+cnt);
            System.out.println("queen : ");
            for(int[] queen : queens){
                System.out.printf("("+queen[0]+","+queen[1]+")");
            }
            System.out.println();*/
            cnt++;
//            return queens;
            return queens;
        }
        for(int i =0;i<N;i++){
            for(int q=N-1;q>=x;q--) {
                queens[q][0] = 0;
                queens[q][1] = 0;
            }
            if(isValid(x,i,queens)){
                queens[x][0]=x;
                queens[x][1]=i;
                queens=dfs(x+1,queens);

            }
        }
        return queens;
    }


    // N*N 의 체스판에서 queen 서로 공격하지 못하게 놓는다.
    // queen 은 모든 방향으로 움직일 수 있다. x +- y +- x+y- x-y+ 4방향 이동거리 d N까지 가능
    private static boolean isValid (int cx, int cy, int[][] queens) {

        for(int n=0;n<cx;n++){
            int[] queen=queens[n];
            int x = queen[0];
            int y = queen[1];
            if(cx==x || cy==y ){

                return false;
            }
            for(int i=1;i<N;i++){
                if((cx==x+i && cy==y+i) ||(cx==x-i && cy==y-i) ||(cx==x+i && cy==y-i) ||(cx==x-i && cy==y+i)  ){

                    return false;
                }
            }
        }
        return true;
    }


}
