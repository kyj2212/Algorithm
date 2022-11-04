package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MathGiveUp {
    public static void main(String[] args) {
//        int[] answers = {1,2,3,4,5};
        int[] answers = {1,3,2,4,2};

        int[] answer = {};
        int[][] cases = {{1,2,3,4,5},{2,1,2,3,2,4,2,5},{3,3,1,1,2,2,4,4,5,5}};
        int[] right = new int [3];
        for(int i =0;i<answers.length;i++){
            if(cases[0][i%5]==answers[i]){
                right[0]++;
            }
            if(cases[1][i%8]==answers[i]){
                right[1]++;
            }
            if(cases[2][i%10]==answers[i]){
                right[2]++;
            }
        }
        int max = Arrays.stream(right).max().getAsInt();

        List<Integer> maxs = new ArrayList<>();
        for(int i =0;i<3;i++){
            if(right[i]==max){
                maxs.add(i+1);
            }
        }
        answer = maxs.stream().mapToInt(i->i).toArray();
        System.out.println(maxs);
    }
}
