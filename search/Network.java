package search;

import java.util.*;

public class Network {
    public static void main(String[] args) {

//        String str = "[1, 1, 0], [1, 1, 1], [0, 1, 1]";
//        str =str.replace('[','{');
//        str  =str.replace(']','}');
//        System.out.println(str);

        int n = 3;
//        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};

        int answer =0;
        int[] nodes = new int[n];
        for(int i=0;i<n;i++){
            nodes[i]=i;
        }
        for(int i =0;i<n;i++){
            union(n,computers,nodes,i);
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
    private static void union(int n, int[][] computers,int[] nodes,int pNode){
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i< n;i++){
            if(computers[pNode][i]==1 && i!=pNode){
                queue.add(i);
            }
        }
        while(!queue.isEmpty()){
            int node = queue.poll();
            nodes[node]=nodes[pNode];
        }
    }

}
