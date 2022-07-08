package showme.s03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*

Drop7은 7×7 크기의 격자에서 진행하는 싱글 플레이어 게임이다. 처음에는 격자가 비어있고, 플레이어는 매 턴마다 1 이상 7 이하의 정수 하나가 적힌 공을 받아 7개의 열 중 한 곳에 떨어뜨려야 한다. 떨어뜨린 공은 이미 배치되어 있는 공 바로 위에 도달하거나 바닥 바로 위에 도달할 때까지 아래로 이동한다.

가로 방향으로 연속해서 놓여있는 공들을 "가로 방향 그룹", 세로 방향으로 연속해서 놓여있는 공들을 "세로 방향 그룹"이라고 하자. 공을 떨어뜨릴 때마다 공들의 그룹이 바뀔 수 있는데, 만약 크기가 $x$인 그룹에 $x$가 적힌 공이 있다면 그 공은 없어진다. 조건을 만족하는 공은 모두 동시에 없어진다. 공이 없어지면 위에 있던 공들이 아래로 내려가며, 공이 없어지는 이벤트는 연쇄적으로 발생한다. 단, 크기가 n인 그룹은 크기가 n-1인 그룹을 포함하지 않는다. 즉 그룹의 가장 끝에 위치한 공은 격자의 테두리 또는 빈 공간과 인접한다.

현재 격자의 상태와 이번에 떨어뜨려야 하는 공의 번호가 주어졌을 때, 공을 한 번 떨어뜨려서 최대한 많이 공을 없애보자.


입력
첫째 줄부터 7개의 줄에 현재 격자의 상태가 주어진다. 공이 있는 칸은 공의 번호가 1부터 7까지의 숫자로 주어지며, 공이 없는 칸은 0으로 주어진다.

다음 줄에 떨어뜨릴 공의 번호가 주어진다.

게임 도중에 나타날 수 있는 상태만 입력으로 주어진다. 즉 격자의 첫 번째 줄은 모두 0이고, 공을 떨어뜨리기 전에 공이 없어지는 이벤트가 발생하지 않는 입력만 주어진다.


출력

공을 떨어뜨렸을 때 격자에 남아있을 수 있는 공의 개수의 최솟값을 출력한다.

0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 0 4 0 2 0
0 0 0 5 0 6 0
3 3 0 7 5 6 7
3

0



 */

class Main {

    static int T;
    static int W;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int[] t = new int[T];
        for (int i = 0; i < T; i++) {
            t[i]=Integer.parseInt(br.readLine());
        }
        br.close();



    }




}


public class showme03 {

}