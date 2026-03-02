## **🔍 문제 요약**

<img width="502" height="355" alt="스크린샷 2026-03-02 오전 1 11 56" src="https://github.com/user-attachments/assets/d51573d9-8abe-479e-80bb-196fd1dfcbe2" />

- 이런 식일 때 3열의 아무거나 하나만 회전(90도)시켜도 전원과 전구가 연결됨
- 돌려야하는 칸의 개수의 최솟값을 구한다
- 불가능하면 NO SOLUTION

- 1 ≤ N, M ≤ 500

---

## **💡문제 접근 / 풀이 전략**

<aside>
💡

**다익스트라(Dijkstra)**

- 그래프의 최단 경로를 구하는 알고리즘
- 음수 가중치 허용 x, 시간복잡도 O(mlogn)
- 과정
    1. 미방문 정점 중 출발지로부터 가장짧은 정점 방문
    2. 해당 정점의 인접 정점들의 값을 갱신하고 방문 체크
        - 인접 정점들은 우선순위큐에 넣어서 짧은 곳 부터 방문한다
        - 거쳐서갈때 더 짧으면 갱신
        - 미방문 정점들의 값은 무한대
    3. 큐가 empty가 되면 알고리즘 종료
</aside>

<aside>
💡

**0 - 1 BFS**

- 가중치가 0, 1만 존재하는 그래프에서 최소 비용 구하기
- 시간복잡도 O(V + E)
- Deque 사용
- 과정
    1. 덱의 front에서 노드 꺼내기
    2. 인접 노드 살펴보고 갱신
    3. 갱신후에 그 노드를 향하는 가중치가 0이면 front, 1이면 back에 삽입 (거리가 작은 노드가 먼저 처리)
</aside>

- 각 칸의 모든 꼭짓점을 기준으로 생각하기
- ex) 2행 2열로 주어진다면 → 9개의 꼭짓점으로 판단
    
    
    | \ | / |
    | --- | --- |
    | / | \ |
    - (0,0) 에서 출발
        
        → 안뒤집고 갈 수 있는 곳 (1,1)
        
        → 뒤집고 갈 수 있는 곳 (1,0), (0,1)
        
- NO SOLUTION도 있을까..? 모르겠다

---

## **✅ 코드 & 소요 시간**

```java
package boj2423;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] map;
	static int[][] dist;
	static int[] dr = {-1, -1, 1, 1};    // 좌상, 우상, 좌하, 우하
	static int[] dc = {-1, 1, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		dist = new int[N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}

		ArrayDeque<Node> q = new ArrayDeque<>();
		q.offerFirst(new Node(0, 0));
		dist[0][0] = 0;

		while (!q.isEmpty()) {
			Node now = q.pollFirst();
			int curD = dist[now.r][now.c];

			for (int i = 0; i < 4; i++) {
				// 이동할 꼭짓점 좌표
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				if (nr < 0 || nc < 0 || nr > N || nc > M) {
					continue;
				}

				// 현재 꼭지점 주변 칸들 검사
				char prefer;
				int mapR, mapC;
				if (i == 0) {    // 좌상
					prefer = '\\';
					mapR = now.r - 1;
					mapC = now.c - 1;
				} else if (i == 1) {    // 우상
					prefer = '/';
					mapR = now.r - 1;
					mapC = now.c;
				} else if (i == 2) {    // 좌하
					prefer = '/';
					mapR = now.r;
					mapC = now.c - 1;
				} else {    // 우하
					prefer = '\\';
					mapR = now.r;
					mapC = now.c;
				}

				int w = (map[mapR][mapC] == prefer) ? 0 : 1;

				//거리 갱신
				if (dist[nr][nc] > curD + w) {
					dist[nr][nc] = curD + w;
					if (w == 0)
						q.offerFirst(new Node(nr, nc));
					else
						q.offerLast(new Node(nr, nc));
				}
			}
		}

		if (dist[N][M] == Integer.MAX_VALUE) {
			System.out.println("NO SOLUTION");
		} else {
			System.out.println(dist[N][M]);
		}
	}
}

class Node {
	int r, c;

	public Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

```

25224 / 228

---

## **✍️ 회고**

- 이런 류의 문제를 첨 풀어봐서 힘들었다… 더 연습해야하듯

---

## **🧩 다른 풀이 (선택)**

```java

```

---
