package search.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ChangeString {
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
//        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        String[] words = {"hot", "dot", "dog", "lot", "log"};

        int answer =0;
        Queue<Node> queue = new LinkedList<>();
        boolean [] visited = new boolean[words.length];

        answer=bfs(begin,target,words,queue,visited);


        System.out.println(answer);
    }
    private static int bfs(String begin, String target, String[] words, Queue<Node> queue, boolean[] visited){

        for(int i =0;i< words.length;i++){
            String word = words[i];
            if(visited[i]){
                continue;
            }
            if(isChangeable(begin, word)){

                queue.add(new Node(word,1,i));
            }
        }
        while(!queue.isEmpty()){
            Node node = queue.poll();

            visited[node.idx]=true;
//            System.out.println("node : "+node.str + " depth : "+node.depth);
            if(node.str.equals(target)){
                return node.depth;
            }
            for(int i =0;i< words.length;i++){
                String word = words[i];
                if(visited[i]){
                    continue;
                }
                if(isChangeable(node.str, word)){

                    queue.add(new Node(word,node.depth+1,i));
                }
            }
        }
        return 0;
    }

    private static boolean isChangeable(String begin, String word) {
        int cnt =0;
        for(int i =0;i<begin.length();i++){

            if(begin.charAt(i)!=word.charAt(i)){
                cnt++;
            }
            if(cnt>1){
                return false;
            }
        }
        return true;
    }
    static class Node{
        String str;
        int depth;
        int idx;
        Node(String str, int depth,int idx){
            this.str=str;
            this.depth=depth;
            this.idx=idx;
        }
    }
}
