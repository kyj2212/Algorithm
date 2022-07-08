package greedy.boj;


/*
* 임한수와 임문빈은 서로 사랑하는 사이이다.

임한수는 세상에서 팰린드롬인 문자열을 너무 좋아하기 때문에, 둘의 백일을 기념해서 임문빈은 팰린드롬을 선물해주려고 한다.

임문빈은 임한수의 영어 이름으로 팰린드롬을 만들려고 하는데, 임한수의 영어 이름의 알파벳 순서를 적절히 바꿔서 팰린드롬을 만들려고 한다.

임문빈을 도와 임한수의 영어 이름을 팰린드롬으로 바꾸는 프로그램을 작성하시오.
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Palindrome {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        br.close();

        int [] kindNum = new int[26];

        for (int i=0;i<str.length();i++){
            for(int j=0;j<26;j++){
                if(str.charAt(i)=='A'+j){
                    kindNum[j]++;
                }
            }
        }

//        for(int i : kindNum)
//           System.out.printf(i+" ");

        int oddCnt=0;
        int mid=-1;
        for (int i = 0; i < 26; i++) {
            if(kindNum[i]%2!=0){
                oddCnt++;
                mid=i;
            }
        }

        ArrayList<Character> p = new ArrayList();


        if(oddCnt>1)
            System.out.println("I'm Sorry Hansoo");
        else {

            for(int i=0;i<26;i++){
                for(int j=0;j<kindNum[i]/2;j++){
                    p.add((char) ('A'+i));
                }
            }
            if (oddCnt==1){
                p.add((char)('A'+mid));
                kindNum[mid]--;
            }
            for(int i=25;i>=0;i--){
                for(int j=0;j<kindNum[i]/2;j++){
                    p.add((char) ('A'+i));
                }
            }
        }

        for(char c : p)
            System.out.print(c);



    }


}
/*
class KindChar {
    char kind;
    int kindNum;

    void kindChar(){
        kindChar(' ',0);
    }

    void kindChar(char kind, int kindNum){
        this.kind=kind;
        this.kindNum=kindNum;
    }
}


*/

/*
        int start=0;
        int end = arr.length-1;
        char tmp;
        int kind=0;



        int i=0;
        while(i<=end/2){
            if(arr[start+i]==arr[end-i]){
                i++;
                continue;
            }
            else{
                kind++;
                if( arr[start+i]==arr[start+i+1]){
                    arr[start+i+1]=arr[end-i];
                    arr[end-i] = arr[start+i];
                    i++;
                }
                else{
                    kind++;
                    tmp = arr[end/2];
                    arr[end/2] = arr[start+i];
                    arr[start+i]=tmp;
                    if(kind>=end/2) {
                        System.out.print("I'm Sorry Hansoo arr : ");
                        for(char c : arr)
                            System.out.print(c);
                        break;
                    }
                }


            }


        }

                System.out.println();
            System.out.printf("result : ");
            for(char c : arr)
                System.out.print(c);



*/



