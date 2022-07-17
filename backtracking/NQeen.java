package backtracking;

import com.sun.org.apache.xpath.internal.objects.XBoolean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NQeen {

    static int N;
   // static boolean visited[];
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        br.close();

       // visited= new boolean[N*N];
        adj=new ArrayList[N*N];


        buildTree();

        int n=0;
        for(ArrayList<Integer> list : adj){
            System.out.printf(n+" { ");
            for (int i : list){
                System.out.printf(i+",");
            }
            System.out.println(" }");
            n++;
        }


        System.out.println();



        //tracking();

    }


    private static void tracking(){
        int cnt=0;

        ArrayList<Integer> allowAll = new ArrayList<>();
        for(int i=0;i<N*N;i++)
            allowAll.add(i);


        // 모든 노드
        for(int i=0;i<N;i++){
            System.out.println("=== start idx : "+i*N);
            cnt+=dfs(i*N,allowAll,1,new boolean[N*N],0);//cnt 초기화
        }

        // 중복 없앤경우
/*

        int i =0;
        while(true){
            System.out.println("=== start idx : "+i);
            cnt+=search.dfs(i,allowAll,1,new boolean[N*N],0);//cnt 초기화
            for (int j = i+1; j < N*N; j++) {
                if(!adj[i].contains(j)){
                    i=j;
                    break;
                }
            }
            if(i>N-1)
                break;
        }

*/
        System.out.println(cnt);

    }

    private static int dfs(int nodeidx, ArrayList<Integer> allow, int nodeCnt, boolean[] visited,int cnt) {

        visited[nodeidx]=true; // 방문체크


        // newallow 구하기
       // boolean [] newAllow= new boolean[N*N];
        ArrayList<Integer> newAllow = new ArrayList<>();
   //       System.out.println("nodeidx : "+nodeidx);
        for(int i : adj[nodeidx]){ // node의 인접노드중에서
            if(allow.contains(i))
                newAllow.add(i);
        }
       System.out.println("newallow : "+newAllow);

        // newAllow 가 비었으면 return
        if(newAllow.size()==0){
            if(nodeCnt>=N)
                System.out.println("newAllow is empty, nodeCnt : "+nodeCnt);
            return nodeCnt<N?0:nodeCnt-(N-1);
        }
        // newallow가 있으면 item 으로 search.dfs
        for(int i : newAllow){
            if(!visited[i]) // 방문안했으면
                cnt+=dfs(i,newAllow,nodeCnt+1,visited,cnt); // 자식노드 search.dfs
        }
        if(cnt!=0)System.out.println("search.dfs("+nodeidx+") : "+cnt);
        return cnt;
    }


    static void buildTree(){
        int n=N*N;
        for (int i = 0; i < n; i++) {
            adj[i]=new ArrayList<>();
            int x=i/N;
            int y=i%N;

            for (int j = 0; j < n; j++) {

                //int xdiff = Math.abs(x-j/N);
                //int ydiff = Math.abs(y-j%N);
                if(j/N>x&&j%N!=y&&Math.abs(x-j/N)!=Math.abs(y-j%N))
                    adj[i].add(j);

            }

        }
    }



    // 옛날 트리
    // 인접 노드 -> 8방향

    /*
    static void buildTree(){
        int n=N*N;
        for (int i = 0; i < n; i++) {
            adj[i]=new ArrayList<>();
            int x=i/N;
            int y=i%N;

            for (int j = 0; j < n; j++) {

                //int xdiff = Math.abs(x-j/N);
                //int ydiff = Math.abs(y-j%N);
                if(j/N!=x&&j%N!=y&&Math.abs(x-j/N)!=Math.abs(y-j%N))
                    adj[i].add(j);

            }

        }
    }
     */

// search.dfs 옛날
/*
static int search.dfs(int idx,ArrayList<Integer> prev){
    int cnt=0;
    visited[idx]=true;
    //System.out.println("prev : "+prev);
    System.out.println("==== idx : "+idx+"====");
    System.out.println("prev : "+prev);

    for(int i:prev){
        if(adj[idx].contains(i)){
            cnt++;
            for(int j:adj[idx]) {
                if (!visited[j])
                    search.dfs(j, adj[idx]);
            }
        }
    }
    System.out.println("idx's adj has prev's adj : "+cnt);
    if(adj[idx].size()<N-1){
        return 0;
    }






        //System.out.println("adj["+idx+"] no "+i+" | cnt : "+cnt);

    System.out.println("search.dfs("+idx+") : "+cnt);
    return cnt;
}
*/


// combi
/*

static int combi (int n){
    int result=1;
    for(int i=0;i<N;i++){
        result*=(n-i);
        result/=(N-i);
    }
    System.out.println(result);
    return result;
}

*/



}

