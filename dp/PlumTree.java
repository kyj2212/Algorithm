package dp;

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

}



