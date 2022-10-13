package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ladder{
    static int N,M,H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        H=Integer.parseInt(st.nextToken());

        int[][] ladder = new int[N+1][H+1];
        for(int i =1;i<=M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 왼쪽만 있으면 -1, 오른쪽만 있으면 1, 둘다 없으면 0, 둘다 있는 경우는 없다
            ladder[b][a]=1; // b번 - b+1번 세로줄 사이의 a 번째 가로줄이 존재
            if(b+1<=N)
                ladder[b+1][a]=-1; // b번 - b+1번 세로줄 사이의 a 번째 가로줄이 존재
        }
//        printLadder(ladder);
        int cnt = dfs(0,ladder,0);
        System.out.println(cnt);
    }


    private static int dfs(int cnt, int[][] ladder,int depth){

        if(isSame(ladder)){
            System.out.println(String.format("성공 : cnt : %d, ",cnt));
            printLadder(ladder);
            return cnt;
        }
        for(int v=1;v<=N;v++){
            for(int h=1;h<=H;h++){
                int[][] tmp = addLine(ladder,v,h);
                if(tmp==null){
                    continue;
                }
                ladder=tmp;
                cnt++;
                System.out.println(String.format("cnt: %d, (%d,%d) 추가",cnt, v,h));
                System.out.println(String.format("ladder size: %d, cnt: %d", count(ladder)-M,cnt));
                if(isSame(ladder)){
                    printLadder(ladder);
                    return cnt;
                }
                if(cnt<3){
                    depth = dfs(cnt,ladder,depth);
                    System.out.println(String.format("depth: %d, cnt: %d", depth,cnt));
                    if(depth!=-1){
                        System.out.println(String.format("depth: %d가 -1이 아닌경우", depth));
                        return depth;
                    }

                }
                ladder=delLine(ladder,v,h);
                cnt--;
                System.out.println(String.format("cnt: %d, (%d,%d) 제거", cnt,v,h));
                System.out.println(String.format("ladder size: %d, cnt: %d", count(ladder)-M,cnt));
            }
        }
        System.out.println(String.format("cnt : %d,다돌았는데 no same, cnt>=3, cnt -1 반환 ", cnt));
        return -1;
    }

    private static boolean isSame(int[][] ladder){
        for(int v=1;v<=N;v++){
            if(!down(ladder,v)){
                System.out.println(String.format("%d 번줄 실패", v));
                return false;
            }
        }
        return true;
    }

    private static int[][] addLine(int[][] ladder, int v, int h){

        if(v<N && ladder[v][h]==0 && ladder[v+1][h]==0){
            ladder[v][h]=1;
            ladder[v+1][h]=-1;
            return ladder;
        }
        return null;
    }
    private static int[][] delLine(int[][] ladder, int v, int h){
        if(v<N){
            ladder[v+1][h]=0;
        }
        ladder[v][h]=0;
        return ladder;
    }

    private static boolean down(int[][] ladder,int v){
        // v 번째 세로줄의 세로줄 타기
        int i = v;
        for(int j=1;j<=H;j++){

            if(ladder[i][j]==0){
                continue;
            }
            if(ladder[i][j]==1){
                System.out.println(String.format("[1] %d번 줄의 %d번 가로줄을 타고 내려감", i,j));
                i++;
                continue;
            }
            if(ladder[i][j]==-1){
                System.out.println(String.format("[2] %d번 줄의 %d번 가로줄을 타고 내려감", i,j));
                i--;
                continue;
            }
        }
        if(v!=i){
            System.out.println(String.format("사다리결과 : %d 같지않음 %d", v,i));
            return false;
        }
        System.out.println(String.format("사다리결과 : %d 같음!!! %d", v,i));
        return true;

    }

    private static int count(int[][] ladder){
        int cnt =0;
        for(int i =1;i<=N;i++){
            for (int j=1;j<=H;j++){
                if(ladder[i][j]==1){
                    cnt++;
                }
            }
        }
        return cnt;
    }
    private static void printLadder(int[][] ladder){
        for(int i =1 ;i<=N;i++){
            for(int j=1;j<=H;j++){
                System.out.printf(String.format("%10d,", ladder[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }
}
