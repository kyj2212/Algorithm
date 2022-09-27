package search.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Miro {
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int [][] miro = new int[N+1][M+1];
        for(int i=1;i<=N;i++){
            String str = br.readLine();
            for(int j=1;j<=M;j++){
                miro[i][j]=str.charAt(j-1)-'0';
            }

        }
        for(int [] row : miro){
            for(int n : row){
                System.out.print(n+" ");
            }
            System.out.println();
        }
        boolean [][] visited = new boolean[N+1][M+1];
        Queue<int[]> q = new LinkedList<>();
        int depth=1;
        bfs(miro, visited,q,1,1,1);
        while(!q.isEmpty()){
            int[] node = q.poll();
            depth=node[0];
            int x= node[1];
            int y= node[2];
            if(x==N && y==M){
                break;
            }
            if(x+1>=1 && x+1<=N && y>=1 && y<=M ){
                bfs(miro,visited,q,x+1,y,depth+1);
            }
            if(x>=1 && x<=N && y+1>=1 && y+1<=M){
                bfs(miro,visited,q,x,y+1,depth+1);
            }
            if(x-1>=1 && x-1<=N && y>=1 && y<=M){
                bfs(miro,visited,q,x-1,y,depth+1);
            }
            if(x>=1 && x<=N && y-1>=1 && y-1<=M){
                bfs(miro,visited,q,x,y-1,depth+1);
            }
        }
        System.out.println(depth);
    }
    static int bfs(int[][] miro, boolean[][] visited, Queue<int[]> q, int x, int y, int depth){
        if(miro[x][y]!=0 && !visited[x][y]){
            q.add(new int[]{depth,x,y});
            visited[x][y]=true;
        }
        return depth;
    }

}
