package hashing;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class PhoneNumberList {
    public static void main(String[] args) {
        String[] phone_book = {"1122223","1235666666666666666666666","12","567","88"};
        boolean answer = true;

        Map<String,Boolean> map = new HashMap<>();
        for(String phone: phone_book){
            map.put(phone,true);
        }
        for(String key : map.keySet()){

        }
        L1 :
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
    static boolean findSub(String key, String target){

        final int len=key.length();
        if(len>target.length()){
            return true;
        }
        for(int i=0;i<len;i++ ){
            if(key.charAt(i)!=target.charAt(i)) {
                return true;
            }
        }
        return false;
    }
}
