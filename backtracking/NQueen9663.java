package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class NQueen9663 {
    static int N;
    static int cnt =0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 모든 경우의 수 이기 때문에 dfs 로 간다.

//        dfs(0,new int[N][N],new int[N][N] );
        int[] queen = new int[N];
        for(int r=0;r<N;r++){
            queen[r]=-1;
        }

        dfs(0,queen);
        System.out.println(cnt);
    }
    static int[] dfs(int r,int[] queen){
        if(r==N){
/*            System.out.println(" 현재 row : "+r + " queen : ");
            for(int i=0;i<N;i++){
                System.out.printf("( "+i+","+queen[i]+" ) ");
            }
            System.out.println();*/
            cnt++;
            return queen;
        }
        // int[] queen = new int[N];
        for(int c=0;c<N;c++){

            if(isValid(r,c,queen)){
                queen[r]=c;
                queen=dfs(r+1,queen);
            }
            queen[r]=-1;

        }
        return queen;
    }
    static boolean isValid(int nr,int nc,int[] queen){
        int r,c;
        for(int i=0;i<N;i++){
           if(queen[i]==-1){
               break;
           }
           r=i;
           c=queen[i];
            if(r==nr| c==nc){
                return false;
            }
           for(int j=1;j<N;j++){
               if(r+j==nr && c+j == nc){
                   return false;
               }
               if(r-j==nr && c-j  == nc){
                   return false;
               }
               if(r-j==nr && c+j  == nc){
                   return false;
               }
               if(r+j==nr && c-j  == nc){
                   return false;
               }
           }
        }
        return true;
    }
/*
    static int[][] dfs(int x,int[][] queens,int[][] before){
        if(x==N){
            System.out.println("cnt : "+cnt);
            System.out.println("queen : ");
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(queens[i][j]==1){
                        System.out.printf("("+i+","+j+")");
                    }
                }
            }
            for(int[] queen : queens){
                System.out.printf("("+queen[0]+","+queen[1]+")");
            }
            System.out.println();
            cnt++;
            return queens;
        }
        // queen 이 놓이면 1
        // visited -1 (queen 못놓으면)
        // 가능하면 0 (초기값)
        for(int i =0;i<N;i++){

            if(queens[x][i]==0){
                int[][] tmp = queens;
                queens=checkValid(x,i,queens);
                queens[x][i]=1;
                queens=dfs(x+1,queens,before);
            }
            System.out.println(String.format("%d 번째 queen : ", x));
            for(int r=0;r<N;r++){
                for(int j=0;j<N;j++){
                    if(queens[r][j]==1){
                        System.out.printf("("+r+","+j+")");
                    }
                }
            }
            System.out.println();
           // x 번째로 back 했으니까 이전 valid 돌려야해
           queens=before;

        }
        return queens;
    }
*/


    // N*N 의 체스판에서 queen 서로 공격하지 못하게 놓는다.
    // queen 은 모든 방향으로 움직일 수 있다. x +- y +- x+y- x-y+ 4방향 이동거리 d N까지 가능
/*
    private static int[][] checkValid (int x, int y, int[][] queens) {

        for(int i =0;i<N;i++){
            queens[x][i]=-1;
            queens[i][y]=-1;
        }
        for(int i=1;i<N;i++){
            if(x+i<N && y+i<N){
                queens[x+i][y+i]=-1;
            }
            if(x+i<N && y-i>=0){
                queens[x+i][y-i]=-1;
            }
            if(x-i>=0 && y-i>=0){
                queens[x-i][y-i]=-1;
            }
            if(x-i>=0 && y+i<N){
                queens[x-i][y+i]=-1;
            }
        }
        return queens;
    }

*/

}
