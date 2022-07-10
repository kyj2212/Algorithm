package tree.trie;

import java.io.*;
import java.util.StringTokenizer;

public class FindString {

    static int N; // 키 문자열의 개수.
    static int M; // 검사해야하는 문자열의 개수

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 집합 S에 포함되어있는 문자열의 개수
        M = Integer.parseInt(st.nextToken()); // 검사해야 하는 문자열의 개수


        String[] S = new String[N];
        String test;
        int cnt=0;

        for (int i = 0; i < N; i++) {
            S[i]=br.readLine();
        }

        TrieNode trie = InsertToTrie(S);


        for (int i = 0; i < M; i++) {
            test=br.readLine();
            if(SearchString(trie,test))
                cnt++;
        }


        br.close();



        System.out.println(cnt);



    }

    static TrieNode InsertToTrie(String[] S){

        // root 노드 생성
        TrieNode root = new TrieNode();

        // 대상 key 문자열을 trie에 insert
        for(int n =0;n<N;n++){
            TrieNode node = root;
            // 각각의 문자열 S[n] 에 대하여
            for(int i=0;i<S[n].length();i++){
                char c = S[n].charAt(i);
                // S[n]의 charAt[] 에 대하여 (문자열의 철자 1개씩)
                if(node.child[c-'a']==null) { // child가 없으면
                    node.child[c-'a']=new TrieNode(); // child 추가

                    // 문자열의 마지막 char 일때
                    if(i==S[n].length()-1) {
                        node.ifLeaf=true;
                        break; // 자식노드로 내려가지말고 해당 string 종료
                    }
                }
                node=node.child[c-'a']; // 자식 노드로 내려가.

            }
        }


        return root;

    }

    static boolean SearchString(TrieNode trie,String test){
        // test 문자열이 trie에 해당하는지 search

        for (int i=0;i<test.length();i++){
            char c = test.charAt(i);
            if(trie.child[c-'a']==null) // trie 없으면 꽝
                return false;
            else {
                trie=trie.child[c-'a'];
                if(trie.ifLeaf==true)
                    return true;
            }
        }
        return false;
    }

    static class TrieNode{
        TrieNode[] child = new TrieNode[26];
        Boolean ifLeaf=false;
    }

}

