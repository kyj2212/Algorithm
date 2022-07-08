package greedy.programmers;


// 프로그래머스, 체육복 문제

/*
* 점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다.
* 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다.
* 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다.
* 예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다.
* 체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.
*
*
*
*
* */


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class SweatSuit {

    public static void main(String[] args) throws IOException {



        /* 입력 */
/*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine()); // 전체 학생의 수 2<=n<=30
        int[] lost = new int[n]; // 도난당한 학생들의 번호가 담긴 배열 lost.lenght >=1, <=n , 중복 x
        int[] reserve = new int[n]; // 여벌의 체육복을 가졍는 학생들의 번호가 담긴 배열 reserver.length >=1 , <=n , 중복 x
*/

        int n = 20;
        int [] lost = {12,5,4,2,1};
        int [] reserve = {3,6,19,8,2,9,11};


        Solution s = new Solution();
        System.out.println(s.solution(n,lost,reserve));
    }


    public static class Solution {
        public int solution(int n, int[] lost, int[] reserve) {

            int answer = 0;
            int r = 0;
            ArrayList lostlist = new ArrayList();
            LinkedList rq = new LinkedList();


            // 중복제거
            for(int i : reserve)
                rq.add(i);

            for(int i=0;i<lost.length;i++){
                if(rq.contains(lost[i]))
                    rq.remove((Object) lost[i]);
                else
                    lostlist.add(lost[i]);
            }

            // 순차정렬
            Collections.sort(rq);
            Collections.sort(lostlist);

            System.out.println("중복제거, 순차정렬");
            System.out.println("lostlist : "+lostlist);
            System.out.println("rq : "+rq);

            if(lost.length==0){
                return n;
            }
            else {
                for(Object i : lostlist){
                    if(rq.isEmpty())
                        break;

                    for(Object j : rq) {

                        if((Integer)i==(Integer)j+1 | (Integer)i==(Integer)j-1) {
                            r++;
                            rq.remove(j);
                            break;
                        }
                        else {
                            //                      System.out.println(i+"번 도난 / " + j+"번 빌리기 X");
                        }
                    }
                }
                System.out.println("====== result ======");
                System.out.println("lostlist : "+lostlist);
                System.out.println("rq : "+rq);
            }

            System.out.println("N = "+n+"-"+lostlist.size()+"+"+r);
            answer = n - lostlist.size() + r;
            return answer;
        }
    }

}

