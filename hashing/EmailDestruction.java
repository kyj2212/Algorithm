package hashing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class EmailDestruction {

    static Map<String,Integer> hashMap = new HashMap<>();
    static String re= "Re: ";
    // re가 가리키는 값은 static string pool에 저장이 되지만 re 라는 변수는 stack에 저장된다. 따라서 static으로 re를 지정해야 한다. "Re: " 로 하는것이 더 효율적인것인가?
    static int answer=0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        for(int i =0;i<k;i++){
            StringBuffer str = new StringBuffer(br.readLine());
            findRe(str);
        }

        // 람다식에는 final만 들어갈 수 있다? 왜?
        // hashmap에서 모든 value값들을 가져오는 방법

        hashMap.forEach((key, value) -> {
            answer+=value+1;
        });

        // re 의 개수가 n개일때
        // 메일이 적어도 n+1
        Collection<Integer> values =  hashMap.values();
        for(int i : values){
            answer+=i+1;
        }

        if(n>=answer)
            System.out.println("YES");
        else System.out.println("NO");

        br.close();

    }


    // re를 제외한 실제 문자열 = key 로 둬
    // key 가 같다면, re 의  개수가 더 많은 케이스만 판단, 적으면 넘어가
    // key 가 다르다면, cnt+1
    static void findRe(StringBuffer str) {
        int cnt;

        // Re : 를 찾아서 Re 가 없을 때까지 cnt 더하고 나머지를 뒤집어 씌어
        for(cnt=0;cnt<str.length();cnt++){
            if(str.length()<cnt*4+4)
                break;
            if(str.substring(cnt*4,cnt*4+4).equals("Re: "))
                continue;
            else break;
        }

        str.delete(0,4*cnt);
        // hashmap 에 string과 카운트를 넣어
        // 기존의 map 에서 있는 key 인지 확인
        String key=str.toString();
        Integer org=hashMap.get(key); // 기존의 Re: 개수

        if(org==null) // key가 없으면 hashmap은 null을 반환하니까, 없으면 새로 추가
            hashMap.put(str.toString(), cnt);
        else  if(org<cnt){ // 새로운 Re: 개수가 크면 바꾸고
            hashMap.replace(key,cnt);
        }
    }


}
