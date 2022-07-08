package StackQueue;

import java.util.*;

public class StackPrinter {




    public static void main(String[] args) {

  //      int[] priorities = {1};
        int[] priorities = {1, 1, 9,1,1,1};
        int location = 0;
//        Scanner sc = new Scanner(System.in);
//        int C =sc.nextInt();
//        int N = sc.nextInt();
//        int location = sc.nextInt();
//
//        int[] priorities = new int[N];
//
//        for (int i = 0; i < C; i++) {
//            for (int j = 0; j < N; j++) {
//                priorities[j]=sc.nextInt();
//            }
//        }

        Solution2 s = new Solution2();
        System.out.println(s.solution2(priorities,location));
    }

}



class Solution2 {
    public int solution2(int[] priorities, int location) {

        int answer = 0;
        int N = priorities.length;
        Stack<Doc> wait = new Stack<>();

        for (int i=0;i<N;i++) {
            Doc w= new Doc(priorities[N-i-1],N-i-1);
            wait.push(w);
        }


        for (int i=0;i<N;i++){
            answer++;

            if(print2(N-i,wait).l==location){
                break;
            }else continue;
        }
        return answer;
    }



    Doc print2(int N, Stack<Doc> wait) {

        // 첫번째 doc 꺼내고
        Stack<Doc> w = new Stack<>();
        Doc print = wait.pop();

        Stack<Doc> ts = new Stack<>();
        while (!wait.isEmpty()){
            if(wait.peek().p > print.p) {

                w.push(print);

                while(!w.isEmpty()) {
                    ts.push(w.pop());
                }
            }
            else {
                w.push(wait.pop());
            }
        }

        while(!ts.isEmpty()) {
            wait.push(ts.pop());
        }

        while (!w.isEmpty()) {
            wait.push(w.pop());
        }

        System.out.println("출력 : "+print.p+" location : "+print.l);

        return print;

    }

}



