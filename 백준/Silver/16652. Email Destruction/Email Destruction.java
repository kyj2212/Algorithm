import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Map<String,Integer> hashMap = new HashMap<>();
    static String re= "Re: "; // re 는 static string pool에 저잗이 되는데 main method 밖에서도 static으로 사용이 가능한가? 궁금쓰

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int cnt=0;

        for(int i =0;i<k;i++){
            StringBuffer str = new StringBuffer(br.readLine());
            findRe(str);
        }


        Collection<Integer> values =  hashMap.values();
        for(int i : values){
            cnt+=i+1;
        }

        if(n>=cnt)
            System.out.println("YES");
        else System.out.println("NO");



        // re 의 개수가 n개일때
        // 메일이 적어도 n+1


        // re를 제외한 실제 문자열 = key 로 둬
        // key 로 문자열의 kind를 구분
        // key 가 같다면, re 의  개수가 더 많은 케이스만 판단, 적으면 넘어가
        // key 가 다르다면, cnt+1



    }

    static void findRe(StringBuffer str) {
        int cnt=0;
        // Re : 를 찾아서 Re 가 없을 때까지 cnt 더하고 나머지를 뒤집어 씌어




        for(cnt=0;cnt<str.length();cnt++){
            if(str.length()<cnt*4+4)
                break;
            if(str.substring(cnt*4,cnt*4+4).equals(re))
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
