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
        int[][] computers = {{1,0, 1,1, 0}, {0,1, 0,1, 0}, {1,0, 1,0, 1}, {1,1, 0,1, 0}, {0,0, 1,0, 1}};
//        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
//        int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};

        int answer = 0;

        nodes = new int[n];
        for(int i=0;i<n;i++){
            nodes[i]=i;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i!=j && computers[i][j]==1){
                    union(i,j);
                    computers[j][i]=0;
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i=0;i<n;i++){
            int node = find(i);
            if(!set.contains(node)){
                answer++;
                set.add(node);
            }
        }

        System.out.println(answer);
    }
    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        nodes[a]=nodes[b]=a<=b ? a : b;

    }
    private static int find(int a){
        if(a ==nodes[a]){
            return a;
        }
        return nodes[a]=find(nodes[a]);
    }

}
