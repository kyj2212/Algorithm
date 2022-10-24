package search;

import java.util.*;

public class Network {

    static int[] nodes;
    public static void main(String[] args) {

//        String str = "[1, 1, 0], [1, 1, 1], [0, 1, 1]";
//        str =str.replace('[','{');
//        str  =str.replace(']','}');
//        System.out.println(str);

        int n = 3;
//        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};

        int answer =0;
        nodes = new int[n];
        for(int i=0;i<n;i++){
            nodes[i]=i;
        }
//        for(int i =0;i<n;i++){
//            union(n,computers,nodes,i);
//        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i!=j && computers[i][j]==1){
                    union(i,j);
                }
            }
        }

//        Arrays.stream(nodes).forEach(x -> System.out.printf(x+" "));
//        System.out.println();
        Set<Integer> set = new HashSet<>();
        for(int i : nodes){
            if(!set.contains(i)){
                answer++;
                set.add(i);
            }
        }
        System.out.println(answer);
    }
    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a!=b){
            nodes[b]=a;
        }

    }
    private static int find(int a){
        if(a ==nodes[a]){
            return a;
        }
        return nodes[a]=find(nodes[a]);
    }

}
//    private static void union(int n, int[][] computers,int[] nodes,int pNode){
//        Queue<Integer> queue = new LinkedList<>();
//        for(int i=0;i< n;i++){
//            if(computers[pNode][i]==1 && i!=pNode){
//                queue.add(i);
//            }
//        }
//        while(!queue.isEmpty()){
//            int node = queue.poll();
//            if(pNode == nodes[pNode]){
//                nodes[node]=pNode;
//                return pNode;
//            }
//            nodes[node]=union(n,computers,nodes,nodes[pNode]);
//        }
//        return nodes[pNode];
//    }