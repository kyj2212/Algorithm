
package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ladder{
    static int N,M,H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        H=Integer.parseInt(st.nextToken());

        int[][] ladder = new int[N+1][H+1];
        for(int i =1;i<=M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 왼쪽만 있으면 -1, 오른쪽만 있으면 1, 둘다 없으면 0, 둘다 있는 경우는 없다
            ladder[b][a]=1; // b번 - b+1번 세로줄 사이의 a 번째 가로줄이 존재
            if(b+1<=N)
                ladder[b+1][a]=-1; // b번 - b+1번 세로줄 사이의 a 번째 가로줄이 존재
        }
//        printLadder(ladder);
        List<int[]> nodes = new ArrayList<>();
        List<Integer> cnt = new ArrayList<>();

        if(!isSame(ladder)){
            cnt = dfs(ladder,0,nodes,cnt);
        }else{
            cnt.add(0);
        }
        int answer = cnt==null || cnt.isEmpty() ? -1 : Collections.min(cnt);
        System.out.println(answer);
    }


    private static List<Integer> dfs(int[][] ladder,int depth,List<int[]> nodes,List<Integer> cnt){

        if(nodes.size()>4){
            return cnt;
        }

        for(int v=1;v<=N;v++){
            for(int h=1;h<=H;h++){

                int[][] tmp = addLine(ladder,v,h);
                int [] node = new int[]{v,h};
                if(tmp==null){
                    continue;
                }
                ladder=tmp;
//                cnt++;
                nodes.add(node);
//                printNode(nodes);
//                System.out.println(String.format("cnt: %d, (%d,%d) 추가",cnt, v,h));
//                System.out.println(String.format("ladder size: %d, cnt: %d", count(ladder)-M,cnt));
                if(isSame(ladder)){
//                    printLadder(ladder);
                    cnt.add(nodes.size());
                }
                if(nodes.size()<3){
//                    List<Integer> result = dfs(ladder,depth,nodes,cnt);
                    cnt = dfs(ladder,depth,nodes,cnt);
//                    System.out.println(String.format("depth: %d, cnt: %d", depth,cnt));
//                    if(result!=null){
////                        System.out.println(String.format("depth: %d가 -1이 아닌경우", depth));
//                        return result;
//                    }

                }
                ladder=delLine(ladder,v,h);
                nodes.remove(node);
//                System.out.println(String.format("cnt: %d, (%d,%d) 제거", cnt,v,h));
//                System.out.println(String.format("ladder size: %d, cnt: %d", count(ladder)-M,cnt));
            }
        }
//        System.out.println(String.format("cnt : %d,다돌았는데 no same, cnt>=3, cnt -1 반환 ", cnt));
        return cnt;
    }

    private static void printNode(List<int[]> nodes) {
        System.out.printf("nodes : ");
        nodes.forEach(node -> System.out.printf(String.format("(%d,%d) ", node[0],node[1])));
        System.out.println();
    }

    private static boolean isSame(int[][] ladder){
        for(int v=1;v<=N;v++){
            if(!down(ladder,v)){
//                System.out.println(String.format("%d 번줄 실패", v));
                return false;
            }
        }
        return true;
    }

    private static int[][] addLine(int[][] ladder, int v, int h){

        if(v<N && ladder[v][h]==0 && ladder[v+1][h]==0){
            ladder[v][h]=1;
            ladder[v+1][h]=-1;
            return ladder;
        }
//        System.out.println(String.format("%d, %d 추가 불가", v,h));
        return null;
    }
    private static int[][] delLine(int[][] ladder, int v, int h){
        if(v<N){
            ladder[v+1][h]=0;
        }
        ladder[v][h]=0;
        return ladder;
    }

    private static boolean down(int[][] ladder,int v){
        // v 번째 세로줄의 세로줄 타기
        int i = v;
        for(int j=1;j<=H;j++){

            if(ladder[i][j]==0){
                continue;
            }
            if(ladder[i][j]==1){
//                System.out.println(String.format("[1] %d번 줄의 %d번 가로줄을 타고 내려감", i,j));
                i++;
                continue;
            }
            if(ladder[i][j]==-1){
//                System.out.println(String.format("[2] %d번 줄의 %d번 가로줄을 타고 내려감", i,j));
                i--;
                continue;
            }
        }
        if(v!=i){
//            System.out.println(String.format("사다리결과 : %d 같지않음 %d", v,i));
            return false;
        }
//        System.out.println(String.format("사다리결과 : %d 같음!!! %d", v,i));
        return true;

    }

    private static int count(int[][] ladder){
        int cnt =0;
        for(int i =1;i<=N;i++){
            for (int j=1;j<=H;j++){
                if(ladder[i][j]==1){
                    cnt++;
                }
            }
        }
        return cnt;
    }
    private static void printLadder(int[][] ladder){
        for(int i =1 ;i<=N;i++){
            for(int j=1;j<=H;j++){
                System.out.printf(String.format("%10d,", ladder[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }
}

/*public class Ladder {
    static int N, M, H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        int[][] ladder = new int[N + 1][H + 1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 왼쪽만 있으면 -1, 오른쪽만 있으면 1, 둘다 없으면 0, 둘다 있는 경우는 없다
            ladder[b][a] = 1; // b번 - b+1번 세로줄 사이의 a 번째 가로줄이 존재
            if (b + 1 <= N)
                ladder[b + 1][a] = -1; // b번 - b+1번 세로줄 사이의 a 번째 가로줄이 존재
        }
//        printLadder(ladder);
        boolean[][] visited = new boolean[N+1][H+1];
        System.out.println(cnt);
    }
    private  static void dfs(int[][] ladder, boolean[][] visited){
        for(int cnt=0;cnt<4;cnt++){
            Queue<int[]> nodes = available(ladder,visited);
            while(!nodes.isEmpty()){
                int[] node = nodes.poll();

            }
        }


    }
    private static Queue<int[]> available(int[][] ladder,boolean[][] visited){
        Queue<int[]> nodes = new LinkedList<>();
        for(int v=1;v<=N;v++){
            for(int h=1;h<=H;h++){
                if(!visited[v][h] && ladder[v][h]==0){
                    int[] node = new int[]{v,h};
                    nodes.add(node);
                }
            }
        }
        nodes.add(null); // 한바퀴가 종료됨. 여기까지 넣어도 안되면 cnt 추가하여서 depth2로 가야함.
        return nodes;
    }
}*/