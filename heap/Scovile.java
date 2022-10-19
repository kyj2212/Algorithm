package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Scovile {

    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;

        int answer =0 ;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i : scoville){
            pq.add(i);
        }

        while(pq.peek()<K){
            mix(pq,K);
            answer++;
        }
        System.out.println(answer);
    }

    private static void mix(PriorityQueue<Integer> pq,int K){
        int min1 = pq.poll();
        int min2 = pq.poll();
        pq.add(min1 + min2*2);
    }
}
