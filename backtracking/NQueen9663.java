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

        dfs(0,queen,new boolean[N][N]);
        System.out.println(cnt);
    }
    static int[] dfs(int r,int[] queen, boolean[][] inValid){
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

            if(!inValid[r][c]){
                queen[r]=c;
                boolean[][] backValid = back(inValid);
                inValid=checkValid(r,c,inValid);
                queen=dfs(r+1,queen,inValid);
                inValid=backValid;
            }
            queen[r]=-1;
        }
        return queen;
    }

    private static boolean[][] back(boolean[][] inValid){
        boolean[][] back=new boolean[N][N];
        for(int i =0;i<N;i++){
            for(int j =0;j<N;j++){
                back[i][j]=inValid[i][j];
            }
        }
        return back;
    }
/*
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
*/

    // N*N 의 체스판에서 queen 서로 공격하지 못하게 놓는다.
    // queen 은 모든 방향으로 움직일 수 있다. x +- y +- x+y- x-y+ 4방향 이동거리 d N까지 가능
    private static boolean[][] checkValid (int x, int y, boolean[][] inValid) {

        for(int i =0;i<N;i++){
            inValid[x][i]=true;
            inValid[i][y]=true;
        }
        for(int i=1;i<N;i++){
            if(x+i<N && y+i<N){
                inValid[x+i][y+i]=true;
            }
            if(x+i<N && y-i>=0){
                inValid[x+i][y-i]=true;
            }
            if(x-i>=0 && y-i>=0){
                inValid[x-i][y-i]=true;
            }
            if(x-i>=0 && y+i<N){
                inValid[x-i][y+i]=true;
            }
        }
        return inValid;
    }

}
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
