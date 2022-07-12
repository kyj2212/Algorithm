package dfs;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JumpKingJelly {

    static int N;
    static int [][] adj;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adj = new int [N*N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < N; j++) {
                adj[i*N+j][0]=Integer.parseInt(st.nextToken()); // val 값을 첫번째로 넣기
                if(i!=N-1) //i==N-1 바닥 끝
                    adj[i*N+j][2]=(i+1)*N+j; // 아래 노드가 인접노드

                if(j!=N-1) // j==N-1 오른쪽 끝
                    adj[i*N+j][1]=i*N+j+1; // 오른 쪽 노드가 인접노드
            }
        }

        for(int [] arr : adj){
            for(int i : arr)
                System.out.printf(i+",");
            System.out.println();
        }

        // node 0 ~ N*N-1
        System.out.println(dfs(adj[0][0],0)==true?"HaruHaru":"Hing");
    }


    // no jump dfs
    static boolean dfs(int val, int node){

        for(int i=1;i<3;i++) {
            int next=adj[node][i];
            if(next==0) // 끝이라서 인접노드 없을 때
                continue;
            int nextVal = adj[next][0];
            if(val-1 == 0){ // 인접노드 밟을 때
                if(nextVal==-1) // target 이면 리턴
                    return true;
                else{ // target 이 아니면
                    if(nextVal==0)
                        continue;
                    if(dfs(nextVal,next)) // next 에서 다시 dfs
                        return true;
                }
            } else { // 인접노드 넘어가 그 앞의 노드의 val을 타고가
                if(dfsJump(val-1,next,i)) // 현재 direction을 인자로 넘겨
                    return true;
            }
        }
        return false;
    }

    // jump dfs 같은 direction 만 가능
    static boolean dfsJump(int val, int node, int prevdir){

            int next=adj[node][prevdir];
            if(next==0)
                return false;
            int nextVal = adj[next][0];
            if(val-1 == 0){ // 인접노드 밟을 때
                if(nextVal==-1) // target 이면 리턴
                    return true;
                else{ // target 이 아니면
                    if(nextVal==0)
                        return false;
                    if(dfs(nextVal,next)) // next 에서 다시 dfs
                        return true;
                }
            } else { // 인접노드 넘어가 그 앞의 노드의 val을 타고가
                if(dfsJump(val-1,next,prevdir))
                    return true;
            }
        return false;
    }

}
