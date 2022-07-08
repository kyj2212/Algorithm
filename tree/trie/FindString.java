package tree.trie;

import java.io.*;
import java.util.StringTokenizer;

public class FindString {

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 집합 S에 포함되어있는 문자열의 개수
        M = Integer.parseInt(st.nextToken()); // 검사해야 하는 문자열의 개수


        String[] S = new String[N];
        String[] test = new String[M];


        for (int i = 0; i < N; i++) {
            S[i]=br.readLine();
        }

        for (int i = 0; i < M; i++) {
            test[i]=br.readLine();
        }


        br.close();

        for(String s : S)
            System.out.printf(s+ " ");
        System.out.println();

        for(String s : test)
            System.out.printf(s+ " ");
        System.out.println();




        //System.out.println(SearchString(S,test));

    }

    static int SearchString(String[] S, String[] test){

        Node root = new Node();
        for(int n =0;n<N;n++){
            for(char c : S[n].toCharArray()){
                if(root.next[c-'a']==null)
                    root.next[c-'a']=new Node();

            }
        }

        return 0;

    }



}


class Node{
    Node[] next = new Node[26];
    Boolean ifLeaf;
}