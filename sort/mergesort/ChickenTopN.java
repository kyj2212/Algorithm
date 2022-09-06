package sort.mergesort;

import java.io.*;
import java.util.StringTokenizer;

public class ChickenTopN {

    static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int [] input = new int[N];
        st = new StringTokenizer(br.readLine()," ");
        for(int i =0;i<N;i++){
            input[i]=Integer.parseInt(st.nextToken());
        }
        int k = Integer.parseInt(br.readLine());

        int[][] result = mergeSort(2,k,divide(1,input));
        for(int[] s : result){
            for(int i : s){
                bw.write(String.valueOf(i));
                bw.write(" ");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    public static int[][] divide(int start, int[] input){
        // 분할
        int[][] sub = new int[N/start][start];
        for(int i =0;i<N;i++){
            sub[i/start][i%start]=input[i];
        }
        return sub;
    }
    public static int[][] mergeSort(int start, int k, int[][] input){

        int[][] sub = new int[N/start][start];

        // 병합
        for(int i=0;i<sub.length;i++){
            int start1=0;
            int start2=0;
            int[] data1 = input[i*2];
            int[] data2 = input[i*2+1];
            // i 라인의 두개 그룹 비교 무조건 두개니까 그냥 비교
            for(int j=0;j<sub[i].length;j++){
                if(start1==data1.length){
                    sub[i][j]=data2[start2];
                    start2++;
                } else if(start2==data2.length){
                    sub[i][j]=data1[start1];
                    start1++;
                } else if(data1[start1]<data2[start2]){
                    sub[i][j]=data1[start1];
                    start1++;
                    continue;
                }
                else{
                    sub[i][j]=data2[start2];
                    start2++;
                    continue;
                }
            }
        }
        if(k==(N/start)){
            return sub;
        }
        return mergeSort(start*2, k,sub);
    }
}
