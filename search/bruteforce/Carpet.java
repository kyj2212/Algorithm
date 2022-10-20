package search.bruteforce;

import java.util.Arrays;

public class Carpet {
    public static void main(String[] args) {
        int brown = 8;
        int yellow = 1;

        int[] answer = {};

        int row=3, column=3;

//        brown = row*2 + (column-2)*2;
//        yellow = (row-2)*(column-2);
//
//        row = brown/2 -column +2;
//        row = yellow/(column-2) +2;
//

        L1 :
        for(int r = 3;r<=brown/2;r++){
            for(int c = r;c>=1;c--){
                if(brown == r*2 + (c-2)*2 && yellow == (r-2)*(c-2)){
                    row=r;
                    column=c;
                    break L1;
                }
            }
        }
        answer = new int[]{row,column};
        System.out.println(row + " " + column);
    }
}
