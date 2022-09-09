package hashing;

import java.util.HashMap;
import java.util.Map;

public class PhoneKetMon {
    public static void main(String[] args) {
        int[] nums = {3,3,3,2,2,2};
        int answer = 0;

        Map<Integer,Integer> map = new HashMap<>();

        for(int n : nums){
            map.put(n,map.getOrDefault(n,0)+1); // 기존 에 있는 cnt ++ 넣거나, 없으면 1 넣기
        }
        int len = nums.length/2;
        int kind = map.keySet().size();

        answer = len> kind ? kind : len;

        System.out.println(answer);
    }
}
