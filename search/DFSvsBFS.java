package search;

import java.io.*;
import java.util.*;

public class DFSvsBFS {
/*


    static int N,M,V;
    static ArrayList<Integer> [] adjList;
    static boolean [] dfsVisited;
    static StringBuilder sb = new StringBuilder();


    // sol01 - 인접리스트
    public static void sol01() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        V=Integer.parseInt(st.nextToken());


        // adj list
        adjList = new ArrayList[N+1];
        for(int i =0;i<N+1;i++)
            adjList[i]=new ArrayList<>();

        // 그래프를 - 인접리스트로 표현
        // node에 대한 인접리스트
        // edge의 개수만큼 받는다.
        for(int i=0;i<M;i++){// edge에 이어져 있다면, 그것은 인접한 노드니까 인접리스트에 넣기
            st = new StringTokenizer(br.readLine()," ");
            int node1= Integer.parseInt(st.nextToken());
            int node2= Integer.parseInt(st.nextToken());

            adjList[node1].add(node2);
            adjList[node2].add(node1);
        }

        // 작은 순서대로 먼저 방문하기 위해 정렬
        for(int i=1;i<N+1;i++)
            Collections.sort(adjList[i]);
        // adj[i].sort(Comparator.comparingInt(a -> a));


        // 그래프 탐색
        dfsVisited = new boolean[N+1];
        dfs01(V);
        bw.write(sb.toString());
        bw.newLine();
        bfs01(V);
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();

    }
    private static void dfs01(int node) throws IOException {

        dfsVisited[node]=true;
        // 노드의 child로 가는거니까.
        if(node!=V)
            sb.append(" ");
        sb.append(node);
        for(int i : adjList[node]){
            if(!dfsVisited[i])
                dfs01(i);
        }
    }
    static void bfs01(int start) throws IOException {
        sb=new StringBuilder();

        // queue 에 넣는다.

        //String answer="";
        Queue<Integer> queue = new LinkedList<>();
        //  Queue<Integer> pq = new PriorityQueue<>(); // 이전에 넣은 queue가 먼저 실행되버려. 안돼
        // 작은값을 순서대로 하려면 adj를 정렬해야한다.

        boolean bfsVisited[]=new boolean[N+1];

        queue.add(start); // 초기화
        while(!queue.isEmpty()) {
            int node = queue.poll();
            bfsVisited[node]=true;
            if(node!=start)
                sb.append(" ");
            sb.append(node);
            for(int i : adjList[node])
                if(!queue.contains(i)&&!bfsVisited[i])
                    queue.add(i);
        }
    }






    public static void main(String[] args) throws IOException {


        sol01();



    }

*/
static int N,M,V;
   // static boolean [][] adjMatrix;
    static int [][] adjMatrix;
    static boolean [] dfsVisited;
    static BufferedWriter bw;


    public static void main(String[] args) throws IOException {

        sol02();


    }
    public static void sol02() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        V=Integer.parseInt(st.nextToken());

        // 인접 행렬
       // adjMatrix=new boolean[N+1][N+1];
        adjMatrix=new int[N+1][N+1];
        // 그래프를 - 인접 행렬로 표현
        // 정렬할 필요가 없지. 이미 순서대로 잇어.
        for(int i=0;i<M;i++){// edge에 이어져 있다면, 그것은 인접한 노드니까 인접리스트에 넣기
            st = new StringTokenizer(br.readLine()," ");
            int node1= Integer.parseInt(st.nextToken());
            int node2= Integer.parseInt(st.nextToken());
            // 양방향(무방향)
            adjMatrix[node1][node2]=1;
            adjMatrix[node2][node1]=1;
        }

        // 그래프 탐색
        dfsVisited = new boolean[N+1];
        dfs02(V);
        bw.newLine();
        bfs02(V);

        br.close();
        bw.flush();
        bw.close();


    }


    // 인접행렬 그래프탐색
    // DFS 로 탐색
    private static void dfs02(int node) throws IOException {
        dfsVisited[node]=true;
        // 노드의 child로 가는거니까.
        bw.write(String.valueOf(node));
        for(int i=1;i<N+1;i++){
            if(adjMatrix[node][i]==1&&!dfsVisited[i]){
                    bw.write(" "); dfs02(i);
            }
        }
    }

    // BFS
    static void bfs02(int start) throws IOException {

        Queue<Integer> queue = new LinkedList<>();

        boolean bfsVisited[]=new boolean[N+1];

        queue.add(start); // 초기화
        while(!queue.isEmpty()) {
            int node = queue.poll();
            bfsVisited[node]=true;
            if(node!=start)
                bw.write(" ");
            bw.write(String.valueOf(node));
            for(int i=1;i<N+1;i++){
                if(adjMatrix[node][i]==1){
                    if(!queue.contains(i)&&!bfsVisited[i])
                        queue.add(i);
                }
            }
        }
    }


}


class DFSandBFS02{



}
