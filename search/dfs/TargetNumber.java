package search.dfs;

import java.util.ArrayList;
import java.util.List;

public class TargetNumber {
    static int[] numbers = {1, 1, 1, 1, 1};
    static int target = 3;
    static int cnt=0;

    public static void main(String[] args) {


        int answer =0;


        // bfs 너비우선 탐색
        // 최소경우가 아니라, 끝까지 가야하니까 dfs로 해보자

        // dfs 깊이 우선 탐색
        // 재귀함수로 호출
        dfs(0,0);
        answer=cnt;
        System.out.println(answer);
    }
    static int dfs(int i, int sum){
        int plus = sum+numbers[i];
        int minus = sum-numbers[i];
        if( i== numbers.length-1){
            if(plus==target||minus==target){
                return ++cnt;
            }else{
                return cnt;
            }
        }else {
            return dfs(i+1,plus) + dfs(i+1,minus);
        }
    }
}
