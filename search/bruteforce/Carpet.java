package search.bruteforce;

import java.util.Arrays;

public class Carpet {
    public static void main(String[] args) {
        int brown = 10;
        int yellow = 2;

        int[] answer = {};

        for(int c =3;c<=brown/4 +1;c++){

            if((brown*(c-2)) - (2*c*(c-2)) == 2*yellow){
                System.out.println( brown/2 -c +2+ " "+ c);
            }
        }

    }
}
