package greedy.boj;

/*
*
* 유명한 제빵사 김원웅은 빵집을 운영하고 있다. 원웅이의 빵집은 글로벌 재정 위기를 피해가지 못했고, 결국 심각한 재정 위기에 빠졌다.

원웅이는 지출을 줄이고자 여기저기 지출을 살펴보던 중에, 가스비가 제일 크다는 것을 알게되었다.
* 따라서 원웅이는 근처 빵집의 가스관에 몰래 파이프를 설치해 훔쳐서 사용하기로 했다.

빵집이 있는 곳은 R*C 격자로 표현할 수 있다. 첫째 열은 근처 빵집의 가스관이고, 마지막 열은 원웅이의 빵집이다.

원웅이는 가스관과 빵집을 연결하는 파이프를 설치하려고 한다. 빵집과 가스관 사이에는 건물이 있을 수도 있다. 건물이 있는 경우에는 파이프를 놓을 수 없다.

가스관과 빵집을 연결하는 모든 파이프라인은 첫째 열에서 시작해야 하고, 마지막 열에서 끝나야 한다.
* 각 칸은 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결할 수 있고, 각 칸의 중심끼리 연결하는 것이다.

원웅이는 가스를 되도록 많이 훔치려고 한다. 따라서, 가스관과 빵집을 연결하는 파이프라인을 여러 개 설치할 것이다.
* 이 경로는 겹칠 수 없고, 서로 접할 수도 없다. 즉, 각 칸을 지나는 파이프는 하나이어야 한다.

원웅이 빵집의 모습이 주어졌을 때, 원웅이가 설치할 수 있는 가스관과 빵집을 연결하는 파이프라인의 최대 개수를 구하는 프로그램을 작성하시오
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BreadHouse {

    static char[][] map;
    static int R;
    static int C;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String [] s = br.readLine().split("\\s");

        int R = Integer.parseInt(s[0]);
        int C = Integer.parseInt(s[1]);

        Point [] path = new Point [R];
        ArrayList<Point> building = new ArrayList<>(R*C);

        for (int i = 0; i < R; i++) {
            char [] line = br.readLine().toCharArray();
            for(int j =0;j<C;j++){
                if(line[j]=='x'){
                    building.add(new Point(i,j));
                }
                else
                    path[i]=(new Point(i,j));
            }
        }

        br.close();
/* path[i] 안됨. 06.20 수정필요
        for (int i = 0; i < R; i++) {
            for(Point p : path[i])
                System.out.print(p);
        }
        System.out.println(building);

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(!building.contains(path[i][j].nextUp())){

                }else if(!building.contains(path[i][j].next())){

                }else if(!building.contains(path[i][j].nextDown())){

                }else {

                }
            }
        }
*/



/*        map = new char[R][C];
        for(int i=0; i<R; i++)
            map[i] = br.readLine().toCharArray();*/






        System.out.println("");


    }



    public static boolean check(int x, int y) {
        map[x][y] = '-';

        if(y == C-1)
            return true;

        if(x > 0 && map[x-1][y+1] == '.')
            if(check(x-1, y+1))
                return true;
        if(map[x][y+1] == '.')
            if(check(x, y+1))
                return true;
        if(x+1 < R && map[x+1][y+1] == '.')
            if(check(x+1, y+1))
                return true;

        return false;
    }

}

class Point{
    int R;
    int C;

    Point(int R, int C){
        this.R = R;
        this.C=C;
    }
    Point(){
        this(0,0);
    }

    Point nextUp(){
        this.R=R-1;
        this.C=C+1;
        return this;
    }

    Point next(){
        this.C=C+1;
        return this;
    }
    Point nextDown(){
        this.R=R+1;
        this.C=C+1;
        return this;

    }
    Point nextLine(){
        this.R=R+1;
        this.C=0;
        return this;

    }
    public String toString(){
        return "("+this.R+","+this.C+")";
    }

}