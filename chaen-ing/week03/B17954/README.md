## **🔍 문제 요약**

- 총 2*N개의 사과가 들어있었는데, 2개의 기다란 튜브에 N개씩 들어있었다.
    - 각 튜브는 앞 뒤가 뚫려있어 총 4개의 구멍으로 사과 꺼낼 수 있다
- 사과는 크기가 각자 다르고 1 ~ 2N까지 중복 X
- 구멍쪽에 있는 4개의 사과 중 가장 작은 사과를 꺼내는 과정을 2N번 반복
- 사과 부패 → 남은 크기 * T만큼의 부패도를 갖게되며 누적됨
- 최대한 부패되지 않도록 사과를 배치한다
- 최소의 부패도를 갖는 배치가 여러개 존재시 아무거나 출력
- 1 ≤ N ≤ 10,000

---

## **💡문제 접근 / 풀이 전략**

- 가장 큰걸 먼저 빼는건 불가능 → 무조건 마지막인듯
- 큰거부터 4개를 고른다
    - 가장 먼저 뺄 수 있는 것 중에서 제일 큰거는 2N-3
    - 해당 값 안쪽에 남은것중에 가장 큰걸 채우기
- `예를들어` N = 4라서 8개라면 가장 먼저 뺄 수 있는 것 중 가장 큰것은 5
    - 5 4 3 X / X 2 1 X
- 이때 5 → 4 → 3 차례로 비게 되므로 다음차례엔 6, 7, 8 중에서 지울 수 있다
    - 3옆에 6을 두면 다음걸 7을 지울 수 있음
    - 만약 3옆에 7을 둔다면 다음 순서가 6 → 2 → 1로 넘어감

---

## **✅ 코드 & 소요 시간**

```java
package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		// 1인 경우 따로 처리
		if (n == 1) {
			System.out.println(2);
			System.out.println(1);
			System.out.println(2);
			return;
		}

		long appleSum = 0;
		for (int i = 1; i <= 2 * n; i++) {
			appleSum += i;
		}

		// 사과 배치 생성
		ArrayDeque<Integer> first = new ArrayDeque<>();
		ArrayDeque<Integer> second = new ArrayDeque<>();

		for (int i = 2 * n - 3; i >= n - 1; i--) {
			first.offer(i);
			sb.append(i).append(" ");
		}
		first.offer(2 * n - 2);
		sb.append(2 * n - 2).append("\n");

		second.offer(2 * n - 1);
		sb.append(2 * n - 1).append(" ");
		for (int i = n - 2; i >= 1; i--) {
			second.offer(i);
			sb.append(i).append(" ");
		}
		second.offer(2 * n);
		sb.append(2 * n).append("\n");

		// 부패도 계산
		long sum = 0;
		int top = 0;
		for (int i = 0; i < 2 * n; i++) {
			sum += i * appleSum;
			if (i < n) {
				top = first.pollFirst();
			} else {
				top = second.pollFirst();
			}
			appleSum -= top;
		}

		System.out.println(sum);
		System.out.println(sb);
	}
}

```

16296 / 148

---

## **✍️ 회고**

- 하고보니까 큐가 필요없을 것 같은데 귀찮아서 걍 뒀습니다…. 배열도 필요없을듯…..