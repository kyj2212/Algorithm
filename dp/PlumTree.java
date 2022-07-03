package DinamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PlumTree {
    static int T; //x
    static int W;//y 원하는 움직임의 횟수 최대 움직임

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int[] t = new int[T];
        for (int i = 0; i < T; i++) {
          t[i]=Integer.parseInt(br.readLine());
        }
        br.close();
        System.out.println(countPlum(t));

    }

    static int countPlum(int[] t) {

        int [][] plum = new int[W+1][T+1];
        int [] pTree= new int[W+1];
        int [] nTree= new int[W+1];

        int last=W;

        for (int i =0;i<W+1;i++) {
            pTree[i]=1;
        }


        for (int i = 1; i < T+1; i++) {

            for (int move = 0; move < (i<W+1?i:W+1); move++) {

                // tree 가 달라
                if(t[i-1]!=pTree[move]){
                    // i 를 안먹어 tree 안바뀌어
                    plum[move][i]=Math.max(plum[move][i],plum[move][i-1]);

                    // i 를 먹는경우
                    if(move+1<=W) {
                        if(plum[move + 1][i]<plum[move][i - 1] + 1){
                            plum[move + 1][i] = plum[move][i - 1] + 1;
                            nTree[move+1]=t[i-1];
                        }

                    }
                } else  // tree 가 같아
                    plum[move][i]=Math.max(plum[move][i],plum[move][i-1]+1);

                // 비어있어
                plum[move][i]=Math.max(plum[move][i],plum[move][i-1]);

            }

            for (int j=0;j<W+1;j++){ // next tree 넣어주고
                if(nTree[j]!=0)
                    pTree[j]=nTree[j];
            }

            last = i<W?i:W; // i 가 W보다 작으면 i 까지가 최댓값
            plum[last][i]=Math.max(plum[last][i],plum[last-1][i]);

        }


        for (int i = 0; i < W+1; i++) {
            for (int j = 0; j < T+1; j++) {
                System.out.printf(plum[i][j]+",");

            }
            System.out.println();
        }

        return plum[last][T];

    }


    static int countPlum4(int[] t) {

        int [][] plum = new int[W+1][T+1];
        int [] tree= new int[W+1];
        int prev;
        tree[0]=1;



        System.out.println("t");

        for (int i = 1; i < T; i++) {
            prev=i>0?i-1:0;
            System.out.print(t[i-1]);
            for (int move = 0; move < W; move++) {
                System.out.printf("("+ tree[move]+")");

                // tree 가 달라
                if(t[i-1]!=tree[move]){
                    // i 를 안먹어
                    plum[move][i]=Math.max(plum[move][i],plum[move][i-1]);

                    // i 를 먹는경우
                    plum[move+1][i]=Math.max(plum[move+1][i],plum[move][i-1]+1);
                    tree[move+1]=t[i-1];
                }
                // tree 가 같아
               else {
                   plum[move][i]=plum[move][i-1]+1;
                }

               plum[move][i]=Math.max(plum[move][i],plum[move][i-1]);


                System.out.printf(plum[move][i]+",");
            }
            System.out.println();

        }


        return plum[W-1][T-1];


    }

