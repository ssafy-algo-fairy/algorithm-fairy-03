## **🔍 문제 요약**

- 순환 x, 서로 다른 두 정류장을 잇는 경로가 항상 존재

  → 트리

- 조건
    1. 출발 정류장, 도착 정류장
    2. 경유지 수 제한 없다. 안가도됨. 순서 자유
    3. 정류장 사이에 도로
    4. 같은 정류장 여러번 방문 x
- 모든 도로에 하나 이상의 버스 노선이 지나가도록 구성할때 버스 노선 개수 최소

- 2 ≤ N 정류장의 수 ≤ 200,000
- 정류장 번호는 0부터 N-1

---

## **💡문제 접근 / 풀이 전략**

- 리프랑 리프를 연결하는 하나의 도로를 만든다
- 따라서 리프노드 수에서 / 2 홀수개면 + 1

---

## **✅ 코드 & 소요 시간**

```java
package week02.B17842;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		// 리프노드의 개수 / 2 + 1
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if (graph[i].size() == 1)
				sum++;
		}
		sum = sum % 2 == 0 ? sum / 2 : sum / 2 + 1;

		System.out.println(sum);
	}

}

```

---

## **✍️ 회고**

- bfs로 갈라질때 세다가 그러지 않아도 된다는 것을 알게되었다.. 가능하긴 하려나 ? 모르겠다
- 오늘은 자소서 쓸 시간이 많이 있을 것 같다
- 행복하다