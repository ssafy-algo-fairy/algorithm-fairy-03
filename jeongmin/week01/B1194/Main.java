package week2.B1194;

import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m;
	static char[][] map;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int min;
	static Set<Character> keys = new HashSet<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'));
	static Set<Character> doors = new HashSet<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F'));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		min = -1;
		
		int x = -1, y = -1;
		
		for (int i = 0; i < n; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (input[j] == '0') {
					map[i][j] = '.';
					x = i;
					y = j;
				} else {
					map[i][j] = input[j];
				}
			}
		}
		
		bfs(x, y);
		
		System.out.println(min);
	}
	
	public static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] visited = new boolean[n][m][64];
		visited[i][j][0] = true;
		q.add(new int[] {i, j, 0, 0});	
		
		while (!q.isEmpty()) {
			int[] input = q.poll();
			int x = input[0];
			int y = input[1];
			int t = input[2];		
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				int k = input[3];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				
				if (map[nx][ny] == '1') {
					min = t+1;
					return;
				}
				
				if (map[nx][ny] == '#' || visited[nx][ny][k]) continue;
				
				if (doors.contains(map[nx][ny])) {
					int index = map[nx][ny]-'A';
					if ((k & (1 << index)) == 0) continue;
				}
				
				if (keys.contains(map[nx][ny])) {
					int index = map[nx][ny]-'a';
					if ((k & (1 << index)) == 0) k = k ^ (1 << index);
				}
				
				visited[nx][ny][k] = true;
				q.add(new int[] {nx, ny, t+1, k});
			}
		}
		
	}

}
