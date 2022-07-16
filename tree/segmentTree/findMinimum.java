package tree.segmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
* range minimum qeury with segment tree
* use 1 degree array
* 59168kb	668ms
* */

public class findMinimum {

    static int N,M;
    static int [] input;
    static int [] seg;

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
        buildSegmentTree(N);

/*        for(int i: seg){
            System.out.printf(i+" ");
        }*/

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int qs=Integer.parseInt(st.nextToken());
            int qe=Integer.parseInt(st.nextToken());

            bw.write(String.valueOf(RMQ(0,N-1,0,qs-1,qe-1))+'\n');
        }

        br.close();
        bw.flush();
        bw.close();
    }


    static int RMQ(int ss, int se, int nodeidx,int qs, int qe) {

        if(ss>qe||se<qs){ // qs,qe 2,4  ss,se 5,9 걸러
            return 1000000001;
        }else if(qs<=ss&&se<=qe){ //qs,qe 보다 범위가 작아, 큰범위부터 트리가 내려가기때문에 작은값은 무조건 min에서 비교를 간다.
            return seg[nodeidx];
        }else{
            int leftidx=2*nodeidx+1;
            int half=ss+(se-ss)/2;
            if(leftidx<seg.length) {
                return Math.min(RMQ(ss, half, leftidx, qs, qe), // 0,4 --> 0~2, 3~4 로 쪼개져 3~4  --> 0~2는 0~1(걸러), 2(return 100) --> 3~4 범위안이라서 return 38 --> 100이랑 38 비교해 38,
                        RMQ(half + 1, se, leftidx + 1, qs, qe)); //5,9 --> 걸러져 // 38이랑 거른거 비교해 38
            }
        }
        return 1000000001;
    }

    static void buildSegmentTree(int N) {
        double k= (Math.log(N)/Math.log(2));
        int height= k>(int)k?(int)k+1:(int)k;

        seg = new int[(int)Math.pow(2,height+1)-1];

        for(int i=0;i<seg.length;i++)
            seg[i]=1000000001; // 큰값으로 초기화
//        System.out.println("node개수 "+((int)Math.pow(2,height+1)-1));
        seg(0,N-1,0);

    }

    static int seg(int start,int end,int nodeidx){


        if(start==end){
            seg[nodeidx]=input[start];
            return seg[nodeidx];
        }else{

            int half=start+(end-start)/2;
            int leftidx=nodeidx*2+1;

            seg[nodeidx]=Math.min(seg(start,half,leftidx)
                    ,seg(half+1,end,leftidx+1));
            return seg[nodeidx];

        }


    }


}
