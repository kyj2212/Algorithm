package dp.knapsack;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//////////////// BackPack 풀이 1 /////////////
public class BackPack01 {
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

    static class Product {
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
}
//////////////// BackPack 풀이 1 /////////////

