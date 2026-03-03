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
