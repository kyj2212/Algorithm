package search.bfs;


import java.util.LinkedList;
import java.util.Queue;

public class GameMapShortest {
    static int n,m;
    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};

        int answer = 0;
        n = maps.length;
        m = maps[0].length;


        Queue<int[]> q = new LinkedList<>();
        boolean [][] visited = new boolean[n][m];
        int depth=1;
        int x=0;
        int y=0;
        q.add(new int[]{depth,x,y});
        visited[0][0]=true;
        while(!q.isEmpty()){
            int [] node = q.poll();
            depth = node[0];
            x = node[1];
            y = node[2];

            if(x==n-1 && y==m-1){
                break;
            }

            if(canGo(x+1, y)){
                bfs(maps,visited,q,depth+1,x+1,y);
            }
            if(canGo(x, y+1)){
                bfs(maps,visited,q,depth+1,x,y+1);
            }
            if(canGo(x-1, y)){
                bfs(maps,visited,q,depth+1,x-1,y);
            }
            if(canGo(x, y-1)){
                bfs(maps,visited,q,depth+1,x,y-1);
            }

        }
        if(x!=n-1 || y!=m-1){
            depth=-1;
        }
        System.out.println(depth);

    }
    static boolean canGo(int x, int y){
        if(x>=0 && x<n && y>=0 && y<m)
            return true;
        else return false;
    }
    static void bfs(int[][] maps, boolean[][] visited,Queue<int[]> q, int depth, int x, int y){
        if(maps[x][y]!=0 && !visited[x][y]){
            q.add(new int[]{depth,x,y});
            visited[x][y]=true;
        }
    }
}
