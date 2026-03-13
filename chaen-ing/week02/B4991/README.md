## **🔍 문제 요약**

- 직사각형 모양의 방
    - 깨끗한칸, 더러운칸, 가구, 시작위치가 주어짐
- 더러운칸의 개수는 10개 이하 → 비트마스킹
- 모든 더러운 칸을 깨끗한 칸으로 바꾸는 최소 이동 횟수
- 1 ≤ w, h 가로세로 크기 ≤ 20

---

## **💡문제 접근 / 풀이 전략**

- 더러운칸의 위치를 미리 저장해놓고 각각의 인덱스를 **비트마스킹으로** 표현
- **BFS**로 탐색
- **3차원 visited 배열**로 [행][열][더러운칸] 방문관리

---

## **✅ 코드 & 소요 시간**

```java
package week02.B4991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int h, w;
	static char[][] board;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static ArrayList<Integer> dirtyR;
	static ArrayList<Integer> dirtyC;
	static int startR;
	static int startC;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		while (w != 0) {
			// 초기화
			board = new char[h][w];
			dirtyR = new ArrayList<>();
			dirtyC = new ArrayList<>();
			startC = -1;
			startR = -1;

			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					board[i][j] = line.charAt(j);

					if (startC == -1 && board[i][j] == 'o') {
						startR = i;
						startC = j;
					} else if (board[i][j] == '*') {
						dirtyR.add(i);
						dirtyC.add(j);
					}
				}
			}

			sb.append(bfs()).append("\n");

			// 받으면서 끝내기
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
		}

		System.out.println(sb);

	}

	static int bfs() {
		boolean[][][] visited = new boolean[h][w][(1 << dirtyR.size())];
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.offer(new Node(startR, startC, 0, 0));
		visited[startR][startC][0] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.dirty == Math.pow(2, dirtyR.size()) - 1)
				return cur.cnt;

			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr < 0 || nc < 0 || nr >= h || nc >= w || board[nr][nc] == 'x')
					continue;
				if (visited[nr][nc][cur.dirty])
					continue;

				if (board[nr][nc] == '*') {
					int dirtyNum = -1;
					for (int j = 0; j < dirtyR.size(); j++) {
						if (dirtyR.get(j) == nr && dirtyC.get(j) == nc) {
							dirtyNum = j;
							break;
						}
					}

					int newDirty = cur.dirty | (1 << dirtyNum);
					q.offer(new Node(nr, nc, newDirty, cur.cnt + 1));
					visited[nr][nc][newDirty] = true;
				} else {
					q.offer(new Node(nr, nc, cur.dirty, cur.cnt + 1));
					visited[nr][nc][cur.dirty] = true;
				}
			}
		}
		return -1;
	}
}

class Node {
	int r, c;
	int dirty;
	int cnt;

	public Node(int r, int c, int dirty, int cnt) {
		super();
		this.r = r;
		this.c = c;
		this.dirty = dirty;
		this.cnt = cnt;
	}
}

```

50212 / 356

---

## **✍️ 회고**

- 옛날에 풀었던 키가지고 방 찾는문제!!랑 완전 비슷하네요

---