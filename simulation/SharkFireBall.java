package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SharkFireBall {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<int[]> fireBalls = new ArrayList<>();
        for(int i =0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int [] fireball = new int[5]; // r,c,m,s,d
            for (int j=0;j<5;j++){
                fireball[j]=Integer.parseInt(st.nextToken());
            }
            fireBalls.add(fireball);
        }

        for(int i=0;i<K;i++){
            for(int[] fireBall : fireBalls){
                move(fireBall);
            }
            fireBalls=merge(fireBalls);
        }
        int sum=0;
        for(int[] fireBall : fireBalls){
            sum+=fireBall[2];
        }

        System.out.println(sum);
    }
    static List<int[]> merge(List<int[]> fireBalls){
        List<int[]> fireBallList = new ArrayList<>();

        int [] sumM = new int[N*N];
        int [] sumS = new int[N*N];
        int [] cnt = new int[N*N];
        int [] idx = new int[N*N];
        int [] direction = new int[N*N]; // 1 모두 짝, 2 모두 홀, 3 다를때
        for(int[] fireBall : fireBalls){
            int r = fireBall[0]-1;
            int c = fireBall[1]-1;
            cnt[r*N+c]++;
            idx[r*N+c]=fireBalls.indexOf(fireBall);
            sumM[r*N+c]+=fireBall[2];
            sumS[r*N+c]+=fireBall[3];
            if(direction[r*N+c]==0){
                direction[r*N+c]=fireBall[4]%2+1;
            }
            else if(direction[r*N+c]!=3 && ((fireBall[4]%2+1)!=direction[r*N+c])){
                direction[r*N+c]=3;
            }
        }
        for(int i =0;i<N*N;i++){
            if(cnt[i]==0){
                continue;
            }
            if(cnt[i]==1){
                fireBallList.add(fireBalls.get(idx[i]));
                continue;
            }
            if(cnt[i]>1 && sumM[i]/5>0){
                if(direction[i]!=3){ // 모두 홀, 또는 짝
                    for(int j=0;j<=6;j+=2){

                        fireBallList.add(new int[]{i / N + 1, i % N + 1, sumM[i] / 5, sumS[i] / cnt[i], j});
                    }
                }else{ // 아닐 때
                    for(int j=1;j<=7;j+=2){
                        fireBallList.add(new int[]{i/N+1,i%N+1,sumM[i]/5,sumS[i]/cnt[i],j});
                    }
                }
            }
        }
        return fireBallList;
    }
    static int[] move(int[] f){
        if(f[4]==7 || f[4]==0 || f[4]==1){
            f[0]=left(f[0],f[3]%N);
        }
        if(f[4]==5||f[4]==4||f[4]==3){
            f[0]=right(f[0],f[3]%N);
        }
        if(f[4]==7||f[4]==6||f[4]==5){
            f[1]=up(f[1],f[3]%N);
        }
        if(f[4]==1||f[4]==2||f[4]==3){
            f[1]=down(f[1],f[3]%N);
        }
        return f;
    }
    static int left(int r, int s){
        return r-s<=0? r-s+N : r-s;
    }
    static int right(int r, int s){
        return r+s>N ? r+s-N : r+s;
    }
    static int up(int c, int s){
        return c-s<=0 ? c-s+N : c-s;
    }
    static int down(int c, int s){
        return c+s>N ? c+s-N : c+s;
    }

    static void print(List<int[]> fireBalls){
        for(int[] fireBall : fireBalls) {
            System.out.println(fireBall[0]+","+fireBall[1] + ", " + fireBall[2] + "," + fireBall[3] + "," + fireBall[4]);
        }
    }
}
