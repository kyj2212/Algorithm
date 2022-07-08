package dp.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
