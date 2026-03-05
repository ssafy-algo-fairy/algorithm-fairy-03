## 📝 문제 요약

N개의 수 A1, A2, ..., AN과 L이 주어진다.

Di = Ai-L+1 ~ Ai 중의 최솟값이라고 할 때, D에 저장된 수를 출력하는 프로그램을 작성

## 💡 접근 방법

- 사실 실패했다. PQ 두개로 풀었다가 실패했다. HashMap도 섞어서 써봤지만 실패했다.
- 걍 Deque로 푸는게 그냥 개천재 발상
- Deque에 넣으면서 현재 값 보다 크면 버린다. 이게 다다
- 그리고 앞에서 부터 보면 가장 작은 값이 보이겠지..

## 📌 회고
- deque라고 했는데도 어케 푸는지 몰랐다.
- 지능이 점점 낮아지는 것 같다.
- 잠을 잘 자야할 것 같다. 잠을 안자니 머리가 안돌아간다.

## 📌 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_11003 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {

            while (q.peekLast() != null && q.peekLast()[0] > arr[i]) q.pollLast();

            q.addLast(new int[]{arr[i], i});

            while (q.peekFirst() != null && q.peekFirst()[1] <= i - l) q.pollFirst();

            sb.append(q.peekFirst()[0]).append(" ");
        }

        System.out.println(sb);
    }
}

```

---
Ref : #19 
