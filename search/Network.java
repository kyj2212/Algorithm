package search;

import java.util.*;

public class Network {

    static int[] nodes;
    static int n;
    public static void main(String[] args) {

//        String str = "[1, 1, 0], [1, 1, 1], [0, 1, 1]";
//        str =str.replace('[','{');
//        str  =str.replace(']','}');
//        System.out.println(str);

        n = 5;
        int[][] computers = {{1,0, 0,1, 0}, {0,1, 0,1, 0}, {0,0, 1,0, 1}, {1,1, 0,1, 0}, {0,0, 1,0, 1}};
//        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
//        int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};

        int answer = 0;
        nodes = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = i;
        }

//        List<Integer> [] adj = new ArrayList[n];
//        for(int i=0;i<n;i++){
//            adj[i]=new ArrayList<>();
//            for(int j=0;j<n;j++){
//                if(i!=j && computers[i][j]==1){
//                    adj[i].add(j);
//                }
//            }
//        }
        System.out.println("start");

        Arrays.stream(nodes).forEach(x -> System.out.printf(x + " "));
        System.out.println();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i!=j && computers[i][j]==1){
                    union(i,j,computers);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i : nodes){
            if(!set.contains(i)){
                answer++;
                set.add(i);
            }
        }

        System.out.println("end");
        Arrays.stream(nodes).forEach(x -> System.out.printf(x + " "));
        System.out.println();

        System.out.println(answer);
    }
    private static void union(int a, int b, int[][] computers){

        a = find(a); // a의 대표값
        b = find(b); // b의 대표값

        if(a!=b){
            if(a<b){
                nodes[b]=a;
            }else{
                nodes[a]=b;
            }
        }

    }
    private static int find(int a){
        if(a ==nodes[a]){
            return a;
        }
        return nodes[a]=find(nodes[a]);
    }


//    private static void dfs(List<Integer>[] adj, int node,int[][] computers){
//        for(int c : adj[node]){
//            Arrays.stream(nodes).forEach(x -> System.out.printf(x + " "));
//            System.out.println();
//            if(nodes[c]==node){
//                continue;
//            }
//            if(computers[node][c]==1){
//                nodes[c]=node;
//                dfs(adj,nodes[c],computers);
//
//            }
//        }
//    }
}



//    Set<Integer> set = new HashSet<>();
//        for(int i : nodes){
//                if(!set.contains(i)){
//                answer++;
//                set.add(i);
//                }
//        }