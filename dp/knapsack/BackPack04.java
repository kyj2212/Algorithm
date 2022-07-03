package DinamicProgramming;

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



//////////////// BackPack 풀이 1 /////////////
class BackPack01 {
    static int N;
    static int K;

    void sol01() throws IOException {

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
        System.out.println(maxV(W,V));

    }

    static int maxV(int[] W, int[] V){

        ArrayList<Product> p = sort(W,V);
        for (int i = 0; i < N; i++) {
            W[i]=p.get(i).w;
            V[i]=p.get(i).v;
        }

        System.out.printf("W : ");
        for(int j : W)
            System.out.printf(j+",");
        System.out.println();
        System.out.printf("V : ");
        for(int j : V)
            System.out.printf(j+",");
        System.out.println();

        int[] sum = new int[N+1];
        int[] sumW = new int[N+1];

        for (int i = 0; i <N; i++) {
            //sum[i+1]=Math.max(sum[i],V[i]);
            for (int j = i;j < N; j++) {

                if(sumW[i]+W[j]<=K) {
                    if(sum[j+1]<sum[i]+V[j]){
                        sum[j+1]=sum[i]+V[j];
                        sumW[j+1]=sumW[i]+W[j];
                    }
                }
            }

            if(sum[i+1]<sum[i]){
                sum[i+1]=sum[i];
                sumW[i+1]=sumW[i];
            }
            //  sum[i+1]=Math.max(sum[i+1],sum[i]);
            //  sumW[i+1]=Math.max(sumW[i+1],sumW[i]);
        }

        for(int k : sumW)
            System.out.printf(k+",");
        System.out.println();

        for(int k : sum)
            System.out.printf(k+",");
        System.out.println();

        return sum[N];

    }


    static ArrayList sort(int[] W, int[] V){

        ArrayList<Product> p = new ArrayList<>(N+1);

        for (int i = 0; i < N; i++) {
            p.add(new Product(W[i],V[i]));
        }
        p.sort((s1,s2)->s1.compareTo(s2));

        return p;

    }

}
class Product {
    int w;
    int v;

    Product(int w, int v){
        this.w = w;
        this.v = v;
    }

    public int compareTo(Product s2) {
        if(this.w > s2.w)
            return 1;
        else if (this.w==s2.w){
            if(this.v < s2.v)
                return 1;
            else if (this.v==s2.v){
                return 0;
            }
            else
                return -1;
        }
        else
            return -1;
    }
}
//////////////// BackPack 풀이 1 /////////////


//////////////// BackPack 풀이 2 /////////////
class BackPack02 {
    static int N;
    static int K;

    void sol01() throws IOException {

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
        System.out.println(bagVtoK(W,V,K,N)[1]);

    }

    static int[] bagVtoK(int[] W, int[] V, int k, int n){
        int [] bagW = new int[n];
        int [] bagV = new int[n];
        int [] bagToK = new int[2];
        int [] bag = new int[2];
        int pred =0;
        int predW,predV;

        for (int i = 0; i < n; i++) {
            predW = i>0?bagW[i-1]:bagW[0];
            predV = i>0?bagV[i-1]:bagV[0];

            if (predW+ W[i] <= k) {
                bagW[i] = predW + W[i];
                bagV[i] = predV + V[i];
            }
            else {
                if(W[i]<k) {
                    bagToK = bagVtoK(W, V, k - W[i], i); // i 를 포함해 pred=i
                    if (predV<bagToK[1]+V[i]) {
                        bagW[i]=bagToK[0]+W[i];
                        bagV[i]=bagToK[1]+V[i];
                    }
                }
                if(bagV[i]<predV) {
                    bagW[i] = predW;
                    bagV[i] = predV;
                }
            }
        }
        bag[0]=bagW[n-1];
        bag[1]=bagV[n-1];
        return bag;
    }

    static void maxV(int W, int V){

        int [] bagW = new int[N];
        int [] bagV = new int[N];

        int pred;
        int noUse;

        // bagV[pred] 로 할 수 없어 bagV[pred] 할 때 다른 걸 구해야해. 무엇을 인자로줄까
        // bagW[pred] + W[i] > K 클 때,
        // W[i] 를 넣거나
        //// K= K-W[i] , N=N-1, bagV[i] = V[i] + " bagV[no V[i], K=K-W[i]]"
        // W[i] 를 넣지 않거나 = bagW[i]=bagW[pred]
        // bagV[i] = Math.Max();





    }



