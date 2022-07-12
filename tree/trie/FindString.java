package tree.trie;

import java.io.*;
import java.util.StringTokenizer;

// 문자열 비교 String -> byte 연산으로 비교하는 것으로 수정하면 시간이 빨라질까.

public class FindString {

    static int N; // 키 문자열의 개수.
    static int M; // 검사해야하는 문자열의 개수
    static int cnt=0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 집합 S에 포함되어있는 문자열의 개수
        M = Integer.parseInt(st.nextToken()); // 검사해야 하는 문자열의 개수




        String[] S = new String[N];
        String test;

        for (int i = 0; i < N; i++) {
            S[i]=br.readLine();
        }

        TrieNode trie = InsertToTrie(S);


        //O(string S.최대 길이)
        for (int i = 0; i < M; i++) {
            test=br.readLine();
            SearchString(trie,test);
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

                }
                // 현재 node는 마지막 전 문자.
                // 현재 node의 child[c]가 마지막 문자노드
                node=node.child[c-'a']; // 자식 노드로 내려가.
                // 현재 노드가 마지막 노드,
                // 문자열의 마지막 char 일때
                if(i==S[n].length()-1) {
                    node.ifLeaf=true;
                }

            }
        }


        return root;

    }

    static void SearchString(TrieNode trie,String test){
        // test 문자열이 trie에 해당하는지 search

        for (int i=0;i<test.length();i++){
            char c = test.charAt(i);
            if(trie.child[c-'a']==null) // trie 없으면 꽝
                break;
            else {
                trie=trie.child[c-'a'];
                if(i==test.length()-1&&trie.ifLeaf==true) // test string도 마지막문자여야함.
                    cnt++;
            }
        }
    }

    static class TrieNode{
        TrieNode[] child = new TrieNode[26];
        Boolean ifLeaf=false;
    }

}

