package dp;


//


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class QuitJob {
    static int N; // 퇴사일

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 퇴사일
        int[] T = new int[N];
        int[] P = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            T[i]=Integer.parseInt(st.nextToken());
            P[i]=Integer.parseInt(st.nextToken());
        }


       // IntConsumer f = System.out::println; 나중에 해보기

        br.close();

        System.out.println(sumP(T,P));

    }


    static int sumP(int[] T, int[] P){
        int [] sum =new int[N+1];

            // T : 6 4 3 5   : 4가 되야함
            // i : 0 1 2 3
        //   i+T: 6 7 5 8
        //   sum :

        int nextDay;

        // int i = time
        for(int i =0;i<N;i++){
            nextDay=i+T[i];
            if(nextDay<=N){
                sum[nextDay]=Math.max(sum[nextDay],sum[i]+P[i]);
            }
            sum[i+1]=Math.max(sum[i+1],sum[i]);
        }

        for(int i :sum)
            System.out.printf(i+",");
        System.out.println();


        return sum[N];


    }

/*
    static int maxP(int[] T, int[] P){

        int [] sum =new int[N];

        for (int i = 0; i <N ; i+=T[i]) {

            for (int j = 1; i+j < N; j+=T[i+j]) {
                if(i+j+T[i+j]<=N)
                    sum[i+1]=sum[0]+P[i+j];
               //System.out.println("i : "+i+" j : "+j+" sum :" + sum[i+1]);
            }
            if(i+T[i]<=N)
                sum[0]+=P[i];
          //  System.out.println("sum0 : "+sum[0]);

        }
  //      for (int i : sum)
 //          System.out.print(i+",");
  //      System.out.println();
        return Arrays.stream(sum).max().getAsInt();
    }
*/
/*

    static void TopBottomMax(int[] T, int[] P){
        int sumT=0;
        int sumP=0;
        int maxP=0;
        for (int i = N-1; i >=0 ;i--) {
            if(i+T[i]<=N) {
                sumP+=P[i];
                System.out.println("day : "+i+" sumP : "+sumP);
                for (int j = 1; j <= i; j++) {
                    if(T[i-j]<j) {
                        i = i - j+1;
                        break;
                    }
                    else if (j==i){
                        maxP=sumP;
                        sumP-=P[i];
                        System.out.println("day : "+i+" maxP : "+maxP);

                    }
                }
            }
        }
    }

    static int findMaxP(int[] T, int[] P){
        int maxP=0;
        int[] sumP = new int[N];
    //    int sumP=0;
    //    int day =0;
        for (int i = 0; i < N; i++) {
            int day=i;
        //    for (int j = 0; j <N ; j++) {
            for (int j = i; j <N ; j++) {
                day+=T[j];
             //   sumP+=P[j];
                sumP[i]+=P[j];

                if(day>N||sumP[i]<=maxP){
                    day-=T[j];
                //    sumP-=P[j];
                    sumP[i]-=P[j];
                }
                else{
        //           if(sumP>maxP){
                    maxP=sumP[i];
                    j=day-1;
          //         }
               //     if(sumP[i]>maxP)
              //          maxP=sumP[i];

                }
             //   System.out.println("day : "+day+" sumP : "+sumP+" maxP : "+maxP);

            }
                  System.out.println("day : "+day+" sumP : "+sumP[i]);
        }
      //      return maxP;
        return Arrays.stream(sumP).max().getAsInt();
    }

    static int findMaxP2(int[] T, int[] P){
        int maxP=0;
        int sumP=0;
        int day=0;

        for (int i = 0; i <N; i++) {
            for (int j = i; j < N; j++) {
                day+=T[j];
                sumP+=P[j];
                if(day>N){
                    day-=T[j];
                    maxP-=P[j];
                }
                else{



                    maxP=sumP;
                    j=day-1;
                }
                System.out.println("day : "+day+" sumP : "+maxP);
            }
        }
        return maxP;
    }


    static void rPick(int[] T, int[] P){
        int r=6;
        int[] pick= new int[N];
        int cnt=0;

        for (int i = 0; i < N; i++) {
            for (int k = i+r-1; k < N; k++) {
                for (int j = i; j < i+r-1; j++) {
                    System.out.printf(j+",");
                }
                System.out.println(k);
            }
        }

    }

    static void findMaxP3( int[] T, int[] P){
        int day=0;
        int sumP=0;
        int maxP=0;
        ArrayList max = new ArrayList();


        for (int i = 0; i < N; i++) {
            day+=T[i];
            sumP+=P[i];
            if(day>N) {
                day-=T[i];
                sumP-=P[i];
            }
            else{

                max.add(sumP);

                day-=T[i];
                sumP-=P[i];
                for (int j = i+1; j <N ; j++) {

                    day+=T[j];
                    sumP+=P[j];
                    if(day>N) {
                        day-=T[j];
                        sumP-=P[j];
                    }
                    else{
                        max.add(sumP);
                        j=day-1;
                    }
                }
                System.out.println("day : "+day+" sumP : "+sumP);
                i=day-1;
            }
            System.out.println("day : "+day+" sumP : "+sumP);
        }

        System.out.println(max);
    }
*/


}
