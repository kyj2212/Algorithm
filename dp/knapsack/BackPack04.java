package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/*
// 답 26
4 7
6 13
4 8
2 6
1 12

// 답 23
4 8
4 13
5 15
2 8
5 9

답 61
10 36
5 10
5 9
5 8
5 7
5 6
5 10
5 9
5 8
5 7
5 6

2 1
3 5
5 6

답 20
3 2
5 50
2 20
5 50

답 20
10 2
5 50
4 40
9 30
2 20
9 10
10 10
6 20
5 30
9 40
5 50

답 120
10 12
5 50
4 40
3 30
2 20
1 10
1 10
2 20
3 30
4 40
5 50

답 46
10 29
5 10
5 9
5 8
5 7
5 6
5 10
5 9
5 8
5 7
5 6
* */


public class BackPack04 {
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken()); // 물품 수
        K = Integer.parseInt(st.nextToken()); // 무게
        int[] W = new int[N];
        int[] V = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            W[i]=Integer.parseInt(st.nextToken());
            V[i]=Integer.parseInt(st.nextToken());
        }

        br.close();
        System.out.println(maxItem(W,V));

    }

    static int maxItem (int [] W, int [] V){
        int[][] bag = new int[N][K+1];

        for (int i = 0; i < N; i++) {

            int prev,next;
            prev=i>0?i-1:0;
            for (int j = 0; j <=K; j++) {
                next=j+W[i];
                if(next<=K)
                    bag[i][next]=i>0?Math.max(bag[prev][next],bag[prev][j]+V[i]):V[i];
                if(i<N-1)
                    bag[i+1][j]=bag[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K+1; j++) {
                System.out.printf(bag[i][j]+",");
            }
            System.out.println();
        }

        return bag[N-1][K];
    }

    static int maxItem5 (int [] W, int [] V){
        int[][] bag = new int[N][K+1];

       // Queue<Integer> prev = new LinkedList();
       // ArrayList<Integer> prev = new ArrayList<>();
       // int[] prev = new int[K];

       // bag[0][W[0]]=V[0];
        for (int i = 0; i < N; i++) {
            //bag[i][W[i]]=V[i];
            int prev,next;
            prev=i>0?i-1:0;
            for (int j = 0; j <=K; j++) {
                next=j+W[i];
                if(next<=K){

                   // bag[i][next]=bag[prev][next];
                    //bag[i][prev.poll()+W[i]]=Math.max(bag[i-1][prev.poll()+W[i]],bag[i-1][prev.poll()]+V[i]);
                    bag[i][next]=i>0?Math.max(bag[prev][next],bag[prev][j]+V[i]):V[i];
                }
                if(i<N-1)
                    bag[i+1][j]=bag[i][j];
            }
          //  for (int j = 0; j < K; j++) {
                //bag[i+1][j]
           // }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K+1; j++) {
                System.out.printf(bag[i][j]+",");
            }
            System.out.println();
        }
        return bag[N-1][K];
    }


    static int maxItem4(int [] W, int [] V){

        int[][] bag = new int[N][K+1];
//        if(W[0]<=K)
//            bag[0][W[0]]=V[0];

        int pre, prev;
        for (int i = 0; i < N; i++) {
            pre = i>0?i-1:0;
            prev=0;
            for (int k = 0; k <= i; k++) {
//                prev=k>0?W[k]:0;
                //if(W[k]+W[i]<=K) {
                 //   bag[i][W[k]+W[i]]=Math.max(bag[i][W[k]+W[i]],bag[k][W[k]]+V[i]);
                //}
                //prev=W[k];
            }
        //    for (int j = 0; j <= K; j++) {

        //        if(j<K){
        //            bag[i][j+1]=Math.max(bag[i][j+1],bag[i][j]);
              //  if(j+W[i]<=K){
              //      bag[i][j+W[i]]=Math.max(bag[i][j+W[i]],bag[i][j+W[i]]);
              //  }
         //   }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K+1; j++) {
                System.out.printf(bag[i][j]+",");
            }
            System.out.println();
        }

        return Arrays.stream(bag[N-1]).max().getAsInt();


    }


    static int maxItem3(int [] W, int [] V){

        int[][] bag = new int[N][K+1];
        //     int next=0;
        //      int pre = 0;

        bag[0][W[0]]=V[0];

        for (int i = 1; i < N; i++) {
            //        next=i;
            //       pre = 0;


            //int j = 0; j <= i; j++
            for (int j = 0; j <= K; j++) {

                // i = 2,
                // pre = 0, pre = W[0], pre = W[1], pre=W[2]
                // W[i] 추가된 물건의 무게
                // pre 기존의 무게
                //      if(j<=K)
                //        bag[i][j]=Math.max(bag[i-1][j],bag[i][j]);

                // bag[i][pre+W[i]]=Math.max(bag[i][pre+W[i]],bag[i-1][pre+W[i]]);
                //  bag[i][pre]=Math.max(bag[i][pre],bag[i-1][pre]);
                if(j+W[i]<=K){
                    bag[i][j+W[i]]=Math.max(bag[i-1][j+W[i]],bag[i-1][j]+V[i]);
                    //  bag[i][pre+W[i]]=Math.max(bag[i][pre+W[i]],bag[i-1][pre+W[i]]);
                }
                // pre 가 최대값, 끝값 = K
                //pre=j>0?W[j-1]:0;

                //                  else if(pre<K){

                //                       bag[i][pre+1]=Math.max(bag[i][pre],bag[i][pre+1]);
//                        bag[i][pre+1]=Math.max(bag[i][pre+1],bag[i-1][pre+1]);
                //                   }

                // pre = W[j];
            }

            //       for (int j = 0; j < K; j++) {
            //         bag[i][j+1]=Math.max(bag[i][j+1],bag[i][j]);
            //     }

            //bag[i][j]=Math.max(bag[i][j],bag[i-1][j]);

            //for (int j = 0; j < K; j++) {
            //if(j<K)
            //    bag[i][j+1]=Math.max(bag[i][j+1],bag[i][j]);
            // if(i<N-1)
            //    bag[i+1][j]=Math.max(bag[i+1][j],bag[i][j]);
            //}
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K+1; j++) {
                System.out.printf(bag[i][j]+",");
            }
            System.out.println();
        }


        // return bag[N-1][K];
        return Arrays.stream(bag[N-1]).max().getAsInt();


    }
    static int maxItem2(int [] W, int [] V){

        int[][] bag = new int[N][K+1];
        int next,pre;

        for (int i = 0; i < N; i++) {
            next=0;
            for (int j = i; j < N; j++) {
                if(next+W[j]<=K){
                    pre = next;
                    next+=W[j];
                    bag[i][next]=Math.max(bag[i][next],bag[i][pre]+V[j]);
                }

            }
            for (int j = 0; j < K+1; j++) {
                if(j<K)
                    bag[i][j+1]=Math.max(bag[i][j+1],bag[i][j]);
                if(i<N-1)
                    bag[i+1][j]=Math.max(bag[i+1][j],bag[i][j]);
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K+1; j++) {
                System.out.printf(bag[i][j]+",");
            }
            System.out.println();
        }


        // return bag[N-1][K];
        return Arrays.stream(bag[N-1]).max().getAsInt();


    }


}

