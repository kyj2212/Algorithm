package math;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PrimePath {
    static boolean [] noPrime= new boolean[10000];
    static int cnt=0;
    public static void main(String[] args) throws IOException {



        // 4자리 소수 모두 구하기
        // 소수이면 false, 배수이면 true
        noPrime[0]=noPrime[2]=true;
        for(int i=2;i<=Math.sqrt(9999);i++){
            if(noPrime[i])
                continue;
            for(int j=i*i;j<=9999;j+=i)
                noPrime[j]=true;
        }




        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());



        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine()," ");
            String input=st.nextToken();
            String output=st.nextToken();

            int answer=cntStep(input,output);
            if(answer==-1)
                sb.append("Impossible").append("\n");
            sb.append(cntStep(input,output)).append("\n");
        }


        System.out.println(sb.toString());
/*
        int [][] input = new int[T][4];
        int [][] output = new int[T][4];
        for(int i =0;i<T;i++){
            String [] s = br.readLine().split(" ",2);
            input[i]=Arrays.stream(s[0].split("",4)).mapToInt(Integer::parseInt).toArray();
            output[i]=Arrays.stream(s[1].split("",4)).mapToInt(Integer::parseInt).toArray();

        }
*/








    }

    static int cntStep(String input, String output){
        cnt =0;

        for(int i=0;i<4;i++){

            String newInput=change(input,i,intAt(output,i));
            if(isPrime(newInput)) { // 소수이면
                input=newInput;
                cnt++;
                continue;
            }

            for (int j = i<1?1:0; j < 10; j++) {

                if(j==intAt(input,i))
                    continue;

                newInput=change(input,i,j);
                if(isPrime(newInput)) { // 소수이면
                    input=newInput;
                    cnt++;
                    break;
                }

            }
        }

        if(input.equals(output))
            return cnt;
        else return -1;
    }
    static boolean isPrime(String input){
        return !noPrime[Integer.parseInt(input)];
    }

    static String change(String input,int idx, int n){
        String newInput=changeOne(input,idx,n);

        return newInput;
    }

    static int[] getIntArr (String num){
        int[] arr= Arrays.stream(num.split("",4))
                .mapToInt(Integer::parseInt)
                .toArray();
        return arr;
    }

    static int intAt(String num,int idx){
        return getIntArr(num)[idx];
    }

    static String changeOne(String input, int idx, int n){
        int[] arr= getIntArr(input);
        arr[idx]=n;
        StringBuilder strb = new StringBuilder();

        strb.append(Arrays.toString(arr).replaceAll("[^0-9]",""));

        return strb.toString();
    }




}
