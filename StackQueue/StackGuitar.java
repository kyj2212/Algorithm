package StackQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

public class StackGuitar {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken()); // 음의 수
        int P = Integer.parseInt(st.nextToken()); // 프렛 수
        int cnt = 0;

        // stack 테스트 해본거
/*
        Stack<int[]> stack = new Stack();
        int [] a = {1,6};
        stack.push(a);
        System.out.println(a);
        System.out.println(stack.peek()[0]);
*/

        // 총 1~6 string, 각각 string 마다 Stack (동일한 string 에서만 high 한 fret 이 연주됨)
        Stack<Integer> [] s = new Stack[6]; // 여기 공부해보기, 왜 stack를 7 로 선언? 배열과 달리 stack은 맨끝에 주소값이 들어가나?
        for (int i =0;i<6;i++) {
            s[i]=new Stack<Integer>();
        }


        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int string = Integer.parseInt(st.nextToken());
            int fret = Integer.parseInt(st.nextToken());
            cnt +=fingerCount(s[string-1],string, fret);
        }

        br.close();

        System.out.println(cnt);

    }

    static int fingerCount(Stack<Integer> s, int string,int fret) {


        int cnt=0;

        while (!s.isEmpty()) {
            if(fret > s.peek()) {
                s.push(fret); cnt++; break;
            }
            else if (fret < s.peek()) {
                s.pop(); cnt++;
            }
            else break;
        }
        if (s.isEmpty()) {
            s.push(fret);
            cnt++;
        }

        return cnt;
    }



}

