package hashing;

import java.util.HashMap;
import java.util.Map;

public class Camouflage {
    public static void main(String[] args) {
        String[][] clothes = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
       // String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

        int answer = 1;

        // value, key 조합
        // 종류 2개
        // 2 1
        // 3*2 -1 = 6-1

        // 종류 3개
        // 2 2 1
        // 3*3*2 = 9*2 = 18 -1 = 17



        Map<String, Integer> map = new HashMap<>();
        for(String [] item : clothes){
            int cnt=map.getOrDefault(item[1],0);
            map.put(item[1],++cnt);
        }

        int kind = map.keySet().size();


        for(String key : map.keySet()){
           answer*=(map.get(key)+1);
        }

        System.out.println(answer-1);



    }
}
