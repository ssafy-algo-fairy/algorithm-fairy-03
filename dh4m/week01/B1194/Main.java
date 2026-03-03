import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class State {
		int i;
		int j;
		int key;
		
		public State(int i, int j, int key) {
			super();
			this.i = i;
			this.j = j;
			this.key = key;
		}
	}
	
	static int n, m;
	static char[][] map;
	static boolean[][][] visit;
	static int[][] delta = {
			{1, 0}, {0, 1}, {-1, 0}, {0, -1}
	};
	
	static boolean isValid(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		int start_i = 0;
		int start_j = 0;
		map = new char[n][m];
		visit = new boolean[n][m][1 << 6];
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == '0') {
					start_i = i;
					start_j = j;
					map[i][j] = '.';
				}
			}
		}
		
		Queue<State> q = new ArrayDeque<>();
		visit[start_i][start_j][0] = true;
		q.add(new State(start_i, start_j, 0));
		boolean complete = false;
		int len = 0;
		bfsLoop:
		while (q.size() != 0) {
			int qsize = q.size();
			len++;
			for (int i = 0; i < qsize; i++) {
				State now = q.poll();
				for (int[] d : delta) {
					int di = now.i + d[0];
					int dj = now.j + d[1];
					int key = now.key;
					if (isValid(di, dj)) {
						if (map[di][dj] == '.') {
							if (!visit[di][dj][key]) {
								visit[di][dj][key] = true;
								q.add(new State(di, dj, key));
							}
						}
						else if ('a' <= map[di][dj] && map[di][dj] <= 'f') {
							key |= 1 << (map[di][dj] - 'a');
							if (!visit[di][dj][key]) {
								visit[di][dj][key] = true;
								q.add(new State(di, dj, key));
							}
						}
						else if ('A' <= map[di][dj] && map[di][dj] <= 'F') {
							if ((key & (1 << (map[di][dj] - 'A'))) != 0) {
								if (!visit[di][dj][key]) {
									visit[di][dj][key] = true;
									q.add(new State(di, dj, key));
								}
							}
						}
						else if (map[di][dj] == '1') {
							complete = true;
							break bfsLoop;
						}
					}
				}
			}
		}
		System.out.println(complete ? len : -1);
	}
}