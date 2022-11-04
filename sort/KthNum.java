package sort;

import java.util.Arrays;

public class KthNum {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
//        String str = "[2, 5, 3], [4, 4, 1], [1, 7, 3]";
//        str =str.replace('[','{');
//        str  =str.replace(']','}');
//        System.out.println(str);

        int[] answer = new int[commands.length];
        for(int l=0;l< commands.length;l++){

            int[] arr = Arrays.stream(Arrays.copyOfRange(array,commands[l][0]-1,commands[l][1])).sorted().toArray();
            answer[l]=arr[commands[l][2]-1];
        }

        Arrays.stream(answer).forEach(s -> System.out.printf(" "+s +" "));



    }
}
