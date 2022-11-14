package search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
        int answer=-1;
        boolean[][] visited = new boolean[dungeons.length][dungeons.length];

//        List<int[]> dunList= new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        for(int i =0;i< dungeons.length;i++){
//            dunList.add(dungeons[i]);
            queue.add(new int[]{k,0,0,dungeons[i][0],dungeons[i][1]});
        }
        answer =sol(dungeons.length,dungeons,queue,visited);

        System.out.println(answer);
    }


    private static int sol(int len, int[][] dungeons, Queue<int[]> queue, boolean[][] visited){
        int cnt =0;
        while(!queue.isEmpty()){

            int[] node=queue.poll();
            int k = node[0];
            int num = node[1];
            cnt = node[2];
            int[] dungeon= new int[]{node[3],node[4]};


            if(cnt==len){
                System.out.println(String.format("cnt : %d,  k : %d", cnt,k));
                return cnt;
            }

            for(int j=0;j<len;j++){
                if(visited[cnt][j]){
                    continue;
                }
                queue.add(new int[]{k,j,cnt,dungeons[j][0],dungeons[j][1]});
            }

            if(dungeon[0]<=k){
                k-=dungeon[1];
                System.out.println(String.format("cnt : %d, dungeon : %d %d k : %d", cnt,dungeon[0],dungeon[1],k));

                visited[cnt][num]=true;

                for(int j=0;j<len;j++){
                    if(visited[cnt][j]){
                        continue;
                    }
                    queue.add(new int[]{k,j,cnt+1,dungeons[j][0],dungeons[j][1]});
                }
            }
        }
        return cnt;
    }

/*    private static int dfs(int k, int len, int[][] dungeons, Queue<int[]> queue, int cnt,boolean[][] visited){
        if(cnt==len){
            return cnt;
        }
        if(queue.isEmpty()){
            System.out.println(String.format("cnt : %d,  k : %d", cnt,k));
            return cnt;
        }
        int tmpK = k;

        int[] node=queue.poll();
        int num = node[0];
        int[] dungeon= new int[]{node[1],node[2]};
        visited[cnt][num]=true;
        int tmpCnt = dfs(tmpK,len,dungeons,queue,cnt,visited);
        if(dungeon[0]<=k){
            k-=dungeon[1];
            System.out.println(String.format("cnt : %d, dungeon : %d %d k : %d", cnt,dungeon[0],dungeon[1],k));

            tmpCnt=Math.max(tmpCnt,dfs(k,len,dungeons,queue,cnt+1,visited));

        }
        for(int j=0;j<len;j++){
            if(visited[cnt][j]){
                continue;
            }
            queue.add(new int[]{cnt,dungeons[j][0],dungeons[j][1]});
        }
        return tmpCnt;
    }*/

}
