package StackQueue;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;

public class IsPPAP {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(str);
        PPAP p = new PPAP(str);

        br.close();
/*
        if(p.isPPAPcnt(p))
            System.out.println(isPP(p));
        else System.out.println("NP");
*/
///*
        System.out.println("A : "+p.Acnt+" P : "+p.Pcnt);
        boolean cnt = p.isPPAPcnt(p);
//*/

        PPAPtoP(p);



 /*
        String isPPAP = isPP(p);
        if(cnt){
            System.out.println(isPPAP+" , isPPAPcnt "+cnt+", isPP " + isPPAP);

        } else {

            System.out.println("NP , isPPAPcnt : "+ cnt);
        }
 */



/*

        char[] arr = new char[1000000];
        for (int i = 0; i < str.length(); i++) {
            arr[i]=str.charAt(i);
        }
*/


    }

    static void changeP(int a, ArrayList<Character> sub){
        System.out.println("remove : ");
        for (int i = 0; i < sub.size(); i++) {
            if(sub.get(i)=='A'){
                a--;
                if(a==0) {
                    for (int j = 0; j < 3; j++) {
                        System.out.print(sub.get(a-2));
                        sub.remove(a-2);
                    }
                    System.out.println();
                }
            }
        }

    }

    static void PPAPtoP (PPAP p){
        ArrayList<Character> sub = p.arr;

        while(p.P[MaxP(p)]>=2) {
            changeP(MaxP(p),sub);
        }

        System.out.println("After PPAPtoP : ");
        for(char c : sub)
            System.out.print(c);
        System.out.println();

    }

    static int MaxP (PPAP p){
        int max=0;
        for (int i = 1; i <= p.Acnt; i++) {
                if(p.P[max]<p.P[i]){
                    max=i;
                }
        }
        return max;
    }



    /*
    static boolean isPPP (StackQueue.PPAP p){
        ArrayList<Character> sub = p.arr;
        Stack<Character> s = new Stack<>();

        int cntP=0;
        for (int i = 0; i < sub.size(); i++) {
            if(cntP==3){
                changeP(i,sub);
                return true;
            }
            else if(sub.get(i)=='A') {
                cntP=0;
            }
            cntP++;
        }
        return false;
    }

*/






    static boolean isAP (LinkedList<Character> sub, Stack<Character> s) {

        if (sub.pollLast() == 'P'&& sub.peekLast() == 'A') {
            sub.pollLast();
            return true;
        }
        else
            return false;
    }


    static boolean findAP (LinkedList<Character> sub, Stack<Character> s) {
        if(sub.size()>=2) {
            while (!isAP(sub, s)) {
                s.push('P');
            }
        } else{
            System.out.println("findAP, sub.size() : "+sub.size());
            return false;
        }
        return true;

    }

    /*        if(sub.pollLast()=='P'){
            if(sub.pollLast()=='A'){
                // AP++;
                return true;
            }
            else {
                //  AP++;
                s.push('P');
                if (sub.pollLast() == 'A')
                    return true;
                else if (sub.pollLast() == 'A') {
                    s.push('P');
                    return true;
                }else return false;
            }

        }else return false;*/







    static String isPP (PPAP p){

        LinkedList<Character> sub = p.list;

        //int d = p.Acnt;
        int AP  = 0;

        Stack<Character> s = new Stack<>();

        if(!isAP(sub,s))
            return "NP";
        else
            AP++;

        for (int d = p.Acnt-1; d >0; d--) {
            if(findAP(sub,s)){
                AP++;
                if(d==1){
                    int i = sub.size();
                    if(i>=2){
                        while(i>=2){
                            i--;
                            AP--;
                        }
                        break;
                    }else {
                        System.out.println("sub.size : " + i +"AP : "+AP);
                        return "NP";
                    }
                }
            }
            else{
                System.out.println("findAP false, d : "+ d);
                return "NP";
            }
        }
/*
        while(isAP(sub,s)){
            AP++;
            if(d==1){
                if(sub.size()==2){
                    AP--;

                    break;
                }
                else return "NP";
            } else {
                d--;
            }
        }
*/
        System.out.println("AP : "+AP);
        System.out.printf("last sub : ");
        for (char c : sub)
            System.out.print(c);
        System.out.println();



        while(!s.isEmpty()){
            s.pop();
            AP--;
        }




        if(s.isEmpty()&&AP==0)
            return "StackQueue.PPAP";
        else return "NP";


    }


}


class PPAP {
    static int Acnt = 0;
    static int Pcnt = 0;
    static int[] P = new int[0];
    static LinkedList<Character> list = new LinkedList();
    static ArrayList<Character> arr = new ArrayList();

    public PPAP(){
        this("PP");
    }

    public PPAP(@NotNull String str) {


        for(int i = 0;i<str.length();i++){
            arr.add(str.charAt(i));
            list.add(str.charAt(i));
            if (list.peekLast() == 'P') {
                Pcnt++;
            } else if (list.peekLast() == 'A') {
                Acnt++;
            }
            //  else System.out.println("no String");  // P,A 가 아닌 것은 들어오지 않는다고 가정
        }
        P = new int[Acnt+1];
        int a = 0;
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i)=='A'){
                a++;
            } else
                P[a]++;
        }
    }

    static boolean isPPAPcnt (PPAP cnt) {

        if(cnt.Acnt*2 +1 ==cnt.Pcnt){
            return true;
        }
        else return false;

    }

}


class PPAP1 {

    LinkedList<PPAP1> p = new LinkedList<>();

    PPAP1(){
        LinkedList<String> ppap1 = new LinkedList<>();
        ppap1.offer("StackQueue.PPAP");
    }

    PPAP1(int i, int num,LinkedList<PPAP1> sub){
        this.p.addAll(i,sub);
    }


    static boolean isP(char[] arr) {
        int Acnt=0;
        boolean isP=true;
        Stack<Character> p = new Stack();

        // arr.length !=0 인 경우만 와야함.
        if(arr[arr.length-1]!='P') {
            isP=false;
        }
        else {
            for (int i = arr.length-2; i >=0; i++) {
                if(arr[i]=='A'){
                }
                else if (arr[i]=='P'){

                }
            }
        }
        return true;
    }

    static void splitPPAP(char[] arr) {

        Stack<Character> p = new Stack();
        Stack<Character>[] sub = new Stack[4];

        int n = 0;
        while (true) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i]!= 'A')
                    sub[n].push(arr[i]);
                else break;
            }
            n++;
            //sub[n].push(arr[i]);

            if (p.isEmpty())
                break;
        }

    }

    static boolean verifyPPAP(char[] arrCh) {
        boolean isPPAP = true;

        Stack<Character> p = new Stack();



        for(char c : arrCh) {
            p.push(c);
        }


/*        while (!p.isEmpty()) {





            for (int i = 0; i < arrCh.length; i++) {
                if (arrCh[i] == 'P') {
                    p.push(arrCh[i]);
                } else if (arrCh[i] == 'A') {
                    if (arrCh[i+1] == 'P' && p.size() >= 2) {
                        p.pop();
                        p.pop();
                        i++;
                        p.push('P');
                    } else {
                        p.push('A');
                    }
                } else {
                    System.out.println("str null");
                    isPPAP = false;
                }


            }
            for (int i=p.size()-1;i>=0;i--){
                arrCh[i] = p.pop();
            }

            if(arrCh.equals("StackQueue.PPAP")||arrCh.equals("P")){
                break;
            } else if(p.size()<4) {
                isPPAP = false;
            }


        }*/




        return isPPAP;



    }


}
