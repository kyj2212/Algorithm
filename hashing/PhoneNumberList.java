package hashing;

import java.util.HashMap;
import java.util.Map;

public class PhoneNumberList {
    public static void main(String[] args) {
        String[] phone_book = {"123","456","789"};
        boolean answer = true;

        Map<String,Boolean> map = new HashMap<>();

        L1 :
        for(String phone : phone_book){
            for(String key : map.keySet()){
                if(phone.length()< key.length()){
                    continue;
                }
                if(key.equals(phone.substring(0,key.length()))){
                    answer=false;
                    break L1;
                }
            }
            map.put(phone,true);
        }

        System.out.println(answer);
    }
}
