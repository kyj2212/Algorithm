package test.kakao;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BridgeTruck {

    public static void main(String[] args) {
        BridgeTruck f = new BridgeTruck();
        f.solution(2, 10, new int[]{7, 4, 5, 6});
        f.solution(100, 100, new int[]{10});
        f.solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10});
    }

    public void solution(int bridge_length, int weight, int[] truck_weights){

        int answer = 0;
        System.out.println(String.format("시작 bridge len : &d weight : &d",  bridge_length,weight ));
        System.out.println(String.format("시작 truck_weights :: &s :: ", Arrays.toString(truck_weights) ));


        answer = getMin(bridge_length,weight,truck_weights);

        System.out.println("answer :: " + answer);

    }


    public int getMin(int l, int maxW, int[] trucks){

        Queue<Integer> bridge = new LinkedList<>(); // 다리 위에서 남은 시간
        Queue<Integer> waiting = new LinkedList<>(); // 대기중인 트럭

        for(int truck : trucks){
            waiting.add(truck);
        }

        int time = 0;
        int curW = 0;

        for(int i =0; i< l;i++){
            bridge.add(0); // 다리 위에 없으니까 트럭마다 남은시간 0 (다리 길이 = 올라갈수 있는 트럭개수)
        }

        while(!bridge.isEmpty()){
            time++; // 1초 간다

            int left = bridge.poll(); // 맨끝 트럭 빼기
            curW-=left;

            if(!waiting.isEmpty()){
                if(curW + waiting.peek() <= maxW){
                    int t = waiting.poll();
                    bridge.add(t);
                    curW += t;
                }else{
                    bridge.add(0);
                }
            }
        }

        return time;
    }


}