    static int maxV6(int[] W, int[] V){
        int pred=0;
        int [] sumW = new int[N];
        int [] maxV = new int[N];
        int [] w= new int[N];
        int [] v= new int[N];
        int max;
        for (int i = 0; i < N; i++) {
            if(sumW[pred]+W[i]<=K){
                // if(maxV[i]<maxV[pred]+V[i]){
                maxV[i]=maxV[pred]+V[i];
                sumW[i]=sumW[pred]+W[i];
                pred=i;
                // }
            }else{
                for (int start = 0; start < i; start++) {

                    if(W[i]<=K){
                        w[start]=W[i];
                        v[start]=V[i];
                    }
                    for (int k = 0; k < i; k++) {
                        if(w[start]+W[k]<=K){
                            //        if(v[j]<v[j]+V[k]){
                            w[start]=w[start]+W[k];
                            v[start]=v[start]+V[k];

                            //       }
                        }
                    }
                    if(v[start]<(start>0?v[start-1]:v[0])){
                        v[start]=v[start-1];
                        w[start]=w[start-1];
                    }

                }


/*
                for (int j = 0; j < i; j++) {
                    if(W[i]<=K){
                        w=W[i];
                        v=V[i];
                    }
                    for (int k = j; k < i; k++) {

                        if(w+W[k]<=K){
                            if(v<v+V[k]){
                                w=w+W[k];
                                v=v+V[k];
                            }
                        }
                    }
                }

                */

                if((i>0?v[i-1]:v[0])>maxV[pred]){
                    maxV[i]=v[i-1];
                    sumW[i]=w[i-1];
                    pred=i;
                }else{
                    maxV[i]=maxV[pred];
                    sumW[i]=sumW[pred];
                }
            }
        }
        return maxV[N-1];
    }





