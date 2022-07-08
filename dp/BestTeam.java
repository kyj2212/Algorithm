package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BestTeam {

    static int N; // 전체 플레이어 수
    static int t=4; // 팀별 플레이어 정원

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] vW = new int[1000];
        int[] vB = new int[1000];


        String str;

        while((str=br.readLine())!=null) {
            StringTokenizer st = new StringTokenizer (str," ");
            vW[N]=Integer.parseInt(st.nextToken());
            vB[N]=Integer.parseInt(st.nextToken());
            N++;
        }



        br.close();


        System.out.println(N);
        System.out.println(makeTeam(vW,vB));

    }
    static int makeTeam(int[] vW, int[] vB){


        int [] W = new int[N];
        int [] B = new int[N];

        for (int i = 0; i < N; i++) {
            W[i]=vW[i];
            B[i]=vB[i];
        }

        for(int i : W)
            System.out.printf(i+",");
        System.out.println();


        for(int i : B)
            System.out.printf(i+",");
        System.out.println();


        int [][] Tw = new int[t][N+1];
        int [][] Tb = new int[t][N+1];


        for (int i = 0; i < N+1; i++) {
            // 1개씩 넣어


            for(int num = 1; num< (i<t?i+2:t); num++){


                // 30 명 아직 안될때
                // white 바꿔야 할때
                int prev=i>0?i-1:0;
                if(Tw[num][prev]<Tw[num-1][prev]+W[i]){
                    Tw[num][i]=Tw[num-1][prev]+W[i];

                }
                // black 바꿔야 할때
                else if(Tb[num][i]<Tb[num-1][prev]+B[i]){
                    Tb[num][i]=Tb[num-1][prev]+B[i];
                }
            }
            // 30명 넘었을 때,

        }



        return 0;
    }
}

