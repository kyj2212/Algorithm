# 분할 정복이란?

분할 : 작은 부분 문제로 문제를 분할한다.

정복 : 각각의 부분 문제를 재귀적으로 호출하여 해결한다.

결합 : 부분 문제들을 결합하여 최종 결과를 도출한다.

분할 정복 알고리즘을 사용하는 알고리즘 예시

1. 퀵정렬 (boj 11004)
    
    기준값(pivot)을 선정해 해당 값보다 작은 데이터와 큰 데이터로 분류하는 것을 반복해 정렬하는 알고리즘
    
    핵심이론 
    
    ```java
    void pivot() {
    	while(start==end){
    		if(start < pivot) { start++ }
    		if(end > pivot) { end— }
    		if(start > pivot && end < pivot} { tmp=start; start=end; end=tmp; }
    	}
    	return pivot()
    }
    ```
    
2. 병합 정렬 ( boj 2751)
    
    분할 정복 방식을 사용해 데이터를 분할 하고 분할한 집합을 정렬하며 합치는 알고리즘
    
    핵심 이론
    
    가장 작은 데이터 집합으로 분할 → 병합하면서 정렬 → … 반복 .. → 최종 집합 1개 
    
    ```java
    // 두개의 집합을 병합하면서 정렬하는 방법
    // 투포인터 사용
    
    int idx1, idx2;
    List<> sub1, sub2;
    List<> merge;
    
    while( idx1==end and idx2==end){
    if( sub1(idx1) < sub2(idx2) || idx2==end ){
    	merge.add(sub1(idx1));
      idx1++;
    }else{
    	merge.add(sub2(idx2));
    	idx2++;
    }
    }
    ```
    
3. 이분 탐색 (boj 1920)
    
    데이터가 정렬돼 있는 상태에서 원하는 값을 찾아내는 알고리즘
    
    대상 데이터의 중앙갑과 찾고자 하는 값을 비교해 데이터의 크기를 절반씩 줄이면서 대상을 찾음
    
    핵심이론
    
    오름차순(또는 내림차순이면 반대)으로 데이터 정렬 →
    
    중앙값 선택 → if(중앙값> target ) 중앙값 왼쪽 데이터 선택 → if(중앙값<target) 중앙값 오른쪽 데이터 선택 → whlie(중앙값==target) 종료
    
