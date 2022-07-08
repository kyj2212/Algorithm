package dp.knapsack;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


//////////////// BackPack 풀이 3 /////////////

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

    static class Item {
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