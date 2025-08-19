package test.kakao;

public class Friends4Blocks {
    public  char[][] charB;
    public  boolean[][] delB;

    public static void main(String[] args) {
        Friends4Blocks f = new Friends4Blocks();
        int m = 5;
        int n = 6;
        String[] board = {"AAAAAA", "BBAATB", "BBAATB", "JJJTJJ", "JJJTJJ"};
        f.solution(m,n,board);
    }

    public int solution(int m, int n, String[] board) {
        charB = new char[m][n]; // 초기화
        delB = new boolean[m][n]; // 초기화
        int answer = 0;

        // 초기화
        for(int i =0;i<m;i++){
            char[] chars = board[i].toCharArray();  // 한글자씩 나눠
            for(int j =0;j<n;j++){
                charB[i][j] = chars[j];
                delB[i][j] = false;
            }
        }
        print(charB);
        print(delB);

        int fCnt = 1;
        int dCnt = 0;
        while ( fCnt > 0){
            // 삭제할 블록 찾기
            fCnt = find();
            System.out.println("fint cnt :: " + fCnt);
            //print(delB);

            // 블록 삭제
            dCnt +=  del();
            System.out.println("del cnt :: " + dCnt);
           // print(charB);
        }
        answer = dCnt;
        return answer;
    }
    public void print(boolean[][] arr){
        System.out.println("print 배열");
        for(int i=0;i<arr.length;i++){
            StringBuffer buf = new StringBuffer();
            for(int j=0;j<arr[i].length;j++){
                buf.append(arr[i][j]).append(" ");
            }
            System.out.println(buf.toString() );
        }
    }
    public void print(char[][] arr){
        System.out.println("print 배열");
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
    public int del(){
        int cnt = 0;
        for(int i =0;i<charB.length;i++){
            for(int j=0;j<charB[0].length;j++){
                if(delB[i][j] && charB[i][j] != '0'){
                    charB[i][j] = '0';
                    cnt++;
                }
            }
        }
        System.out.println("삭제하고 나서");
        print(charB);
        for(int i =0;i< charB.length;i++){
            for(int j=0;j<charB[0].length;j++){
                if(charB[i][j] == '0'){
                    for(int k=0;i-k-1>=0;k++){
                        char t1 = charB[i-k][j];
                        boolean t2 = delB[i-k][j];
                        charB[i-k][j]=charB[i-k-1][j];
                        delB[i-k][j] = delB[i-k-1][j];
                        charB[i-k-1][j] = t1;
                        delB[i-k-1][j] = t2;
                    }
                }
            }
        }
        System.out.println("내리고 나서");
        print(charB);
        return cnt;
    }
    public int find(){
        int cnt= 0;
        int m = charB.length;
        int n = charB[0].length;

         for(int i =0; i<m-1;i++ ){
            for(int j =0; j< n-1; j++){
                char c = charB[i][j];
                if( c!='0' && c == charB[i+1][j] && c == charB[i][j+1] && c == charB[i+1][j+1]){
                    delB[i][j] = true;
                    delB[i+1][j] = true;
                    delB[i][j+1] = true;
                    delB[i+1][j+1] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

}