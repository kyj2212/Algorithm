package math;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PrimePath {

    static ArrayList<Integer> prime;
    public static void main(String[] args) throws IOException {



        // 4자리 소수 모두 구하기
        // 소수이면 false, 배수이면 true
        boolean [] noPrime= new boolean[10000];
        noPrime[0]=noPrime[1]=true;
        for(int i=2;i<=Math.sqrt(9999);i++){
            if(noPrime[i])
                continue;
            for(int j=i*i;j<=9999;j+=i)
                noPrime[j]=true;
        }

        prime = new ArrayList<>();
        for(int i=1000;i<10000;i++){
            if(!noPrime[i])
                prime.add(i);
        }


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());



        int [] input = new int[4];
        int [] output = new int[4];
        for(int i =0;i<T;i++){
            String [] s = br.readLine().split(" ",2);
            input=Arrays.stream(s[0].split("",4)).mapToInt(Integer::parseInt).toArray();
            output=Arrays.stream(s[1].split("",4)).mapToInt(Integer::parseInt).toArray();
            int answer=sol04(input,output);
            sb.append(answer<0?"Impossible":answer).append("\n");
        }


        System.out.println(sb.toString());
        br.close();

    }

    // 가장 짧은 cnt 를 구하는거니까 BFS 인가봐 다 구해야해서.
    // 한자리수가 다른 모든 소수를 Queue 에 넣기, 갈수있는 다른 노드로 취급
    // 해당 노드가 output과 같으면 그만 다르면 다음 노드
    // queue에 fifo 로 poll 꺼내서 다시 한자리수가 다른 모든 소수를 Queue  에 넣어


    static class Node{
        int val;
        int cnt;

        Node(int val, int cnt){
            this.val=val;
            this.cnt=cnt;
        }
    }

    static int sol04(int[] input, int[] output){


        Queue<Node> queue = new LinkedList<>();
        boolean [][] visited = new boolean[10000][10000];

        // 초기값 input 을 넣고
        int cnt=0;
        Node node =new Node(arrToint(input),cnt);
        queue.add(node);

        while(!queue.isEmpty()){

            // 하나 꺼내서 output과 같은지 확인
            node=queue.poll();
            visited[cnt][node.val]=true;
            if(node.val==arrToint(output)) {
                return node.cnt;
            }

            input = intToarr(node.val);

            // 한자리수가 다른 소수 구해서 queue 에 넣기
            for(int i : prime){
                // 소수 중에 input 값이랑 하나만 다르고, 그 노드가 이전에 방문한적 없으면
                if(diffOne(input,intToarr(i))&&!visited[cnt][i]){
                    queue.add(new Node(i,node.cnt+1));
                }
            }

        }


        return -1;


    }



    static int[] intToarr(int num){
        int [] arr = new int[4];
        arr[0]=num/1000;
        num=num%1000;
        arr[1]=num/100;
        num=num%100;
        arr[2]=num/10;
        num=num%10;
        arr[3]=num;
        return arr;
    }

    static int arrToint(int[] arr){
        return 1000*arr[0]+100*arr[1]+10*arr[2]+arr[3];
    }
    static boolean diffOne(int[] input, int[] target){
        int diff=0;
        for(int i=0;i<4;i++){
            if(diff>1)
                return false;
            if(input[i]!=target[i]){
                diff++;
            }
        }
        if(diff==1)
            return true;
        else return false;
    }



    static int sol02(int[] input, int[] output){
        int cnt=0;
        // 소수 중에서 얘랑 한자리만 다른걸 찾는거야!
        // 그중에서 output 의 값이랑 같은걸 찾는거지
        // 없으면 작은값부터 먼저 바꿔

        int sio=hasSame(input,output);
        for(int i=0;i<prime.size();i++){
            int [] target = intToarr(prime.get(i));
            if(hasSame(input,target)==3){
                int sto=hasSame(target,output);
                if(sto==4)
                    break;
                else if(sio<sto){
                    input=target;
                    sio=hasSame(input,output);
                    cnt++;
                    continue;
                }
                else if(i==prime.size()-1){
                    input=target;
                    sio=hasSame(input,output);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static int hasSame(int[] a, int[] b){
        int same=0;
        for(int i=0;i<4;i++){
            if(a[i]==b[i]){
                same++;
            }
        }
        return same;
    }




    /*

    static int cntStep(String input, String output){
        cnt =0;

        for(int i=0;i<4;i++){

            String newInput=change(input,i,intAt(output,i));
            if(isPrime(newInput)) { // 소수이면
                input=newInput;
                cnt++;
                continue;
            }

            for (int j = i<1?1:0; j < 10; j++) {

                if(j==intAt(input,i))
                    continue;

                newInput=change(input,i,j);
                if(isPrime(newInput)) { // 소수이면
                    input=newInput;
                    cnt++;
                    break;
                }

            }
        }

        if(input.equals(output))
            return cnt;
        else return -1;
    }
    static boolean isPrime(String input){
        return !noPrime[Integer.parseInt(input)];
    }

    static String change(String input,int idx, int n){
        String newInput=changeOne(input,idx,n);

        return newInput;
    }

    static int[] getIntArr (String num){
        int[] arr= Arrays.stream(num.split("",4))
                .mapToInt(Integer::parseInt)
                .toArray();
        return arr;
    }

    static int intAt(String num,int idx){
        return getIntArr(num)[idx];
    }

    static String changeOne(String input, int idx, int n){
        int[] arr= getIntArr(input);
        arr[idx]=n;
        StringBuilder strb = new StringBuilder();

        strb.append(Arrays.toString(arr).replaceAll("[^0-9]",""));

        return strb.toString();
    }

*/


}
