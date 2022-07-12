package showme.s01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



class Main {

    static int N;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int[] a = new int[N];
        int[] b = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        StringTokenizer stb = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(stb.nextToken());
        }


        br.close();

        System.out.println(sameSum(a, b));
    }

    static int sameSum(int[] a, int[] b) {
        long aSum = 0;
        long bSum = 0;
        int cnt = 0;



        return cnt;
    }

}
public class showme01 {

}