- 참고 : Geeks for Geeks
    1. **Divide:** This involves dividing the problem into smaller sub-problems.
    2. **Conquer:** Solve sub-problems by calling recursively until solved.
    3. **Combine:** Combine the sub-problems to get the final solution of the whole problem.
    
    The following are some standard algorithms that follow Divide and Conquer algorithm.
    
    1. **[Quicksort](https://www.geeksforgeeks.org/quick-sort/)** is a sorting algorithm. The algorithm picks a pivot element and rearranges the array elements so that all elements smaller than the picked pivot element move to the left side of the pivot, and all greater elements move to the right side. Finally, the algorithm recursively sorts the subarrays on the left and right of the pivot element.
    2. **[Merge Sort](https://www.geeksforgeeks.org/merge-sort/)** is also a sorting algorithm. The algorithm divides the array into two halves, recursively sorts them, and finally merges the two sorted halves.
    3. **[Closest Pair of Points](https://www.geeksforgeeks.org/closest-pair-of-points-using-divide-and-conquer-algorithm/)** The problem is to find the closest pair of points in a set of points in the x-y plane. The problem can be solved in O(n^2) time by calculating the distances of every pair of points and comparing the distances to find the minimum. The Divide and Conquer algorithm solves the problem in O(N log N) time.
    4. **[Strassen’s Algorithm](https://www.geeksforgeeks.org/strassens-matrix-multiplication/)** is an efficient algorithm to multiply two matrices. A simple method to multiply two matrices needs 3 nested loops and is O(n^3). Strassen’s algorithm multiplies two matrices in O(n^2.8974) time.
    5. **[Cooley–Tukey Fast Fourier Transform (FFT) algorithm](http://en.wikipedia.org/wiki/Cooley%E2%80%93Tukey_FFT_algorithm)** is the most common algorithm for FFT. It is a divide and conquer algorithm which works in O(N log N) time.
    6. **[Karatsuba algorithm for fast multiplication](https://www.geeksforgeeks.org/karatsuba-algorithm-for-fast-multiplication-using-divide-and-conquer-algorithm/)** does the multiplication of two *n*digit numbers in at mos

[https://www.geeksforgeeks.org/divide-and-conquer-algorithm-introduction/](https://www.geeksforgeeks.org/divide-and-conquer-algorithm-introduction/)

---

# 문제 : 백준 11582

[11582번: 치킨 TOP N](https://www.acmicpc.net/problem/11582)

- 병합 정렬 문제
- 2개 → 4개 → 8개 → 16개 → N개

ex) (1, 5, 2, 4, 2, 9, 7, 3)

- 1차 코드
    
    ```java
    package sort.mergesort;
    
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    
    public class ChickenTopN {
    
        static int N;
        public static void main(String[] args) throws IOException {
    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            N = Integer.parseInt(st.nextToken());
            int [] input = new int[N];
            st = new StringTokenizer(br.readLine()," ");
            for(int i =0;i<N;i++){
                input[i]=Integer.parseInt(st.nextToken());
            }
            int k = Integer.parseInt(br.readLine());
    
            int[][] result = mergeSort(2,k,divide(1,input));
            for(int[] s : result){
                for(int i : s){
                    System.out.printf(i+" ");
                }
            }
    
        }
        public static int[][] divide(int start, int[] input){
            // 분할
            int[][] sub = new int[N/start][start];
            for(int i =0;i<N;i++){
                sub[i/start][i%start]=input[i];
            }
            return sub;
        }
        public static int[][] mergeSort(int start, int k, int[][] input){
    
            int[][] sub = new int[N/start][start];
    
            // 병합
            for(int i=0;i<sub.length;i++){
                int start1=0;
                int start2=0;
                int[] data1 = input[i*2];
                int[] data2 = input[i*2+1];
                // i 라인의 두개 그룹 비교 무조건 두개니까 그냥 비교
                for(int j=0;j<sub[i].length;j++){
                    if(start1==data1.length){
                        sub[i][j]=data2[start2];
                        start2++;
                    }
                    else if(start2==data2.length){
                        sub[i][j]=data1[start1];
                        start1++;
                    }
                    else if(data1[start1]<data2[start2]){
                        sub[i][j]=data1[start1];
                        start1++;
                        continue;
                    }
                    else{
                        sub[i][j]=data2[start2];
                        start2++;
                        continue;
                    }
                }
            }
            if(k==(N/start)){
                return sub;
            }
            return mergeSort(start*2, k,sub);
        }
    }
    ```
    

336136 kb 7192ms

굉장히 시간이 많이 소요되었음.

- 2차 코드
    
    ```java
    package sort.mergesort;
    
    import java.io.*;
    import java.util.StringTokenizer;
    
    public class ChickenTopN {
    
        static int N;
        public static void main(String[] args) throws IOException {
    
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            N = Integer.parseInt(st.nextToken());
            int [] input = new int[N];
            st = new StringTokenizer(br.readLine()," ");
            for(int i =0;i<N;i++){
                input[i]=Integer.parseInt(st.nextToken());
            }
            int k = Integer.parseInt(br.readLine());
    
            int[][] result = mergeSort(2,k,divide(1,input));
            for(int[] s : result){
                for(int i : s){
                    bw.write(String.valueOf(i));
                    bw.write(" ");
                }
            }
            bw.flush();
            bw.close();
            br.close();
        }
        public static int[][] divide(int start, int[] input){
            // 분할
            int[][] sub = new int[N/start][start];
            for(int i =0;i<N;i++){
                sub[i/start][i%start]=input[i];
            }
            return sub;
        }
        public static int[][] mergeSort(int start, int k, int[][] input){
    
            int[][] sub = new int[N/start][start];
    
            // 병합
            for(int i=0;i<sub.length;i++){
                int start1=0;
                int start2=0;
                int[] data1 = input[i*2];
                int[] data2 = input[i*2+1];
                // i 라인의 두개 그룹 비교 무조건 두개니까 그냥 비교
                for(int j=0;j<sub[i].length;j++){
                    if(start1==data1.length){
                        sub[i][j]=data2[start2];
                        start2++;
                    } else if(start2==data2.length){
                        sub[i][j]=data1[start1];
                        start1++;
                    } else if(data1[start1]<data2[start2]){
                        sub[i][j]=data1[start1];
                        start1++;
                        continue;
                    }
                    else{
                        sub[i][j]=data2[start2];
                        start2++;
                        continue;
                    }
                }
            }
            if(k==(N/start)){
                return sub;
            }
            return mergeSort(start*2, k,sub);
        }
    }
    ```
    

254688kb 1140ms

`println → BufferedWriter` 로 만 바꾸었는데 시간이 약 7배 감소함