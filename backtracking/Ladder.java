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
//        int cnt = dfs(ladder,0);
//        int cnt = bfs(ladder);
        int cnt = addLines(0,ladder,1,1,0);
        System.out.println(cnt);
    }


    private static int addLines(int cnt, int[][] ladder,int vs, int hs,int depth){

        if(isSame(ladder)){
            System.out.println(String.format("성공 : cnt : %d, ",cnt));
            return cnt;
        }
        for(int v=vs;v<=N;v++){
            for(int h=hs;h<=H;h++){
                int[][] tmp = addLine(ladder,v,h);
                if(tmp==null){
                    continue;
                }
                ladder=tmp;
                cnt++;
                System.out.println(String.format("cnt: %d, (%d,%d) 추가",cnt, v,h));
                System.out.println(String.format("ladder size: %d, cnt: %d", count(ladder)-M,cnt));
                if(isSame(ladder)){
                    return cnt;
                }
                if(cnt<3){
                    depth = addLines(cnt,ladder,vs,hs,depth);
                    System.out.println(String.format("depth: %d, cnt: %d", depth,cnt));
                    if(depth!=-1){
                        System.out.println(String.format("depth: %d가 -1이 아닌경우", depth));
                        return depth;
                    }

                }
//                if(cnt!=-1){
//                    return cnt;
//                }
                ladder=delLine(ladder,v,h);
                cnt--;
                System.out.println(String.format("cnt: %d, (%d,%d) 제거", cnt,v,h));
                System.out.println(String.format("ladder size: %d, cnt: %d", count(ladder)-M,cnt));
            }
        }
        System.out.println(String.format("cnt : %d,다돌았는데 no same, cnt>=3, cnt -1 반환 ", cnt));
        return -1;
    }

    private static boolean isSame(int[][] ladder){
        for(int v=1;v<=N;v++){
            if(!down(ladder,v)){
                System.out.println(String.format("%d 번줄 실패", v));
                return false;
            }
        }
        return true;
    }

/*    private static int bfs(int[][] ladder){
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][H+1];
        int cnt =0;

        if(isSame(ladder,queue,visited)){
            return cnt;
        }
        while(!queue.isEmpty()){
            int[] q = queue.poll(); // q[v,h]
            addLine(ladder,q[0],q[1]);
            cnt++;
            visited[q[0]][q[1]]=true;
            if(isSame(ladder,queue,visited)){
                break;
            }
            delLine(ladder,q[0],q[1]);
            cnt--;
            visited[q[0]][q[1]]=false;
            if(cnt>3){
                cnt=-1;
                break;
            }
        }
        // 모두 통과
        // cnt 반환
        return cnt;
    }*/
/*    private static boolean isSame(int[][] ladder, Queue<int[]> queue,boolean[][] visited){
        boolean answer=true;
        for(int v=1;v<=N;v++){
            // v 에 대하여 사다리 타기
            if(!down(ladder,v)){
                answer=false;
                // 하나라도 다르면
                for(int h=1;h<=H;h++){
                    if(visited[v][h]){
                        continue;
                    }
                    if(v==N && ladder[v][h]==0 ){
                        queue.add(new int[]{v,h});
                    }
                    if(v<N && ladder[v][h]==0 && ladder[v+1][h]==0){
                        queue.add(new int[]{v,h});
                    }
                }
            }
        }
        return answer;
    }*/
    private static int[][] addLine(int[][] ladder, int v, int h){

        if(v<N && ladder[v][h]==0 && ladder[v+1][h]==0){
            ladder[v][h]=1;
            ladder[v+1][h]=-1;
            return ladder;
        }
//        if(v==N && ladder[v][h]==0){
//            ladder[v][h]=1;
//            return ladder;
//        }
        return null;
    }
    private static int[][] delLine(int[][] ladder, int v, int h){
        if(v<N){
            ladder[v+1][h]=0;
        }
        ladder[v][h]=0;
        return ladder;
    }
