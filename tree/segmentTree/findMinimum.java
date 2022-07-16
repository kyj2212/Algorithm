package tree.segmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
* range minimum qeury with segment tree
* use Node class
* 65976	696 / 65512	700
* */

public class findMinimum {

    static int N,M;
    static int [] input;

    static class Node {
        int start;
        int end;
        int min;
        Node left;
        Node right;

        Node(int start, int end){
            this.start=start;
            this.end=end;
            int half = start+(end-start)/2;
            if(start<=half&& half+1<=end){
                this.left=new Node(start,half);
                this.right=new Node(half+1,end);
            }
        }
        int getMin(){return this.min;}
    }



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N=Integer.parseInt(st.nextToken()); // node의 개수
        M=Integer.parseInt(st.nextToken()); // query의 개수

        input = new int[N];
        for(int i=0;i<N;i++){
            input[i]=Integer.parseInt(br.readLine());
        }
       Node root = buildSegmentTree();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int qs=Integer.parseInt(st.nextToken());
            int qe=Integer.parseInt(st.nextToken());

            bw.write(String.valueOf(RMQ(root,qs-1,qe-1))+'\n');
        }

        br.close();
        bw.flush();
        bw.close();
    }


    static int RMQ(Node node, int qs, int qe) {

        if(node.start>qe||node.end<qs){ // qs,qe 2,4  ss,se 5,9 걸러
            return 1000000001;
        }else if(qs<=node.start&&node.end<=qe){ //qs,qe 보다 범위가 작아, 큰범위부터 트리가 내려가기때문에 작은값은 무조건 min에서 비교를 간다.
            return node.getMin();
        }else{
            if(node.left!=null) { // left가 없고 right 만 있을 수는 없어
                return Math.min(RMQ(node.left,qs, qe), // 0,4 --> 0~2, 3~4 로 쪼개져 3~4  --> 0~2는 0~1(걸러), 2(return 100) --> 3~4 범위안이라서 return 38 --> 100이랑 38 비교해 38,
                        RMQ(node.right, qs, qe)); //5,9 --> 걸러져 // 38이랑 거른거 비교해 38
            }
        }
        return 1000000001;
    }

    static Node buildSegmentTree() {
        Node root = new Node(0, N-1);
        seg(root);
        return root;
    }



    static int seg(Node node){

        if(node.start==node.end){
            node.min=input[node.start];
            return node.getMin();
        }else{
            node.min=Math.min(seg(node.left)
                    ,seg(node.right));
            return node.getMin();
        }
    }

}
