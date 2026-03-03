import java.io.*;
import java.util.*;

public class boj1194 {
	static int N, M;
	static int x, y;
	static char[][] map;
	static boolean[][][] visited;
	static int answer = 0;

	static Queue<int[]> queue = new ArrayDeque();

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M][64];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == '0') {
					x = i;
					y = j;
				}
			}
		}

		queue.add(new int[] { x, y, 0 });
		visited[x][y][0] = true;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int k = 0; k < size; k++) {
				int[] cur = queue.poll();
				int x = cur[0];
				int y = cur[1];
				int key = cur[2];

				if (map[x][y] == '1') {
					System.out.println(answer);
					return;
				}

				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					int newKey = key;

					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;

					if (map[nx][ny] == '#')
						continue;
					else if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
						newKey |= (1 << (map[nx][ny] - 'a'));
					} else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
						if ((newKey & (1 << (map[nx][ny] - 'A'))) == 0)
							continue;
					}

					if (!visited[nx][ny][newKey]) {
						visited[nx][ny][newKey] = true;
						queue.add(new int[] { nx, ny, newKey });
					}

				}
			}

			answer++;
		}

		System.out.println(-1);
	}

}
