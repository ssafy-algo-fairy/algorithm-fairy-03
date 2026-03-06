package com.ssafy.algo.fairy.m3.week01.B1937;

import java.util.*;
import java.io.*;

public class b1937 {

	static class Point {
		int x, y, d;

		public Point(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	static int N, ans;

	static int[][] Map, dp;

	static Queue<Point> q = new LinkedList<>();

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		Map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				Map[i][j] = Integer.parseInt(st.nextToken());
		}

		ans = 0;
		dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans = Math.max(ans, dfs(i, j));
			}
		}

		System.out.println(ans);

	}

	static int dfs(int x, int y) {
		if (dp[x][y] != 0)
			return dp[x][y];

		dp[x][y] = 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i], ny = y + dy[i];
			if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1 || Map[nx][ny] <= Map[x][y])
				continue;

			dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
		}

		return dp[x][y];
	}

}
