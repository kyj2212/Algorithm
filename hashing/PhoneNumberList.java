package hashing;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class PhoneNumberList {
    public static void main(String[] args) {
        String[] phone_book = {"12","1122223","1235666666666666666666666","567","88"};
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
        BigInteger key,target;
        if(a.length()> b.length()){
            key = new BigInteger(b);
            target = new BigInteger(a.substring(0,b.length()));
        }else{
            key = new BigInteger(a);
            target= new BigInteger(b.substring(0,a.length()));
        }
        return key.equals(target) ? false : true;
    }
}
