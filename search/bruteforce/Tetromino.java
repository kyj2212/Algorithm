
package search.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tetromino {

    static int N,M;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        int[][] board = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<M;j++){
                board[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(findMaxSum(board));

    }

    private static int findMaxSum(int[][] board){
        int result=0;
        for(int i =1;i<=5;i++){
            for(int x=0;x<N;x++){
                for(int y=0;y<M;y++){
                    int sum = poly(i,board,x,y);
                    if(sum==-1){
                        continue;
                    }
                    result = Math.max(sum, result);
                }
            }
        }
        return result;
    }


    private static int poly(int type, int[][] board,int x, int y){


        // 일자
        if(type==1){
            int sum  = -1;
            // y, y+1, y+2, y+3
            if(y+3<M){
                sum = Math.max(sum,board[x][y] + board[x][y+1] + board[x][y+2] + board[x][y+3]);
            }
            // x, x+1, x+2, x+3
            if(x+3<N){
                sum= Math.max(sum,board[x][y] + board[x+1][y]+board[x+2][y]+board[x+3][y]);
            }

            return sum;
        }
        //
        if(type==2){
            // x, x+1,y y+1,x x+1,y+1
            int sum = -1;
            if(x+1<N && y+1 <M){
                sum=Math.max(sum, board[x][y] + board[x+1][y] + board[x][y+1] + board[x+1][y+1]);
            }
            return sum;
        }
        //
        if(type==3){
            int sum = -1;
            //x x+1,y x+2,y x+2,y+1
            if(x+2 < N && y+1 < M){
                sum = Math.max(sum, board[x][y]+board[x+1][y]+board[x+2][y]+board[x+2][y+1]);
            }
            // x, x+1,y x+2,y y-1,x+2
            if(x+2 < N && y-1>=0 ){
                sum = Math.max(sum,board[x][y] + board[x+1][y] + board[x+2][y] + board[x+2][y-1] );
            }

            // x,y x,y+1 x,y+2, x+1,y
            if(x+1 < N && y+2 <M){
                sum = Math.max(sum, board[x][y] + board[x][y+1] + board[x][y+2] + board[x+1][y]);
            }

            // x x+1,y x+1,y+1 x+1,y+2
            if(x+1 < N && y+2 <M){
                sum = Math.max(sum, board[x][y] + board[x+1][y] + board[x+1][y+1] + board[x+1][y+2]);
            }

            // x,y x,y+1 x,y+2 x-1,y+2
            if(x-1 >=0 && y+2 <M) {
                sum = Math.max(sum, board[x][y] + board[x][y+1] + board[x][y+2] + board[x-1][y+2]);
            }

            // x,y x,y+1 x,y+2 x+1,y+2
            if(x+1 < N && y+2 <M){
                sum = Math.max(sum, board[x][y] + board[x][y+1] + board[x][y+2] + board[x+1][y+2]);
            }
            // x,y x,y+1 x+1,y+1 x+2,y+1
            if(x+2<N && y+1 <M){
                sum = Math.max(sum, board[x][y] + board[x][y+1] + board[x+1][y+1] + board[x+2][y+1]);
            }

            // x,y x,y+1 x+1,y x+2,y
            if(x+2<N && y+1 <M){
                sum = Math.max(sum, board[x][y] + board[x][y+1] + board[x+1][y] + board[x+2][y]);
            }

            return sum;
        }
        //
        if(type==4){
            int sum = -1;
            // x,y x+1,y x+1,y+1 x+2,y+1
            if(x+2< N && y+1 <M){
                sum = Math.max(sum, board[x][y] + board[x+1][y] + board[x+1][y+1] + board[x+2][y+1]);
            }

            // x,y x+1,y x+1,y-1 x+2 y-1
            if(x+2 <N && y-1 >=0){
                sum = Math.max(sum, board[x][y]+board[x+1][y] + board[x+1][y-1] + board[x+2][y-1]);
            }

            // x,y x,y+1 x+1 y+1 x+1 y+2
            if(x+1<N && y+2 <M){
                sum = Math.max(sum, board[x][y] + board[x][y+1] + board[x+1][y+1] + board[x+1][y+2]);
            }

            // x,y x,y+1 x-1,y+1 x-1,y+2
            if(x-1>=0 && y+2 <M){
                sum = Math.max(sum, board[x][y] + board[x][y+1] + board[x-1][y+1] + board[x-1][y+2]);
            }

            return sum;
        }
        //
        if(type==5){
            int sum  = -1;
            // x,y x,y+1 x,y+2 x+1,y+1
            if(x+1<N && y+2<M){
                sum = Math.max(sum, board[x][y] + board[x][y+1] + board[x][y+2] + board[x+1][y+1]);
            }

            // x,y x+1,y x+1,y-1 x+1,y+1
            if(x+1 < N && y-1 >=0 && y+1 <M){
                sum = Math.max(sum, board[x][y] + board[x+1][y] + board[x+1][y-1] + board[x+1][y+1]);
            }


            // x,y x,y+1 x-1,y+1 x+1,y+1
            if(x-1>=0 && x+1 <N && y+1 <M){
                sum = Math.max(sum, board[x][y] + board[x][y+1] + board[x-1][y+1] + board[x+1][y+1]);
            }

            // x,y x+1,y x+2,y x+1,y+1
            if(x+2< N && y+1 <M){
                sum = Math.max(sum,board[x][y] + board[x+1][y] + board[x+2][y] + board[x+1][y+1]);
            }

            return sum;
        }
        return -1;
    }

}
