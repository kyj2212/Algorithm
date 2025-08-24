package test.kakao;


public class TwoUnderBits {
    public static void main(String[] args) {
        TwoUnderBits f = new TwoUnderBits();

        long[] numbers = {2, 7, 11, 123456789L};
        f.solution(numbers);
    }

    public void solution(long[] numbers){

        long[] answer = {};
        int len = numbers.length; // 개수
        answer = new long[len];
        for(int i =0; i<len ;i++){
            long n = numbers[i];
            System.out.println("n :: getbit 시작 " + n );

            long r =gpt_fx(n);
            answer[i] = r;
            System.out.println("n :: " + n + " answer :: " + answer[i]);
        }

    }

    public long gpt_fx(long n){
        long r;
        if( (n&1) == 0) { // 짝수
          System.out.println("짝수 n" + n);
          r=n+1L;
        }else{ // 홀수인 경우
            long bit = 1L;
            int idx = 0;

            while((n&bit) != 0){ // 0010 이랑 & 해서 n 이 0 이여야 0 이니 0 나올때까지
                bit<<=1; // 한개올리고
                idx++;
                // 0111 7
                // 0010 >> bit
                // 0111
            }
            // bit 의 위치에 0 이 있는것임
            // 가장 작으려면 그 0 을 1로 바꾸로 바로 오른쪽을 바꿔야해 ( 나머지는 다 1이니까 가장 큰 1을 0으로 바꿔야지)
            System.out.println("idx :: " + idx);
            r = n^(bit); // 0있는 위치에 1로 바꾸고
            System.out.println("0 을 1로 바꾸고 " + r);
            r= r^(1L<<(idx-1)); // 바로 오른쪽 1을 0으로 바꾸고
            System.out.println("오른쪽 1 을 0으로 바꾸고" + r);
            return r;

        }
        return r;
    }

}