/*    private static int dfs(int[][] ladder,int cnt){
        System.out.println(String.format("dfs 시작 , cnt : %d , 추가개수 : %d", cnt,count(ladder)-M));
        for(int[] ll : ladder){
            for(int l : ll){
                System.out.printf(l+", ");
            }
            System.out.println();
        }
        System.out.println();

        if(cnt>3){
            return -1;
        }
        for(int i =1;i<=N;i++){

            if(down(ladder,i)){
                continue;
            }
            for(int j =1;j<=H;j++){

                if(i+1>N && ladder[i][j]==0){
                    System.out.println(String.format("[3] cnt:%d,  %d 개 추가한 상태 ", cnt,count(ladder)-M));
                    System.out.println(String.format("[3] %d 세로줄에 %d 가로줄 추가한 상태 ", i,j));
                    ladder[i][j]=1;
                    int tmp=dfs(ladder,cnt+1);
                    System.out.println("tmp : "+tmp);
                    if(tmp<0){
                        if(j==H){
//                            tmp = dfs(ladder,cnt+1);
//                            if(tmp!=-1)
//                                return tmp;
                            return cnt;
                        }
                        ladder[i][j]=0;
                        cnt--;
                        continue;
                    }
                    return tmp;
                }

                if(i+1<=N && ladder[i][j]==0 && ladder[i+1][j]==0){
                    System.out.println(String.format("[3] cnt:%d,  %d 개 추가한 상태 ", cnt,count(ladder)-M));
                    System.out.println(String.format("[3] %d 세로줄에 %d 가로줄 추가한 상태 ", i,j));

                    ladder[i][j]=1;
                    ladder[i+1][j]=-1;
                    int tmp=dfs(ladder,cnt+1);
                    System.out.println("tmp : "+tmp);
                    // 마지막 j도 안되면 안되면 cnt 추가
                    if(tmp<0){
                        if(j==H){
//                            tmp = dfs(ladder,cnt+1);
//                            if(tmp!=-1)
//                                return tmp;
                            return cnt;
                        }
                        ladder[i][j]=0;
                        ladder[i+1][j]=0;
                        cnt--;
                        continue;
                    }
                    return tmp;
                }
            }
            // 다 돌았는데 안되는 경우
            if(i==N){
                cnt=-2;
            }
        }
        return cnt;
    }*/
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
    private static boolean down(int[][] ladder,int v){
        // v 번째 세로줄의 세로줄 타기
        int i = v;
        for(int j=1;j<=H;j++){

            if(ladder[i][j]==0){
                continue;
            }
            if(ladder[i][j]==1){
                System.out.println(String.format("[1] %d번 줄의 %d번 가로줄을 타고 내려감", i,j));
                i++;
                System.out.println("[1] v : " + i);
                continue;
            }
            if(ladder[i][j]==-1){
                System.out.println(String.format("[2] %d번 줄의 %d번 가로줄을 타고 내려감", i,j));
                i--;
                System.out.println("[2] v : " + i);
                continue;
            }
        }
        if(v!=i){
            System.out.println(String.format("사다리결과 : %d 같지않음 %d", v,i));
            return false;
        }
        System.out.println(String.format("사다리결과 : %d 같음!!! %d", v,i));
        return true;

/*        for(int i =h+1;i<H;i++){
            if(ladder[v][i]==0){
                continue;
            }
            if(ladder[v][i]==1){
                System.out.println(String.format("[1] %d번 줄의 %d번 가로줄을 타고 내려감", v,i));
                v=down(ladder,v+1,i);
                System.out.println("[1] v : " + v);
            }

        }
        // 끝까지 다 타고 내려왔을 때
        return v;*/
    }
}


/*
package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ladder {
    static int N,M,H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        H=Integer.parseInt(st.nextToken());
        List<Integer>[] ladder = new ArrayList[N+1];
        for(int i =1;i<=N;i++){
            ladder[i]=new ArrayList<>();
        }
        */
/*
        * 5 5 6
        1 1
        3 2
        2 3
        5 1
        5 4
        * *//*

        // a번 가로줄이 b번, b+1번 세로줄에 해당
        // 가로선에 있는 세로줄 의 리스트
        // ladder[1] = {1} // 1,2
        // ladder[2] = {3}// 3++, 4--
        // ladder[3] = {2} // 2++, 3--
        // ldadder[4] =
        // ldadder[5] = {1,4} // 1++, 2-- 4++ 5--

        // 방향 있는 그래프
        // a b
        // a번 가로줄이 b번, b+1번 세로줄에 해당 -> ladder[b]={a}
        // 세로 선에 있는 가로줄 의 리스트
        // ladder[1] = {1,5}
        // ladder[2] = {3}
        // ladder[3] = {2}
        // ldadder[4] = {5}
        // ldadder[5] =

        for(int i =1;i<=M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
//            ladder[a].add(b);
            ladder[b].add(a);
        }
        int cnt = dfs(ladder,0,1);

        System.out.println(ladder);
        System.out.println(cnt);
    }

    // start == end 가 같아질때까지 찾는거지
    // start==end가 같고, start==N 일 때의 cnt
    // 만약에 cnt>3 이면 -1
    private static int dfs(List<Integer>[] ladder, int cnt, int vs){
        // 같으면 다음 세로선
        if(isSame(ladder,vs)){
            if(vs==N){
                if(cnt>3){
                    return -1;
                }
                return cnt;
            }
            return dfs(ladder,cnt,vs+1);
        }
        // 다르면 라인 추가해서 비교
        for(int i =1 ;i<=H;i++){
            List<Integer>[] tmp =addLine(ladder,vs,i);
            if(tmp==null){
                continue;
            }
            ladder=tmp;
            return dfs(ladder,cnt+1,vs);
        }

        return dfs(ladder,cnt,vs);
    }

    private static boolean isSame(List<Integer>[] ladder,int v){

        // 현재 v 세로선의 오른쪽,왼쪽 가로줄의 개수
        int rLine=ladder[v].size();
        int lLine=v>1 ? ladder[v-1].size() : 0;
        int h = 0;


        for(int i =0;i<rLine;i++){
            // 가로줄의 넘버
            int r=ladder[v].get(i);
            // h보다 아래 줄이면 내려가
            if(r> h){
                v++;
                h=r;
            }
        }
        for(int i =0;i<lLine;i++){
            // 가로줄의 넘버
            int l=ladder[v-1].get(i);
            // h보다 아래 줄이면 내려가
            if(l> h){
                v--;
                h=l;
            }
        }
        return false;
    }
    private static List<Integer>[] addLine(List<Integer>[] ladder, int vs, int h){

        if(ladder[vs].contains(h)){
            return null;
        }
        ladder[vs].add(h);
        return ladder;
    }
}
*/
