import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main  {
	static int w, h;
	static int dest;
	static int[][] map;
	static int[][] coor;
	static int[][] cost;
	static int[][] dp;
	
	static class Vertex {
		int i, j;

		public Vertex(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
	
	static int[][] delta = {
			{1, 0}, {0, 1}, {-1, 0}, {0, -1}
	};
	
	static boolean isValid(int i, int j) {
		return i >= 0 && i < h && j >= 0 && j < w;
	}
	
	static int dist(int i, int j) {
		if (i == j)
			return 0;
		if (cost[i][j] > 0)
			return cost[i][j];
		int len = 1;
		Queue<Vertex> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[h][w];
		q.add(new Vertex(coor[i][0], coor[i][1]));
		
		while (q.size() != 0) {
			int qsize = q.size();
			for (int r = 0; r < qsize; r++) {
				Vertex now = q.poll();
				for (int[] d : delta) {
					int di = now.i + d[0];
					int dj = now.j + d[1];
					if (di == coor[j][0] && dj == coor[j][1]) {
						cost[i][j] = cost[j][i] = len;
						return len;
					}
					if (isValid(di, dj) && map[di][dj] != -1 && !visit[di][dj]) {
						visit[di][dj] = true;
						q.add(new Vertex(di, dj));
					}
				}
			}
			len++;
		}
		return -1;
	}
	
	static int tsp(int node, int visit) {
		if (visit == (1 << (dest + 1)) - 1)
			return 0;
		if (dp[node][visit] != 0) 
			return dp[node][visit];
		
		int result = Integer.MAX_VALUE;
		for (int i = 1; i <= dest; i++) {
			if (((1 << i) & visit) == 0) {
				int distance = dist(node, i);
				if (distance == -1)
					return -1;
				int tsp_val = tsp(i, visit | (1 << i));
				if (tsp_val == -1)
					return -1;
				result = Math.min(result, distance + tsp_val);
			}
		}
		dp[node][visit] = result;
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		while (w != 0 && h != 0) {
			map = new int[h][w];
			coor = new int[11][2];
			dest = 0;
			for (int i = 0; i < h; i++) {
				String input = br.readLine();
				for (int j = 0; j < w; j++) {
					char c = input.charAt(j);
					if (c == 'o') {
						coor[0][0] = i;
						coor[0][1] = j;
					}
					else if (c == '*') {
						dest++;
						coor[dest][0] = i;
						coor[dest][1] = j;
					}
					if (c == 'x')
						map[i][j] = -1;
				}
			}
			cost = new int[dest + 1][dest + 1];
			dp = new int[dest + 1][1 << (dest + 1)];
			bw.append(String.valueOf(tsp(0, 1)) + "\n");
			
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
		}
		bw.flush();
		bw.close();
	}
}