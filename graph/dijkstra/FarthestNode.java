package graph.dijkstra;

import java.util.*;

public class FarthestNode {
    public static void main(String[] args) {
        int n=6;
        int[][] edge ={{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        // 인접 리스트 구현하기

        ArrayList<Integer> adj [] = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            adj[i]=new ArrayList<>();
        }
        for(int i =0;i<edge.length;i++){
            adj[edge[i][0]].add(edge[i][1]);
            adj[edge[i][1]].add(edge[i][0]);
        }
//        for(ArrayList list : adj){
//            System.out.println(list);
//        }

        // 최단거리 배열 초기화하기

        int[] dist = new int[n+1];
        for(int i =2;i<=n;i++){
            dist[i]=900000; // 큰수로 초기화
        }

        //int min=1;
        boolean [] visited = new boolean[n+1];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        while(!queue.isEmpty()){
            int node=queue.poll();
            visited[node]=true;
            for(int next : adj[node]){
                dist[next]=Math.min(dist[next],dist[node]+1);
                if(!visited[next]){
                    queue.add(next);
                }
            }
        }
        int max =Arrays.stream(dist).max().getAsInt();
        int cnt =0;
        for(int i : dist){
            if(i==max){
                cnt++;
            }
        }
        System.out.println(cnt);


    }
}
