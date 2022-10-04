package StackQueue;

import java.util.Stack;

public class RightBracket {
    public static void main(String[] args) {
//        String s = "(())()";
        String s = "(()(";
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        L1 :
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c){
                case '(' :
                    stack.push(c);
                    break;
                case ')':
                    if(stack.isEmpty()){
                        answer=false;
                        break L1;
                    }else{
                        stack.pop();
                    }
            }
        }
        if(!stack.isEmpty()){
            answer=false;
        }
        System.out.println(answer);
    }
}
