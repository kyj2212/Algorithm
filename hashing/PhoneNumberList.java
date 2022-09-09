package hashing;

public class PhoneNumberList {
    public static void main(String[] args) {
        String[] phone_book = {"119", "97674223", "1195524421"};
        boolean answer = true;


        Phone number = new Phone();
        L1 :
        for(String phone : phone_book){
            for(int i=0;i<phone.length();i++){

                if(number.child[phone.charAt(i)-'0']==null){
                    number.child[phone.charAt(i)-'0']=new Phone();
                    continue;
                }else if(i==phone.length()-1){
                    answer=false;
                    break L1;
                }
            }
        }

        System.out.println(answer);
    }
    static class Phone{
        Phone[] child = new Phone[10];
    }
}
