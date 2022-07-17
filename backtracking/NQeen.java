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
        int cnt=0;

       // boolean [] allowAll = new boolean[N*N];
        ArrayList<Integer> allowAll = new ArrayList<>();
        for(int i=0;i<N*N;i++)
            allowAll.add(i);

        for(int i=0;i<N*N;i++){
            System.out.println("=== start idx : "+i);
            //boolean [] visited = new boolean[N*N];
            cnt+=dfs(i,allowAll,1,new boolean[N*N]);

        }

        System.out.println(cnt);

    }

    private static int dfs(int nodeidx, ArrayList<Integer> allow, int nodeCnt, boolean[] visited) {

        visited[nodeidx]=true; // 방문체크
        int cnt=0; // 초기화

        // newallow 구하기
       // boolean [] newAllow= new boolean[N*N];
        ArrayList<Integer> newAllow = new ArrayList<>();
        System.out.println("nodeidx : "+nodeidx);
        for(int i : adj[nodeidx]){ // node의 인접노드중에서
            if(allow.contains(i))
                newAllow.add(i);
        }
        System.out.println("newallow : "+newAllow);

        // newAllow 가 비었으면 return
        if(newAllow.size()==0){
            System.out.println("newAllow is empty, nodeCnt : "+nodeCnt);
            if(nodeCnt<N)
                return 0;
            else return 1;
        }
        // newallow가 있으면 item 으로 dfs
        for(int i : newAllow){
            if(!visited[i]) // 방문안했으면
                cnt+=dfs(i,newAllow,nodeCnt+1,visited); // 자식노드 dfs
        }
        System.out.println("dfs("+nodeidx+") : "+cnt);
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
                if(j/N!=x&&j%N!=y&&Math.abs(x-j/N)!=Math.abs(y-j%N))
                    adj[i].add(j);

            }

        }
    }



// dfs 옛날
/*
static int dfs(int idx,ArrayList<Integer> prev){
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
                    dfs(j, adj[idx]);
            }
        }
    }
    System.out.println("idx's adj has prev's adj : "+cnt);
    if(adj[idx].size()<N-1){
        return 0;
    }






        //System.out.println("adj["+idx+"] no "+i+" | cnt : "+cnt);

    System.out.println("dfs("+idx+") : "+cnt);
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

