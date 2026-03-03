## **🔍 문제 요약**

- 미로 탈출 최소 횟수 구하기
- 문으로는 대응하는 열쇠가 있을때만 이동가능
- 열쇠 위치에 최초 도착시 획득 가능
- 같은 열쇠, 같은 문이 여러개 있을 수 있고 문에 대응하는 열쇠가 없을수도 있다
- 출구는 최소 1개 이상 존재. 탈출 못할 시 -1 출력
- 열쇠 여러 번 사용 가능

- 1 ≤ 세로 N, 가로 M ≤ 50

---

## **💡문제 접근 / 풀이 전략**

1. bfs로 경로 구하면서 갖고 있는 열쇠를 체크하자
2. 열쇠 보유 여부에 따라 왔던 길도 다시 갈 수 있음
3. 최소 횟수를 저장하되 열쇠가 생기면 같은 곳도 다시 방문하도록 
    
    → 이때 열쇠를 111111 이런식으로 비트마스킹 (열쇠가 6개밖에 없으므로)
    
4. **이때 열쇠가 많다고 무조건 좋은 것이 아님…**
    
    **⇒ 키마다 최소 이동 횟수를 유지하자**
    

---

## **✅ 코드 & 소요 시간**

```java
package boj1194;

import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static char[][] maze;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N][M];

		int curR = -1, curC = -1;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = line.charAt(j);

				if (curR == -1 && maze[i][j] == '0') {
					curR = i;
					curC = j;
					maze[i][j] = '.';
				}
			}
		}

		System.out.println(bfs(curR, curC));

	}

	static int bfs(int r, int c) {
		boolean[][][] visited = new boolean[N][M][64]; // 2^6 : 111111

		ArrayDeque<Node> q = new ArrayDeque<>();
		q.offer(new Node(r, c, 0, 0));

		while (!q.isEmpty()) {
			Node node = q.poll();
			int curR = node.r;
			int curC = node.c;
			int curMove = node.move;

			for (int i = 0; i < 4; i++) {
				int nr = curR + dr[i];
				int nc = curC + dc[i];
				int nk = node.key;

				// 범위초과, 벽
				if (nr < 0 || nc < 0 || nr >= N || nc >= M || maze[nr][nc] == '#')
					continue;

				// 종료
				if (maze[nr][nc] == '1')
					return curMove + 1;

				// 문 -> 열쇠 체크
				if (maze[nr][nc] >= 'A' && maze[nr][nc] <= 'F') {
					if ((nk & (1 << (maze[nr][nc] - 'A'))) == 0)
						continue;
				}

				// 열쇠 칸
				if (maze[nr][nc] >= 'a' && maze[nr][nc] <= 'f') {
					nk |= 1 << (maze[nr][nc] - 'a');
				}

				if (!visited[nr][nc][nk]) {
					visited[nr][nc][nk] = true;
					q.offer(new Node(nr, nc, nk, curMove + 1));
				}

			}

		}
		return -1;
	}
}

class Node {
	int r;
	int c;
	int key;
	int move;

	public Node(int r, int c, int key, int move) {
		this.r = r;
		this.c = c;
		this.key = key;
		this.move = move;
	}
}
```

15388/132

---

## **✍️ 회고**

- 열쇠가 생기는 경우에 항상 업데이트했는데 열쇠있다고 무조건 best 인 것이 아님..!! 열쇠 개수별로 유지하기

---
