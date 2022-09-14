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

        List<FireBall> fireBalls = new ArrayList<>();
        for(int i =0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");
            fireBalls.add(new FireBall(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        for(int i=0;i<K;i++){
            for(FireBall fireBall : fireBalls){
                move(fireBall);
            }
            fireBalls=merge(fireBalls);
        }
        int sum=0;
        for(FireBall fireBall : fireBalls){
            sum+=fireBall.m;
        }

        System.out.println(sum);
    }
    static List<FireBall> merge(List<FireBall> fireBalls){
        List<FireBall> fireBallList = new ArrayList<>();

        int [] sumM = new int[N*N];
        int [] sumS = new int[N*N];
        int [] cnt = new int[N*N];
        int [] idx = new int[N*N];
        int [] direction = new int[N*N]; // 1 모두 짝, 2 모두 홀, 3 다를때
        for(FireBall fireBall : fireBalls){
            int r = fireBall.r-1;
            int c = fireBall.c-1;
            cnt[r*N+c]++;
            idx[r*N+c]=fireBalls.indexOf(fireBall);
            sumM[r*N+c]+=fireBall.m;
            sumS[r*N+c]+=fireBall.s;
            if(direction[r*N+c]==0){
                direction[r*N+c]=fireBall.d%2+1;
            }
            else if(direction[r*N+c]!=3 && ((fireBall.d%2+1)!=direction[r*N+c])){
                direction[r*N+c]=3;
            }
        }
        for(int i =0;i<N*N;i++){
            if(cnt[i]==1){
                fireBallList.add(fireBalls.get(idx[i]));
            }
            if(cnt[i]>1 && sumM[i]/5>0){
                if(direction[i]!=3){ // 모두 홀, 또는 짝
                    for(int j=0;j<=6;j+=2){
                        fireBallList.add(new FireBall(i/N+1,i%N+1,sumM[i]/5,sumS[i]/cnt[i],j));
                    }
                }else{ // 아닐 때
                    for(int j=1;j<=7;j+=2){
                        fireBallList.add(new FireBall(i/N+1,i%N+1,sumM[i]/5,sumS[i]/cnt[i],j));
                    }
                }
            }
        }
        return fireBallList;
    }
    static FireBall move(FireBall f){
        if(f.d==7 || f.d==0 || f.d==1){
            f.r=left(f.r,f.s%N);
        }
        if(f.d==5||f.d==4||f.d==3){
            f.r=right(f.r,f.s%N);
        }
        if(f.d==7||f.d==6||f.d==5){
            f.c=up(f.c,f.s%N);
        }
        if(f.d==1||f.d==2||f.d==3){
            f.c=down(f.c,f.s%N);
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
    static class FireBall {
        int r;
        int c;
        int m;
        int s;
        int d;

        FireBall(int r, int c,  int m, int s, int d){
            this.r=r;
            this.c=c;
            this.m=m;
            this.s=s;
            this.d=d;
        }

    }
    static void print(List<FireBall> fireBalls){
        for(FireBall fireBall : fireBalls) {
            System.out.println(fireBall.r+","+fireBall.c + ", " + fireBall.m + "," + fireBall.s + "," + fireBall.d);
        }
    }
}
