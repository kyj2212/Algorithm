package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Scovile {

    public static void main(String[] args) {
//        int[] scoville = {0, 0, 0, 0, 0, 0};
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;

        int answer =0 ;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i : scoville){
            pq.add(i);
        }

        while(pq.peek()<K){
            if(!mix(pq,K)){
                answer=-1;
                break;
            }
            answer++;
        }
        System.out.println(answer);
    }

    private static boolean mix(PriorityQueue<Integer> pq,int K){
        if(pq.size()<2){
            return false;
        }
        int min1 = pq.poll();
        int min2 = pq.poll();
        pq.add(min1 + min2*2);
        return true;
    }
}
