package day_0306;

import java.io.*;
import java.util.*;

public class boj1937 {
	static int N, answer;
	static int[][] map;
	static int[][] dp;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dfs(i, j);
			}
		}

		System.out.println(answer);
	}

	public static void dfs(int x, int y) {
		int n = map[x][y];

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;

			if (map[nx][ny] > n) {
				if (dp[nx][ny] == 0) {
					dfs(nx, ny);
				}
				dp[x][y] = Math.max(dp[x][y], dp[nx][ny] + 1);
			}
		}

		if (dp[x][y] == 0) {
			dp[x][y] = 1;
		}
		answer = Math.max(answer, dp[x][y]);
	}

}
