package iotest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AplusB01 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int A;
        int B;


        String str;

       while((str=br.readLine())!=null) {
            StringTokenizer st = new StringTokenizer (str," ");

            A=Integer.parseInt(st.nextToken());
            B=Integer.parseInt(st.nextToken());
            sb.append(A+B).append('\n');

        }


        br.close();

        System.out.println(sb);





    }


}
