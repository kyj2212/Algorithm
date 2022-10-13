
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

        List<int[]> nodes = new ArrayList<>();
        List<Integer> cnt = new ArrayList<>();

        if(!isSame(ladder)){
            cnt = dfs(ladder,nodes,cnt);
        }else{
            cnt.add(0);
        }
        int answer = cnt==null || cnt.isEmpty() ? -1 : Collections.min(cnt);
        System.out.println(answer);
    }


    private static List<Integer> dfs(int[][] ladder, List<int[]> nodes,List<Integer> cnt){
        if(nodes.size()>4){
            return cnt;
        }
        for(int v=1;v<=N;v++){
            for(int h=1;h<=H;h++){
                int[][] tmp = addLine(ladder,v,h);
                if(tmp==null){
                    continue;
                }
                int [] node = new int[]{v,h};
                ladder=tmp;
                nodes.add(node);
                if(isSame(ladder)){
                    cnt.add(nodes.size());
                }
                if(nodes.size()<3){
                    cnt = dfs(ladder,nodes,cnt);
                }
                ladder=delLine(ladder,v,h);
                nodes.remove(node);
            }
        }
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
                i++;
                continue;
            }
            if(ladder[i][j]==-1){
                i--;
                continue;
            }
        }
        if(v!=i){
            return false;
        }
        return true;
    }
}
