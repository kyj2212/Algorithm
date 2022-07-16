package tree.segmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * range minimum qeury with segment tree
 * use Node class
 * 65976	696 / 65512	700 /64036	696
 * */



public class findMinimum {

    static int N,M;
    static int [] input;

    static class Node {
        int min;
        Node left;
        Node right;

        int getMin(){return this.min;}
        Node getLeft(){return this.left=new Node();}
        Node getRight(){return this.right=new Node();}
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

            bw.write(String.valueOf(RMQ(root,0,N-1,qs-1,qe-1))+'\n');
        }

        br.close();
        bw.flush();
        bw.close();
    }


    static int RMQ(Node node,int ss, int se, int qs, int qe) {

        if(ss>qe||se<qs){ // qs,qe 2,4  ss,se 5,9 걸러
            return 1000000001;
        }else if(qs<=ss&&se<=qe){ //qs,qe 보다 범위가 작아, 큰범위부터 트리가 내려가기때문에 작은값은 무조건 min에서 비교를 간다.
            return node.getMin();
        }else{
            if(node.left!=null) { // left가 없고 right 만 있을 수는 없어
                int half=half(ss,se);
                return Math.min(RMQ(node.left,ss,half,qs, qe), // 0,4 --> 0~2, 3~4 로 쪼개져 3~4  --> 0~2는 0~1(걸러), 2(return 100) --> 3~4 범위안이라서 return 38 --> 100이랑 38 비교해 38,
                        RMQ(node.right,half+1,se, qs, qe)); //5,9 --> 걸러져 // 38이랑 거른거 비교해 38
            }
        }
        return 1000000001;
    }

    static Node buildSegmentTree() {
        Node root=new Node();
        seg(root,0,N-1);
        return root;
    }



    static int seg(Node node,int start, int end){
        if(start==end){
            node.min=input[start];
            return node.getMin();
        }else{
            int half = half(start,end);
            if(half>=start&& half+1<=end){
                node.min=Math.min(seg(node.getLeft(),start,half)
                        ,seg(node.getRight(),half+1,end));
            }
            return node.getMin();
        }
    }

    static int half(int start, int end){return start+(end-start)/2;}


}


