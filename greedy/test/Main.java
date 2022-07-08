package greedy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in=br.readLine();
        int e=in.length()-1;

        System.out.println(e);
        int s=0;
        int n=0;
        while(s<=e) {
            if(in.charAt(e)!=in.charAt(s)) {
                if(n<s+1)n=s+1;
                s++;
                e=in.length()-1;
            }else {
                s++;
                e--;
            }
        }
        System.out.println(in.length()+n);

        br.close();
    }
}
