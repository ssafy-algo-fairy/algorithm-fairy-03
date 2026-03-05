## **🔍 문제 요약**

- Di = Ai-L+1 ~ Ai 중의 최솟값
    
    ⇒ 즉 0번 인덱스부터 전으로 L개 중에서 최솟값을 연속해서 출력
    
    - 비교시 A0보다 작은것은 무시한다
- 1 ≤ L ≤ N ≤ 5,000,000

---

## **💡문제 접근 / 풀이 전략**

- 최악의 경우 N = 5,000,000이고 L = 1
- ~~우선순위큐로 할 수 있을 것 같았는데 안됨 ㅎㅋ~~
- **오름차순 덱 + 슬라이딩윈도우**
    1. 기본적으로 덱의 마지막에 새로운 값(n)을 넣는다
        - rear 값이 자신보다 크면 삭제한다 : 윈도우 내부에서 n보다 크고, n보다 먼저 빠지지도 않음 → 최솟값이 될 수 없다
        - rear 값이 자신보다 작으면 그냥 둠 : 앞의 값이 삭제되었을 때 n이 최솟값이 될 수 있음
    2. 빠지는 값은 front에서 확인 후 동일하다면 제거

---

## **✅ 코드 & 소요 시간**

```java
package boj11003;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		ArrayDeque<Integer> q = new ArrayDeque<>();
		int front = 0, back = L - 1;
		for (int i = 0; i < N; i++) {
			int n;

			if (i < L)
				n = arr[i];
			else
				n = arr[++back];

			// rear 값이 자신보다 크면 삭제한다 : 범위안에 자기보다 큰 수가 존재할 필요 없음
			// rear 값이 자신보다 작으면 그냥 둠 : 앞의 값이 삭제되었을 때 자신이 최솟값이 될 수 있음
			while (!q.isEmpty() && q.peekLast() > n) {
				q.pollLast();
			}
			q.offerLast(n);

			// front값이 본인과 동일하면 삭제
			if (i >= L) {
				if (arr[front++] == q.peekFirst())
					q.pollFirst();
			}

			sb.append(q.peekFirst()).append(" ");
		}

		System.out.println(sb);

	}

}

```

504684 / 2056

---

## **✍️ 회고**

---

## **🧩 다른 풀이 (선택)**

```java

```

---
