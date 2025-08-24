package test.kakao;


public class TwoUnderBits {
    public static void main(String[] args) {
        TwoUnderBits f = new TwoUnderBits();

        long[] numbers = {2,7};
        f.solution(numbers);
    }

    public void solution(long[] numbers){

        long[] answer = {};
        int len = numbers.length; // 개수
        answer = new long[len];
        for(int i =0; i<len ;i++){
            long n = numbers[i];
            System.out.println("n :: getbit 시작 " + n );
            //boolean[] bit = getBits(n);
            String bit = Long.toBinaryString(n);
            System.out.println("bit :: " + bit);
            answer[i] = fx(bit, n);
            System.out.println("n :: " + n + " answer :: " + answer[i]);
        }

    }

    public long fx(String bit, long n){
        int cnt =3;
        while(cnt>2){
            //boolean[] tmp = getBits(++n);
            System.out.println("왜 여기를 안들어오지 ?? ");
            String tmp = Long.toBinaryString(++n);
            System.out.println("tmp :: " + tmp);
            cnt = comp(bit, tmp);
            System.out.println("n 과 비교 :: " + cnt);
            if(cnt <=2){
                System.out.println("cnt <=2야 ??  "+cnt);
                return n;
            }


            System.out.println("cnt > 2 이면 while 돌아야하는데?" + cnt);
        }
        return n;
    }

    public int comp(String s1 , String s2){
        int cnt = 0;
        int l1 = s1.length();
        int l2 = s2.length();
        if(l2>l1){
            for(int i =0;i<l2-l1;i++){
                s1= "0"+s1;
            }
        }
        System.out.println("s1 :: " + s1);
        System.out.println("s2 :: " + s2);
        for(int i =0; i<l2;i++){
            if(s1.charAt(i) != s2.charAt(i)){
                cnt++;
            }
        }
        return cnt;
    }
    public int comp2(boolean[] b1 , boolean[] b2){
        int cnt = 0;
        for(int i =0; i<64;i++){
            if(b1[i] != b2[i]){
                cnt++;
            }
        }
        return cnt;
    }
    public boolean[] getBits(long n){
        System.out.println("getbit n :: " + n);
        boolean[] b = new boolean[64];
        for(int i =0;i<n/2;i++){
            System.out.println("n%2 :: " + (n%2));
            b[i] = (n%2 != 0);
            System.out.println("n/2 :: " + (n/2));
            n=n/2;
        }
        print(b);
        return b;
    }

    public void print(boolean[] arr){
        StringBuffer buf = new StringBuffer();
        for(int i=0;i<arr.length;i++){
            buf.append(arr[i] ? 1 : 0);
        }
        System.out.println(buf.toString() );
    }
}
