package com.ssafy.algo.fairy.m3.w1.B1194;

import java.util.*;
import java.io.*;

public class b1194 {

	static class Point {
		int x, y, mask, t;

		Point(int x, int y, int mask, int t) {
			this.x = x;
			this.y = y;
			this.mask = mask;
			this.t = t;
		}
	}

	static int N, M, x, y;

	static char[][] Map;

	static boolean[][][] visited;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				Map[i][j] = str.charAt(j);
				if (Map[i][j] == '0') {
					x = i;
					y = j;
					Map[i][j] = '.';
				}
			}
		}

		visited = new boolean[N][M][64];
		Queue<Point> q = new LinkedList<>();

		q.offer(new Point(x, y, 0, 0));
		visited[x][y][0] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();
			if (Map[p.x][p.y] == '1') {
				System.out.println(p.t);
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;

				char c = Map[nx][ny];
				if (c == '#') // 벽
					continue;

				int nmask = p.mask;

				if (c >= 'a' && c <= 'f') { // 열쇠
					nmask |= 1 << (c - 'a');
				}

				if (c >= 'A' && c <= 'F') { // 문
					if ((p.mask & (1 << (c - 'A'))) == 0)
						continue;
				}

				if (!visited[nx][ny][nmask]) {
					q.offer(new Point(nx, ny, nmask, p.t + 1));
					visited[nx][ny][nmask] = true;
				}
			}
		}

		System.out.println(-1);
		
	}

}
