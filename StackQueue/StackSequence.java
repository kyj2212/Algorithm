package StackQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class StackSequence {


    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        boolean invalid = false;

        int input;
        int end = 0;
        for (int i = 0; i < n; i++) {
            input = Integer.parseInt(br.readLine());

            if (!stack.isEmpty()&&stack.peek() == input) {
                stack.pop();
                sb.append("-").append("\n");
            } else {
                if (end + 1 > input) {
                    invalid = true;
                    break;
                }
                for (int num = end + 1; num <= input; num++) {
                    stack.push(num);
                    sb.append("+").append("\n");
                }
                end = input;
                stack.pop();
                sb.append("-").append("\n");
            }
        }


        if (invalid) {
            System.out.println("NO");
        } else {
            System.out.println(sb.toString());
        }
        br.close();
    }


}
