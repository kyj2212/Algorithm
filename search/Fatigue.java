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
//        int answer =dfs(k,dungeons,0,0);
        int answer = bfs(k,dungeons);
        System.out.println(answer);
    }

    private static int bfs(int k , int[][] dungeons){
        // 초기화
        int cnt =0;
        int[][] visited = new int[dungeons.length][dungeons.length];
        Queue<int[]> queue = new LinkedList<>();
        for(int i =0;i<dungeons.length;i++){
            queue.add(new int[]{k,cnt,i});
        }
        while(!queue.isEmpty()){

            int[] node = queue.poll();
            cnt=node[1];
            visited[cnt][node[2]]=1;
            int[] tmp = explore(dungeons,node);
            if(tmp[1]==dungeons.length){
                return tmp[1];
            }
            for(int i =0;i<dungeons.length;i++){ // visited[0]=0, visited[4]=1 이야 4번노드는 1이지만 3번노드가 들린게 아니야, 근데 자기와 같은 depth 인데 visited 이면 앞의 큐에서 계산햇을거야
                if(visited[tmp[1]][i]==1){
                    continue;
                }
                queue.add(new int[]{tmp[0],tmp[1],i});
            }
        }
        return cnt;
    }
    private static int[] explore(int[][] dungeons,int[] node){
        int k=node[0];
        int cnt=node[1];
        int[] dungeon = dungeons[node[2]];
        if(dungeon[0]<=k){
            k-=dungeon[1];
            cnt++;
        }
        return new int[]{k,cnt};
    }
/*
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
    }*/


}
