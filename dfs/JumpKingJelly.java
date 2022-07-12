package dfs;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JumpKingJelly {

    static int N;
    static ArrayList<Integer> [] adj;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N*N];

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < N; j++) {
                adj[i*N+j] = new ArrayList<>();
                adj[i*N+j].add(Integer.parseInt(st.nextToken())); // val 값을 첫번째로 넣기
                //i==N-1 바닥 끝

                if(i!=N-1){ // 아래 노드가 인접노드
                    adj[i*N+j].add((i+1)*N+j);
                }
                // j==N-1 오른쪽 끝
                if(j!=N-1){ // 오른 쪽 노드가 인접노드
                    adj[i*N+j].add(i*N+j+1);
                }
            }
        }

        for(ArrayList<Integer> list : adj){
            for(int i : list)
                System.out.printf(i+",");
            System.out.println();
        }

        // node 0 ~ N*N-1
        System.out.println(dfs(adj[0].get(0),0)==true?"HaruHaru":"Hing");
    }



    static boolean dfs(int val, int node){

       // boolean anwer=false;
        // val = adj[node].get(0);
    //    if(val==0)
     //       return "Hing";

        for (int i = 1; i < adj[node].size(); i++) {
            // 인접노드 : adj[node].get(1 ~ ...)
            int next = adj[node].get(i);
            int nextVal = adj[next].get(0);
            if(val-1 == 0){ // 인접노드 밟을 때
                if(nextVal==-1) // target 이면 리턴
                    return true;
                else{ // target 이 아니면
                    if(nextVal==0){
                        continue;
                    }
                    if(dfs(nextVal,next)) // next 에서 다시 dfs
                        return true;
                }
            } else { // 인접노드 넘어가 그 앞의 노드의 val을 타고가
               if(dfs(val-1,next))
                   return true;
            }
        }
        return false;
    }
}
