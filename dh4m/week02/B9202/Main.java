import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Main  {
	static class Trie {
		Trie[] node = new Trie[26];
		boolean end = false;
		
		void input(String str, int idx) {
			if (str.length() == idx) {
				this.end = true;
				return;
			}
			int c = str.charAt(idx) - 'A';
			if (node[c] == null) {
				node[c] = new Trie();
				node[c].input(str, idx + 1);
			}
			else
				node[c].input(str, idx + 1);
		}
	}
	
	static int[][] delta = {
			{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
	};
	
	static Trie trie = new Trie();
	static Set<String> set; 
	static int w;
	static int b;
	static char[][] board = new char[4][];
	static boolean[][] visit = new boolean[4][4];
	static char[] nowword = new char[8];
	static int score;
	static String longword;
	static int searchword;
	
	static boolean isValid (int i, int j) {
		return i >= 0 && i < 4 && j >= 0 && j < 4;
	}
	
	static void search(int i, int j, int depth, Trie root) {
		nowword[depth] = board[i][j];
		if (root.end) {
			int len = depth + 1;
			String now = String.valueOf(nowword, 0, len);
			if (!set.contains(now)) {
				set.add(now);
				if (len > longword.length() || (len == longword.length() && now.compareTo(longword) < 0)) {
					longword = now;
				}
				searchword++;
				switch (len) {
				case 1: case 2:
					break;
				case 3: case 4:
					score += 1;
					break;
				case 5:
					score += 2;
					break;
				case 6:
					score += 3;
					break;
				case 7:
					score += 5;
					break;
				case 8:
					score += 11;
					break;
				}
			}
		}
		for (int[] d: delta) {
			int di = i + d[0];
			int dj = j + d[1];
			if (isValid(di, dj) && !visit[di][dj] && root.node[board[di][dj] - 'A'] != null) {
				visit[di][dj] = true;
				search(di, dj, depth + 1, root.node[board[di][dj] - 'A']);
				visit[di][dj] = false;
			}
		}
		return ;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		w = Integer.parseInt(br.readLine());
		for (int i = 0; i < w; i++) {
			String str = br.readLine();
			trie.input(str, 0);
		}
		
		br.readLine();
		
		b = Integer.parseInt(br.readLine());
		for (int t = 0; t < b; t++) {
			score = 0;
			longword = "";
			set = new HashSet<>();
			searchword = 0;
			for (int i = 0; i < 4; i++) {
				board[i] = br.readLine().toCharArray();
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (trie.node[board[i][j] - 'A'] != null) {
						visit[i][j] = true;
						search(i, j, 0, trie.node[board[i][j] - 'A']);
						visit[i][j] = false;
					}
				}
			}
			bw.append(score + " " + longword + " " + searchword + "\n");
			set = null;
			if (t != b - 1)
				br.readLine();
		}
		bw.flush();
		bw.close();
	}
}