/*
    static int countPlum3(int[] t) {

        int [][] plum = new int[W+1][T];
        //int [] move = new int[W];
        //int move=0;
        int [] tree= new int[W+1];
        int prev;
        tree[0]=1;

        for (int i = 1; i < T; i++) {
            //prev=i>0?i-1:0;
            for (int move = 0; move < W; move++) {
//                if(move<W){

                    if(t[i]!=tree[move]){
                        // i 를 안먹어
                        plum[move][i]=Math.max(plum[move][i],plum[move][i-1]);

                        // i 를 먹는경우
                        //if(move+1<=W)
                        plum[move+1][i]=Math.max(plum[move+1][i],plum[move][i-1]+1);
                        tree[move+1]=t[i];
                        //plum[move+1][i]=Math.max(plum[move][i],plum[move][i-1]+1);
                    }

                    plum[move][i]=plum[move][i-1]+1;
  //              }
            }

        }
        System.out.println("plum");
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < T; j++) {
                System.out.printf(plum[i][j]+",");
            }
            System.out.println();
        }

        return plum[W-1][T-1];


    }
*/
/*
    static int countPlum2(int[] t){

        int[] move = new int[W];
      //  int [][] plum = new int [W][T];
        int [] plum = new int [T];
        int tree = 1;
        boolean [] skip= new boolean[T];

//////
        if(t[0]==tree){
            move[0]=0;
            plum[0]=1;
        } else {
            if (move[0] <= W) {
                if (!skip[0]) {
                    move[0] = 1;
                    plum[0] = 1;
                    tree=t[0];
                }
            } else {
                return plum[0];
            }
        }

/////

        int prev;
        for (int w = 0; w < W; w++) {

            for (int i = 0; i < T; i++) {
                prev=i>0?i-1:0;

                if(t[i]==tree){

               //   plum[w][i]=Math.max(plum[w-1][i],plum[w][prev]+1);
                    move[i]=move[prev];
                   plum[i]=plum[prev]+1;
                } else {
                    if(move[prev]==W) {
                        printarr(move,plum);
                        return plum[prev];
                    }
                    else {
                        // i를 먹는 경우
                        //  if(!skip[i]){
                        move[i] = move[prev] + 1;
                        plum[i]=plum[prev]+1;
                        tree = t[i];
                        //  }
                        // i를 안먹는 경우
                    }
                }
                //if(t[0]!=1)
                //   move[0]=1;
                //for (int j = 1; j < T; j++) {
                //  move[j]=t[j]!=t[j-1]?move[j-1]+1:move[j-1];
                //}
            }
        }
        printarr(move,plum);
        return plum[T-1];
    }

    static int maxPlum(int[] t ){
        boolean [] skip= new boolean[T];
        int [][] plum = new int[2][T];

        int prev;
        for (int i = 0; i < T; i++) {
            prev=i>0?i-1:0;
            if(t[i]!=t[prev]){
                skip[i]=false;
                //  plum[0][i]=countPlum(t,skip);
                skip[i]=true;
                //  plum[1][i]=countPlum(t,skip);
            }
        }


        return 0;
    }

    static void printarr(int[] move, int [] plum){

        System.out.printf("move : ");
        for(int i:move)
            System.out.printf(i+",");
        System.out.println();

        System.out.printf("plum : ");
        for(int i:plum)
            System.out.printf(i+",");
        System.out.println();
    }

    */

}

/*

        for (int move = 0; move < W; move++) {
        if(t[0]!=tree[move]){
        // i 를 안먹어
        plum[move][0]=Math.max(plum[move][i],plum[move][i-1]);

        // i 를 먹는경우
        plum[move+1][0]=Math.max(plum[move+1][i],plum[move][i-1]+1);
        tree[move+1]=t[0];
        }
        plum[move][0]+=1;
        }
     */


class PlumTreeOther {


 // 솔루션 01
    void sol01() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()), T = Integer.parseInt(st.nextToken());

        int[][] dist = new int[T + 1][N];
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - 1;
            if (i == 0) {
                if (arr[0] == 0) dist[0][0] = 1;
                continue;
            }
            dist[0][i] = dist[0][i - 1];
            if (arr[i] == 0) dist[0][i]++;
        }

        for (int i = 1; i < T + 1; i++) {
            int cur = i % 2;
            if (cur == arr[0]) dist[i][0] = 1;

            for (int j = 1; j < N; j++) {
                dist[i][j] = Max(dist[i - 1][j], dist[i][j - 1]);
                if (arr[j] == cur) dist[i][j]++;
            }
        }

        int max = 0;
        for (int i = 0; i < T + 1; i++) {
            max = Max(max, dist[i][N - 1]);
        }

        System.out.println(max);
        br.close();
    }

    private int Max(int a, int b) {
        return a <= b ? b : a;
    }



    // 솔루션 02
    void sol02() throws IOException {

        int T, W;
        int dp[][];
        int jadu[];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        T = Integer.parseInt(tmp[0]);
        W = Integer.parseInt(tmp[1]);
        jadu = new int[T+1];
        dp = new int[T+1][W+1];

        for(int i = 1; i <= T; i++){
            jadu[i] = Integer.parseInt(br.readLine());
        }


        //탐색 시작
        if(jadu[1] == 1){
            dp[1][0] = 1;
        }else{
            dp[1][1] = 1;
        }

        for(int i = 2; i <= T; i++){
            for(int j = 0; j <=W; j++){
                if(j == 0){
                    dp[i][0] = jadu[i] == 1 ? dp[i-1][0] + 1 : dp[i-1][0];
                }else{
                    if(j % 2 == 0){//짝수번 이동했으면 1번
                        dp[i][j] = jadu[i] == 1 ? Math.max(dp[i-1][j-1], dp[i-1][j]) + 1 : dp[i-1][j];
                    } else{//홀수번 이동 2번
                        dp[i][j] = jadu[i] == 2 ? Math.max(dp[i-1][j-1], dp[i-1][j]) + 1 : dp[i-1][j];
                    }
                }
            }
        }
        int ans = 0;
        for(int i = 0; i <= W; i++){
            ans = ans > dp[T][i] ? ans : dp[T][i];
        }
        System.out.println(ans);
    }

}