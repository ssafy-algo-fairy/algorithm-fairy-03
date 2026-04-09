import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main  {
	
	static class Line {
		int start_x;
		int start_y;
		int end_x;
		int end_y;
		int dir;
		
		int collision(Line other) {
			int dist = 0;
			if (this.dir % 2 == 0
				&& (this.start_x <= other.start_x && other.start_x <= this.end_x || this.start_x <= other.end_x && other.end_x <= this.end_x)
				&& other.start_y <= this.start_y && this.start_y <= other.end_y)
			{
				if (dir == 0)
					dist = other.start_x - this.start_x;
				else
					dist = this.end_x - other.end_x;
			}
			else if (this.dir % 2 == 1
				&& (this.start_y <= other.start_y && other.start_y <= this.end_y || this.start_y <= other.end_y && other.end_y <= this.end_y)
				&& other.start_x <= this.start_x && this.start_x <= other.end_x)
			{
				if (dir == 1)
					dist = other.start_y - this.start_y;
				else
					dist = this.end_y - other.end_y;
			}
			return dist;
		}

		public Line(int start_x, int start_y, int end_x, int end_y, int dir) {
			super();
			if (dir % 2 == 0 && start_x > end_x) {
				int tmp = start_x;
				start_x = end_x;
				end_x = tmp;
			}
			else if (dir % 2 == 1 && start_y > end_y) {
				int tmp = start_y;
				start_y = end_y;
				end_y = tmp;
			}
			this.start_x = start_x;
			this.start_y = start_y;
			this.end_x = end_x;
			this.end_y = end_y;
			this.dir = dir;
		}
	}
	
	static int l;
	static int n;
	static int dir;
	static long t;
	
	static int[][] delta = {
		{1, 0}, {0, 1}, {-1, 0}, {0, -1}
	};
	
	static int[] time;
	static char[] next_dir;
	
	static Line[] line;
	
	static boolean isValid(int x, int y) {
		return -l <= x && x <= l && -l <= y && y <= l;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		l = Integer.parseInt(br.readLine());
		n = Integer.parseInt(br.readLine());
		
		time = new int[n];
		next_dir = new char[n];
		line = new Line[n + 1];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			next_dir[i] = st.nextToken().charAt(0);
		}
		
		dir = 0;
		t = 0;
		int dx = 0;
		int dy = 0;
		for (int i = 0; i < n; i++) {
			int[] d = delta[dir];
			Line now = new Line(dx, dy, dx + d[0] * time[i], dy + d[1] * time[i], dir);
			int col = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				int col_t = now.collision(line[j]);
				if (col_t != 0) {
					if (col > col_t)
						col = col_t;
				}
			}
			if (col != Integer.MAX_VALUE) {
				t += col;
				System.out.println(t);
				return ;
			}
			if (!isValid(dx + d[0] * time[i], dy + d[1] * time[i])) {
				if (dir == 0)
					t += l - dx + 1;
				else if (dir == 1)
					t += l - dy + 1;
				else if (dir == 2)
					t += dx + l + 1;
				else if (dir == 3)
					t += dy + l + 1;
				System.out.println(t);
				return ;
			}
			line[i] = now;
			dx = dx + d[0] * time[i];
			dy = dy + d[1] * time[i];
			t += time[i];
			dir = next_dir[i] == 'L' ? (dir + 1) % 4 : (((dir - 1) % 4) + 4) % 4;
		}
		int[] d = delta[dir];
		Line now = null;
		if (dir == 0)
			now = new Line(dx, dy, l, dy, dir);
		else if (dir == 1)
			now = new Line(dx, dy, dx, l, dir);
		else if (dir == 2)
			now = new Line(dx, dy, -l, dy, dir);
		else if (dir == 3)
			now = new Line(dx, dy, dx, -l, dir);
		int col = Integer.MAX_VALUE;
		for (int j = 0; j < n; j++) {
			int col_t = now.collision(line[j]);
			if (col_t != 0) {
				if (col > col_t)
					col = col_t;
			}
		}
		if (col != Integer.MAX_VALUE) {
			t += col;
			System.out.println(t);
			return ;
		}
		if (dir == 0)
			t += l - dx + 1;
		else if (dir == 1)
			t += l - dy + 1;
		else if (dir == 2)
			t += dx + l + 1;
		else if (dir == 3)
			t += dy + l + 1;
		System.out.println(t);
	}
}