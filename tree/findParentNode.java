package tree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class findParentNode {

    static int N;
    static boolean[] visited;
    static int[] parent;

    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //node의 개수


        visited = new boolean[N];
        parent = new int[N-1];
        tree = new ArrayList[N];
        for(int i=0;i<N;i++)
            tree[i]=new ArrayList<>();


        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            tree[n1-1].add(n2-1);
            tree[n2-1].add(n1-1);
        }

  //      for (int i =0;i<N;i++){
//            System.out.println(tree[i]);
 //       }

        for (int i=0;i<N;i++)
            isParent(i);



       // for(int i : parent)
      //      System.out.printf(i+" ");
      //  System.out.println();


        for(int i : parent){
            bw.write(String.valueOf(i)+'\n');
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static void isParent(int node){
        // DFS
        visited[node]=true;


/*
        System.out.printf("parent : ");
        for(int i : parent)
            System.out.printf(i+" ");
        System.out.println();

        System.out.printf("visited : ");
        for(boolean b : visited)
            System.out.printf(b+" ");
        System.out.println();

 */


        for(int child : tree[node]){
            if(visited[child]==true)
                continue;
            else{
                parent[child-1]=node+1;
                isParent(child);
            }
        }
    }

}
