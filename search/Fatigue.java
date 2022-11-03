package search;

import java.util.LinkedList;
import java.util.Queue;

public class Fatigue {
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80,20},{50,40},{30,10}};
//        String str = "[80,20],[50,40],[30,10]";
//        str =str.replace('[','{');
//        str  =str.replace(']','}');
//        System.out.println(str);


        // 최소 필요 피로도
        // 소모 피로도
        boolean[] visited = new boolean[dungeons.length];
        int answer =dfs(k,dungeons,0,0);
        System.out.println(answer);
    }

    private static int dfs(int k, int[][]dungeons, int i,int cnt){
        if(i==dungeons.length){
            System.out.println(String.format("cnt : %d,  k : %d", cnt,k));
            return cnt;
        }
        int tmpK = k;

        int[] dungeon=dungeons[i];
        int tmpCnt = cnt;
        if(dungeon[0]<=k){
            k-=dungeon[1];
            System.out.println(String.format("cnt : %d, dungeon : %d %d k : %d", cnt,dungeon[0],dungeon[1],k));
            tmpCnt=dfs(k,dungeons,i+1,cnt+1);
        }

       return Math.max(tmpCnt,dfs(tmpK,dungeons,i+1,cnt));
    }


}
