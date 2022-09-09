package hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InCompletion {
    public static void main(String[] args) {
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};

        String answer="";
        Map<String,Integer> map = new HashMap<>();
        for(String p : participant){
            Integer cnt = map.get(p);
            if(cnt==null){
                map.put(p,1);
            }else{
                map.replace(p,++cnt);
            }
        }
        for(String c : completion){
            Integer cnt = map.get(c);
            if(cnt==1){
                map.remove(c);
                continue;
            }
            if(cnt>1){
                map.replace(c,--cnt);
                continue;
            }
        }
        for(String s : map.keySet()){
            answer=s;
        }
        System.out.println(answer);
    }

}
