package StackQueue;

import java.util.*;

public class QueuePrinter {


    // 1장 뽑을 때, 이미 뽑을 순서는 정해진다.
    // 뽑을 순서만 정해서 원하는 순서를 얻었을 때 바로 리턴하도록 하면 더 빠른 결과 얻을 수 있음
    // 개선해보기


    public static void main(String[] args) {
/*

       int[] priorities = {2,1,3,2};
        //int[] priorities = {1, 1, 9, 1, 1, 1};
        int location = 3;

*/
///*
        Scanner sc = new Scanner(System.in);
        int C =sc.nextInt();
        int [] answer = new int[C];


        for (int i = 0; i < C; i++) {
            int N = sc.nextInt();
            int location = sc.nextInt();

            int[] priorities = new int[N];
            for (int j = 0; j < N; j++) {
                priorities[j]=sc.nextInt();
            }

            Solution s = new Solution();
            answer[i]=s.solution(priorities,location);
        }
//*/

        for (int i = 0; i < C; i++) {
            System.out.println(answer[i]);
        }

    }



}


class Solution {
    public int solution(int[] priorities, int location) {

        int answer = 0;
        int N = priorities.length;

        LinkedList<Doc> waitq= new LinkedList<>();
        for (int i = 0; i < N; i++) {
            Doc w = new Doc(priorities[i], i);
            waitq.offer(w);
        }


        for (int i=0;i<N;i++){
            answer++;

            if(print(N-i,waitq).l==location){
                break;
            }else continue;
        }
        return answer;




    }


    Doc print(int N, LinkedList<Doc> waitq) {


        // 첫번째 doc 꺼내고
        LinkedList<Doc> w = new LinkedList<>();
        Doc print=new Doc(0,0);
        Doc max = Max(waitq);


        for (int cnt = 0; cnt < N-1; ) {
            print = waitq.poll();
            if(print.p==max.p&&print.l==max.l)   {
                break;
            }

            cnt =0;
            while(!waitq.isEmpty()){

                    Doc tmp = waitq.poll();
                    if(tmp.p > print.p) {
                        w.offer(tmp);
                    }
                    else {
                        w.offer(tmp);
                        cnt++;
                    }
                }

            if (print.p!=max.p)
                w.offer(print);
            else
                waitq.offer(print);

            while (!w.isEmpty()) {
                waitq.offer(w.poll());
            }



        }

      //  System.out.println("출력 : "+print.p+" location : "+print.l);

        return print;

    }

    Doc Max (LinkedList<Doc> list){
        LinkedList<Doc> l = (LinkedList<Doc>) list.clone();
        Doc max = l.peek();
        while(!l.isEmpty()){
            Doc tmp = l.poll();
            if(max.p < tmp.p){
                max=tmp;
            }
        }
        //System.out.println("max : "+max.p+","+max.l);
        return max;
    }

}



class Doc implements Comparable {

    int p;
    int l;

    Doc(int priorities, int location) {
        this.p = priorities;
        this.l = location;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
