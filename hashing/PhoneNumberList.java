package hashing;

import java.util.HashMap;
import java.util.Map;

public class PhoneNumberList {
    public static void main(String[] args) {
        String[] phone_book = {"12","123","1235","567","88"};
        boolean answer = true;

        Map<String,Boolean> map = new HashMap<>();
        L1:
        for(String phone : phone_book){
            for(String key : map.keySet()){
                if(!findSub(key,phone)){
                    answer=false;
                    break L1;
                }
            }
            map.put(phone,true);
        }

        System.out.println(answer);
    }
    static boolean findSub(String a, String b){
        String key,target;
        if(a.length()> b.length()){
            key = b;
            target = a;
        }else{
            target=b;
            key = a;
        }
        for(int i =0;i<key.length();i++){
            if(target.charAt(i)!=key.charAt(i)){
                return true;
            }
        }
        return false;
    }
}
