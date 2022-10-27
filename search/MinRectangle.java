package search;

public class MinRectangle {
    static int len;
    public static void main(String[] args) {
//        int[][] size = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
//        int[][] size = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};
        int[][] size = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}};
        String str = "[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]";
        str =str.replace('[','{');
        str =str.replace(']','}');
//        System.out.println(str);

        int answer = 0;

        answer = findMax(0,size,new int[2]);
        System.out.println(answer);

    }

    private static int findMax(int i , int[][] size, int[] max){
        if(i==size.length){
            return max[0]*max[1];
        }
        int[] tmp = max.clone();
        int[] card = size[i];
        if(card[0]>max[0]){
            max[0]=card[0];
        }
        if(card[1]>max[1]){
            max[1]=card[1];
        }
        int area = findMax(i+1,size,max);
        max[0]=tmp[0];
        max[1]=tmp[1];

        if(card[1]>max[0]){
            max[0]=card[1];
        }
        if(card[0]>max[1]){
            max[1]=card[0];
        }
        area = Math.min(area,findMax(i+1,size,max));
        return area;
    }
}
