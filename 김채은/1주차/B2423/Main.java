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
