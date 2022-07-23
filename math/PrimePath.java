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

        for(int i =0;i<T;i++){
            st = new StringTokenizer(br.readLine()," ");
            int input = Integer.parseInt(st.nextToken());
            int output = Integer.parseInt(st.nextToken());
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

    static int sol04(int input, int output){


        Queue<Node> queue = new LinkedList<>();
        ArrayList<boolean[]> visitedlist = new ArrayList<>();

        // 초기값 input 을 넣고
        int cnt=0;
        Node node =new Node(input,cnt);
        queue.add(node);

        while(!queue.isEmpty()){

            // 하나 꺼내서 output과 같은지 확인
            node=queue.poll();
            if(visitedlist.size()<=cnt){
                boolean [] visited= new boolean[10000];
                visited[node.val]=true;
                visitedlist.add(visited);
            }else{
                visitedlist.get(cnt)[node.val]=true;
            }

            if(node.val==output) {
                return node.cnt;
            }

            // 한자리수가 다른 소수 구해서 queue 에 넣기
            for(int i : prime){
                // 소수 중에 input 값이랑 하나만 다르고, 그 노드가 이전에 방문한적 없으면
                if(diffOne(0,node.val,i,1000)&&!visitedlist.get(cnt)[i]){
                    queue.add(new Node(i,node.cnt+1));
                }
            }
        }
        return -1;
    }

    static boolean diffOne(int diff, int input, int target, int idx){
        boolean result=false;
        if((input/idx)!=(target/idx)){
            if(++diff>1)
                return false;
        }
        if(idx==1){
            if(diff==1) return true;
        } else result=diffOne(diff,input%idx,target%idx,idx/10);
        return result;
    }

/*    static boolean diffOne(int input, int target){
        int diff=0;

        if((input/1000)!=(target/1000)){
            if(++diff>1)
                return false;
        }
        input=input%1000;
        target=target%1000;
        if((input/100)!=(target/100)){
            if(++diff>1)
                return false;
        }
        input=input%100;
        target=target%100;
        if((input/10)!=(target/10)){
            if(++diff>1)
                return false;
        }
        input=input%10;
        target=target%10;
        if((input)!=(target)){
            if(++diff>1)
                return false;
        }
        if(diff==1)
            return true;
        else return false;
    }*/


/*    static boolean diffOne(int input, int target){
        int diff=0;
        for(int i=3;i>=0;i--){
            if(diff>1)
                return false;
            if((input/(int)Math.pow(10,i))!=(target/(int)Math.pow(10,i))){
                diff++;
            }
            input=input%(int)Math.pow(10,i);
            target=target%(int)Math.pow(10,i);
        }
        if(diff==1)
            return true;
        else return false;
    }*/



}
