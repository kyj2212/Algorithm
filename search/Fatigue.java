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
            return cnt;
        }

        int noUse = dfs(k,dungeons,i+1,cnt);
        cnt=explore(k,dungeons[i],cnt);
        int use = dfs(k,dungeons,i+1,cnt);
        cnt= Math.max(use,noUse);
       return cnt;
    }

    private static int explore(int k, int[] dungeon, int cnt){
        if(dungeon[0]<=k){
            cnt++;
            k-=dungeon[1];
        }
        return cnt;
    }
}
