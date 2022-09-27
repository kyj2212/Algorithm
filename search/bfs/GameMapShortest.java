package search.bfs;


import java.util.LinkedList;
import java.util.Queue;

public class GameMapShortest {
    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};

        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;


        Queue<int[]> q = new LinkedList<>();
        boolean [][] visited = new boolean[n][m];

        int depth = 0;




        System.out.println(answer);

    }

}
