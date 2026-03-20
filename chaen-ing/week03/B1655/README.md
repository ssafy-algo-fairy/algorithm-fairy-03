## **🔍 문제 요약**

- 정수 하나씩 외칠 때 마다 중간값을 말한다
- 짝수개라면 중간의 두 수 중 작은것
- ex)
    - 1 → 1
    - 1, 5 → 짝수니까 두개중 작은것 1
    - 1, 5, 2 → 2
    - 1, 5, 2, 10 → 2, 5 중 작은 2

- 1 ≤ N 개수 ≤ 100,000
- -10,000 ≤ 각 정수 ≤ 10,000
- 시간제한 0.1

---

## **💡문제 접근 / 풀이 전략**

- 두개의 priority queue 사용
    - `first` : 내림차순
    - `second` : 오름차순
- first의 가장 큰 값과 second의 가장 작은 값을 비교
- 이때 first가 항상 1많게 유지한다

---

## **✅ 코드 & 소요 시간**

```java
package week03.B1655;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> first = new PriorityQueue<>(Collections.reverseOrder());    // 내림차순
		PriorityQueue<Integer> second = new PriorityQueue<>();    // 오름차순

		int tmp, res;
		for (int i = 0; i < N; i++) {
			tmp = Integer.parseInt(br.readLine());

			// 첫번째 입력 처리
			if (first.isEmpty()) {
				first.offer(tmp);
				sb.append(tmp).append("\n");
				continue;
			}

			if (!second.isEmpty() && tmp > second.peek())
				second.offer(tmp);
			else
				first.offer(tmp);

			// 개수 조정 : 항상 first가 더 많게
			if (first.size() > second.size() + 1)
				second.offer(first.poll());
			else if (second.size() > first.size())
				first.offer(second.poll());

			if (i % 2 == 1)
				res = Math.min(first.peek(), second.peek());
			else
				res = first.peek();

			sb.append(res).append("\n");
		}

		System.out.println(sb);
	}
}

```

28300 / 384

---

## **✍️ 회고**

-

---