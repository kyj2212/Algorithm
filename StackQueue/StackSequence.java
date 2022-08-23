package StackQueue;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class StackSequence {

    static int [] input;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // input 값 받기
         input = new int[n];
        for(int i =0;i<n;i++){
            input[i]=Integer.parseInt(br.readLine());
        }

        int start=1;
        int max=0;

        for(int i=0;i<n-1;i++){
            // 다음값보다 크면 넘어가
            if(input[i]>input[i+1]){
                // i+1 이 마지막 값일 때
                if(i==n-2){
                    cal(i+1, start, max, sb);
                }
            }
            else{
                cal(i,start,max,sb);
                start=input[max]+1;
                max=i+1;
            }
        }

        if(!stack.isEmpty()){
            System.out.println("NO");
        }else{
            System.out.println(sb.toString());
        }
        br.close();
    }

    public static void cal(int i, int start, int max, StringBuilder sb) {
        for(int j=start;j<=input[max];j++){
            stack.push(j);
            sb.append("+").append("\n");
        }
        for(int j=max;j<=i;j++){
            if(input[j]== stack.peek()){
                stack.pop();
                sb.append("-").append("\n");
            }
        }
    }
}
