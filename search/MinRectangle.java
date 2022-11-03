package search;

public class MinRectangle {
    static int len;
    public static void main(String[] args) {
        int[][] size = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
//        int[][] size = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};
//        int[][] size = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}};
//        String str = "[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]";
//        str =str.replace('[','{');
//        str =str.replace(']','}');
//        System.out.println(str);

        int answer = 0;


        answer=findMax(size);

        System.out.println(answer);

    }
    private static int findMax(int[][] sizes){
        // 초기값
        int max_w=0;
        int max_h=0;

        for(int i =0;i<sizes.length;i++){

            int[] node = sizes[i];
            // 작은 값을 가로
            if(node[0]<node[1]){
                max_w=Math.max(max_w,node[0]);
                max_h=Math.max(max_h,node[1]);
            }else{
                max_w=Math.max(max_w,node[1]);
                max_h=Math.max(max_h,node[0]);
            }
        }
        return max_h*max_w;
    }

}