////////// 생각을 해보쟈ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ
// i    0   1   2
// w    6   4   3
// v    13  8   6
// max  13  13  14


// bag[i][j]
//    0   1   2
// 0
// 1
// 2

/*
    static int maxItem(int[] W, int[] V){
        int [][] sum = new int[N+1][N+1];



        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if(sum[i][k]+sum[k][j]<sum[i][j]){
                        sum[i][j]=sum[i][k]+sum[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf(sum[i][j]+",");
            }
            System.out.println();
        }

        return sum[N][N];
    }
*/
          /*
    static int maxItem(int[] W, int[] V) {


        int[] bag = new int[K+1];
 //       int[] w = new int[K+1];

        for (int i = 0; i < N; i++) {
            int next=W[i];
            int pred=0;
            for (int j = 0; j < i; j++) {
                next+=W[j];
                pred+=W[j];
            }
            for (int j = i; j >=0; j--) {
                //next=j>0?next+W[j]:W[i];
                //pred=j>0?pred+W[j]:0;
                if(next<=K) {
                    bag[next]=Math.max(bag[next],bag[pred]+V[i]);
                }
                next-=W[j];
                pred-=W[j];


            }

        }
        for (int i = K; i >0 ; i--) {
            bag[i]=Math.max(bag[i],bag[i-1]);

        }

        for(int i : bag)
            System.out.printf(i+",");
        System.out.println();

    return bag[K];

}
    */

            /*
        int w_max=Arrays.stream(W).max().getAsInt();
        int v_max=Arrays.stream(V).max().getAsInt();




        int[] weight = new int[K];
        int[] value = new int[K];



        for (int i = 0; i < K; i++) {

            for (int j = 0; j < N; j++) {
                if(i==W[j]-1){
                    weight[i]++;
                    //value[W[j]-1]=Math.max(value[W[j]-1],V[j]);
                }
            }
        }

        for(int i : weight)
            System.out.printf(i+",");
        System.out.println();
        for(int i : value)
            System.out.printf(i+",");
        System.out.println();
*/


            /*
        for (int i = 0; i < K; i++) {
            bag[i+1]=Math.max(bag[i+1],bag[i]);
        }



        int pred;

        for (int i = 0; i <N-1 ; i++) {
            pred=i>0?i-1:0;
            // 6 4 3 5
            //
            // 0 1 2 3 4 5
            //
            //    6 8 12 13
            // 0 1 2 3 4 5 6 7
            // 0 0 3 4 5 6 0
            // 0 6 5 4 3 0 0
            // i =0 , weight 6, weight 1인 애 + V[i]
            // weight 6, index 6 이후에 있는
            int next=0;
            if(w[W[i]]+W[i]<=K){
                if(bag[w[i]+W[i]]<bag[w[i]+W[i]]+bag[w[i]]+V[i]){
                    bag[w[W[i]]+W[i]]=bag[w[W[i]]]+V[i];
                    w[w[W[i]]+W[i]]=w[W[i]]+W[i];
                }
                //bag[w[i]+W[i]]=Math.max(bag[w[i]+W[i]],bag[w[i]]+V[i]);
            }
         //   bag[i+1]=Math.max(bag[i+1],bag[i]);
        }

        for(int i : bag)
            System.out.printf(i+",");
        System.out.println();

        for(int i : w)
            System.out.printf(i+",");
        System.out.println();
*/





