package week1.B1937;

import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	static int[][] map;
	static int[][] dp;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][];
		dp = new int[n][n];
		
		
		for (int i = 0; i < n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.fill(dp[i], -1);
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dfs(i, j);
			}
		}
		
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (dp[i][j] > max) max = dp[i][j];
			}
		}
		
		System.out.println(max);
	}
	
	public static int dfs(int x, int y) {
		if (dp[x][y] != -1) return dp[x][y];
		
		int max = 1;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			
			if (map[x][y] >= map[nx][ny]) continue;
			
			max = Math.max(max, 1 + dfs(nx, ny));
		}
		
		return dp[x][y] = max;
	}

}