   /*
    static void sort2(int[] W, int[] V){

        TreeMap<Integer,Integer> w = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            w.put(i,W[i]);
        }


        for (int i = 0; i < N; i++) {
            System.out.printf(w.get(i)+",");
        }
        System.out.println();
        Object[] val = w.values().toArray();
        Arrays.sort(val);



        for (int i = 0; i < N; i++) {
            System.out.printf(w.get(i)+",");
        }
        System.out.println();

    }


    static int maxV5(int[] W, int[] V){
        ArrayList<Item> bag = sort(W,V);
        for (int i = 0; i < N; i++) {
            W[i]=bag.get(i).w;
            V[i]=bag.get(i).v;
        }


        System.out.printf("W: ");
        for(int k : W)
            System.out.printf(k+",");
        System.out.println();

        System.out.printf("V: ");
        for(int k : V)
            System.out.printf(k+",");
        System.out.println();

        int[] sW=new int[N];
        int[] sV=new int[N];
        int max=0;

        if(W[0]<=K){
            sW[0]=W[0];
            sV[0]=V[0];
        }
        for (int i = 1; i < N; i++) {

            for (int next = i; next < N; next++) {

                if(sW[i]+W[next]<=K){
                    if(sV[next]<sV[i]+V[next]){
                        sV[next]=sV[i]+V[next];
                        sW[next]=sW[i]+W[next];
                    }
                }

            }


            //max=Math.max(Arrays.stream(sV).max().getAsInt(),max);
        }



        for(int k : sW)
            System.out.printf(k+",");
        System.out.println();

        for(int k : sV)
            System.out.printf(k+",");
        System.out.println();


        return Arrays.stream(sV).max().getAsInt();
    }


    static int maxV4(int[] W, int[] V){

        int[] sumW = new int[N];
        int[] maxV= new int[N];

        int max=0;

     //   PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
     //   Queue<Integer> q = new LinkedList<>();
     //   Queue<Integer> pred = new LinkedList<>();
     //   for (int i = 0; i < N; i++) {
     //       q.add(i);
      //  }

   for (int i = 0; i < N; i++) {
       for (int j = i; j < N; j++) {
           maxV[j]=V[j];
           sumW[j]=W[j];
       }
            for (int j = i+1; j < N-1; j++) {
                if(sumW[i]+W[j]<=K){
                    if(maxV[j]<maxV[i]+V[j]||(maxV[j]==maxV[i]+V[j]&&sumW[j]>sumW[i]+W[j])){
                        maxV[j]=maxV[i]+V[j];
                        sumW[j]=sumW[i]+W[j];
                    }
                }else if(maxV[j]<maxV[i]||(maxV[j]==maxV[i]&&sumW[j]>sumW[i])){
                    maxV[j]=maxV[i];
                    sumW[j]=sumW[i];
                }
            }
        max=Math.max(max,maxV[N-1]);
   }




        for(int i : sumW)
            System.out.printf(i+",");
        System.out.println();
        for(int i : maxV)
            System.out.printf(i+",");
        System.out.println();

        return max;

    }


    static int maxV3(int[] W, int[] V){

        int[] sumW = new int[N+1];
        int[] maxV = new int[N+1];

        for (int i = 0; i < N; i++) {
            if(maxV[i]<V[i]){
                maxV[i]=V[i];
                sumW[i]=W[i];
            }

            for (int j = i+1; j< N; j++) {

                if (sumW[i] + W[j] <= K) {
                    //maxV[i+1]=Math.max(maxV[i+1],maxV[i]+V[i]);
                    if (maxV[j] < maxV[i] + V[j]) {
                        maxV[j] = maxV[i] + V[j];
                        sumW[j] = sumW[i] + W[j];
                    }
                }else if(j==N-1){
                    if (maxV[j] < maxV[i]) {
                        maxV[j] = maxV[i];
                        sumW[j] = sumW[i];
                    }
                }
            }

        }



        for(int i : sumW)
            System.out.printf(i+",");
        System.out.println();
        for(int i : maxV)
            System.out.printf(i+",");
        System.out.println();

        return maxV[N-1];
    }


    static int maxV2(int[] W, int[] V){
        int [][] sum = new int[N+1][N+1];



        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum[i][j]=V[j];
            }
        }

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


}

//////////////// BackPack 풀이 2 /////////////


//////////////// BackPack 풀이 3 /////////////
class Item {
    int w, v;
    Item(int w, int v){
        this.w=w;
        this.v=v;
    }

    int compareTo(Item i2) {
        if(this.v>i2.v)
            return -1;
        else if(this.v<i2.v)
            return 1;
        else {
            if(this.w>i2.w)
                return -1;
            else if(this.w>i2.w)
                return 1;
            else return 0;
        }
    }

    public String toString() {
        return "("+w+","+v+")";
    }

}

class BackPack03 {
    static int N;
    static int K;

    void sol01() throws IOException {

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



    }



    static ArrayList<Item> sort(int[] W, int[] V){
        ArrayList<Item> bag = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            bag.add(new Item(W[i],V[i]));
        }
        bag.sort((i1,i2)->i1.compareTo(i2));
        return bag;
    }

    static int maxV5(int[] W, int[] V){
        ArrayList<Item> bag = sort(W,V);
        for (int i = 0; i < N; i++) {
            W[i]=bag.get(i).w;
            V[i]=bag.get(i).v;
        }


        System.out.printf("W: ");
        for(int k : W)
            System.out.printf(k+",");
        System.out.println();

        System.out.printf("V: ");
        for(int k : V)
            System.out.printf(k+",");
        System.out.println();

        int[] sW=new int[N];
        int[] sV=new int[N];
        int max=0;

        if(W[0]<=K){
            sW[0]=W[0];
            sV[0]=V[0];
        }
        for (int i = 1; i < N; i++) {

            for (int next = i; next < N; next++) {

                if(sW[i]+W[next]<=K){
                    if(sV[next]<sV[i]+V[next]){
                        sV[next]=sV[i]+V[next];
                        sW[next]=sW[i]+W[next];
                    }
                }

            }


            //max=Math.max(Arrays.stream(sV).max().getAsInt(),max);
        }



        for(int k : sW)
            System.out.printf(k+",");
        System.out.println();

        for(int k : sV)
            System.out.printf(k+",");
        System.out.println();


        return Arrays.stream(sV).max().getAsInt();
    }

    static int maxV_spp(int start,int [] W, int [] V){


        for (int i = start; i < N; i++) {

            for (int j = 1; j < N; j++) {


            }


        }
        return 0;


    }


}
//////////////// BackPack 풀이 3 /////////////

     /*
        int[] sum = new int[N];
        int[] sumW = new int[N];
        sum[0]=V[0];
        sumW[0]=W[0];
        for (int i = 0; i < N-1; i++) {
            sumW[i+1]=Math.max(sumW[i+1],sumW[i]+W[i+1]);
        }
        for (int i = 0; i < N-1; i++) {
            if(sumW[i+1]<=K)
                sum[i+1]=Math.max(sum[i+1],sum[i]+V[i+1]);
            sum[i+1]=Math.max(sum[i+1],sum[i]);
        }

        for(int i : sumW)
            System.out.printf(i+",");
        System.out.println();
        for(int i : sum)
            System.out.printf(i+",");
        System.out.println();

        return sum[N-1];
*/
/*
        for (int i = N-1; i >=0 ; i--) {
            for (int j = i; j >= 0; j--) {

                if(sumW[j]+W[j]<=K){
                    //sum[i+j]=Math.max(sum[i+j],sum[i]+V[j]);
                    if(sum[i]<sum[j]+V[j]){
                        sum[i]=sum[j]+V[j];
                        sumW[i]=sumW[j]+W[j];
                    }
                }
           }
 */
/*
                if(sumW[i][j]+W[j]<=K){
                    //sum[i+j]=Math.max(sum[i+j],sum[i]+V[j]);
                    if(sum[i][j]<sum[i][j]+V[j]){
                        sum[i][j]=sum[i][j]+V[j];
                        sumW[i][j]=sumW[i][j]+W[j];
                    }
                }
*/

/*
    static Map<Integer,Integer> sort1(int[] W, int[] V){
        Comparator<Integer> comparator = (s1, s2)->s2.compareTo(s1);
        Map<Integer,Integer> map = new TreeMap<>(comparator);
        for (int i = 0; i < N; i++) {
            map.put(W[i],V[i]);
        }
        return map;
    }


    static HashMap sort(int[] W, int[] V){
        HashMap m = new HashMap<>();
        for (int i = 0; i < N; i++) {
            m.put(W[i],V[i]);
        }
        Object[] mapkey = m.keySet().toArray();
        Arrays.sort(mapkey);
        return m;
    }


 */