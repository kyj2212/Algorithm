### 스택 의 핵심 이론

삽입과 삭제 연산이 LIFO (Last-In First-Out) 으로 이루어지는 자료구조

삽입과 삭제가 한쪽(top)에서만 일어난다.

- DFS(깊이 우선 탐색), 백트래킹 에 효과적
- 재귀 함수 알고리즘의 원리와 일맥상통

JAVA 에서 제공하는 `Stack` 클래스

- 수식 계산, 수식괄호검사 ,워드프로세서의 undo/redo, 웹브라우저의 뒤로/앞으로

```java
Stack<> stack = new Stack<>();

// java.util.Stack
/**
The Stack class represents a last-in-first-out (LIFO) stack of objects. 
It extends class Vector with five operations that allow a vector to be treated as a stack.
The usual push and pop operations are provided, 
as well as a method to peek at the top item on the stack, 
a method to test for whether the stack is empty, 
and a method to search the stack for an item and discover how far it is from the top.
When a stack is first created, it contains no items.
**/
```


### 2차 시도 - 34908kb 2600ms

`stack.contains(input)` 을 하여 input을 가지고 있지만 peek가 아닌 경우를 invalid로 빼주었는데,

이부분에서 시간이 오래 소요되는 것 같다. (매번 stack의 모든 요소를 검사하니)

- 소스코드

    ```java
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Stack;
    
    public class Main {
    
        static Stack<Integer> stack = new Stack<>();
    
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            boolean invalid=false;
    
            int input;
            int end=0;
            for (int i = 0; i < n; i++) {
                input = Integer.parseInt(br.readLine());
                if(stack.contains(input)){
                    if(stack.peek()==input){
                        stack.pop();
                        sb.append("-").append("\n");
                    }else{
                        invalid=true;
                        break;
                    }
                }else{
                    for(int num=end+1;num<=input;num++){
                        stack.push(num);
                        sb.append("+").append("\n");
                    }
                    end=input;
                    stack.pop();
                    sb.append("-").append("\n");
                }
            }
    
            if (invalid) {
                System.out.println("NO");
            } else {
                System.out.println(sb.toString());
            }
            br.close();
        }
    
    }
    ```


### 3차 시도 - 27804kb 368ms

2차시도에서 고려했던 `contains()`를 빼고

`num 값이 end+1 보다 작게 되는 경우` 를 invalid로 수정하였다.

ex) 1,2,5,3,4 예제에서 input=3일때, num=3 인데, end+1=6 이므로 불가능하게 된다.

- 소스 코드

    ```java
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Stack;
    
    public class Main {
    
        static Stack<Integer> stack = new Stack<>();
    
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            boolean invalid=false;
    
            int input;
            int end=0;
           for (int i = 0; i < n; i++) {
                input = Integer.parseInt(br.readLine());
    
                if (!stack.isEmpty()&&stack.peek() == input) {
                    stack.pop();
                    sb.append("-").append("\n");
                } else {
                    if (end + 1 > input) {
                        invalid = true;
                        break;
                    }
                    for (int num = end + 1; num <= input; num++) {
                        stack.push(num);
                        sb.append("+").append("\n");
                    }
                    end = input;
                    stack.pop();
                    sb.append("-").append("\n");
                }
            }
    
            if (invalid) {
                System.out.println("NO");
            } else {
                System.out.println(sb.toString());
            }
            br.close();
        }
    
    }
    ```