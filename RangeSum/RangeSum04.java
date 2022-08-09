package RangeSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RangeSum04 {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

       // int [] prefixSum= new int[N];
        int prevSum=0;
        int prefixSum;
        Map<Integer, List<Integer>> map = new HashMap<>();
        // prefixSum 의 중복값을 제거.. 순서를 어떻게 하지? 이건 정확한 값을 비교하는 거라 순서가 필요하다..
        // index 값을 map 에 넣자?
        st= new StringTokenizer(br.readLine(), " ");

        for(int i =0;i<N;i++){

            int num= Integer.parseInt(st.nextToken());
            prefixSum=prevSum+num;

            if(!map.containsKey(prefixSum)) {
                map.put(prefixSum,new ArrayList<>());
            }
            List<Integer> idx = map.get(prefixSum);
            idx.add(i);
            prevSum = prefixSum;

        }

        int answer=0;
        for(int sum : map.keySet()){
            // 자기 자신을 뽑는경우 - 1-x 까지의 합인 경우
            if(sum==K)
                answer+=map.get(sum).size();
            // 값이 두번있는 경우, 같은 값을 빼는 경우
            // K==0 인 경우
            if(K==0){
                int cnt = map.get(sum).size();
                if(cnt>1){ // 카운트가 2~ 부터는 중복된 값들이니까
                    answer+=(cnt*(cnt-1)/2); // nC2 를 더해
                }
            }else {

                for(int sum2 : map.keySet()){
                    if(sum==sum2)
                        break;
                    else {
                        if((sum-sum2)==K){
                            for(int i1 : map.get(sum)){
                                for(int i2 : map.get(sum2)){
                                    if(i1-i2<0)
                                        continue;
                                    else answer++;
                                }
                            }
                        }
                        if((sum2-sum)==K){
                            for(int i1 : map.get(sum)){
                                for(int i2 : map.get(sum2)){
                                    if(i1-i2<0)
                                        continue;
                                    else answer++;
                                }
                            }
                        }

                    }
                }


            }

            // 서로 다른 값을 뽑는 경우, 이미 순서는 있는데,... 값을 어떻게 구하지?



        }


        System.out.println(answer);

    }




}
