package week02.B9202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static int W, B;
	static char[][] board;
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	static Node trie;
	static Set<String> words;    // 단어 찾았는지
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		W = Integer.parseInt(br.readLine());
		trie = new Node();    // 트라이 생성
		for (int i = 0; i < W; i++) {
			trie.insert(br.readLine());
		}

		br.readLine();
		B = Integer.parseInt(br.readLine());
		board = new char[4][4];
		for (int b = 0; b < B; b++) {

			// 보드 입력받기
			for (int i = 0; i < 4; i++) {
				board[i] = br.readLine().toCharArray();
			}

			visited = new boolean[4][4];
			words = new HashSet<>();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					visited[i][j] = true;
					dfs(i, j, String.valueOf(board[i][j]));
					visited[i][j] = false;
				}
			}

			// 점수계산
			int sum = 0;
			String longestWord = "";
			for (String word : words) {
				if (word.length() > longestWord.length()) {
					longestWord = word;
				} else if (word.length() == longestWord.length() && word.compareTo(longestWord) < 0) {
					longestWord = word;
				}

				if (word.length() <= 2)
					continue;
				else if (word.length() <= 4)
					sum += 1;
				else if (word.length() <= 5)
					sum += 2;
				else if (word.length() <= 6)
					sum += 3;
				else if (word.length() <= 7)
					sum += 5;
				else
					sum += 11;

			}
			sb.append(sum).append(" ").append(longestWord).append(" ").append(words.size()).append("\n");

			if (b == B - 1)
				break;
			br.readLine();
		}

		System.out.println(sb);
	}

	static void dfs(int r, int c, String str) {
		int state = trie.search(str);
		if (state == 0)
			return;
		if (state == 2)
			words.add(str);

		if (str.length() == 8)
			return;

		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < 0 || nc < 0 || nr > 3 || nc > 3 || visited[nr][nc])
				continue;

			visited[nr][nc] = true;
			dfs(nr, nc, str + board[nr][nc]);
			visited[nr][nc] = false;
		}
	}
}

class Node {
	HashMap<Character, Node> child;
	boolean endOfWord;

	public Node() {
		child = new HashMap<>();
	}

	public void insert(String str) {
		Node node = this;

		for (int i = 0; i < str.length(); i++) {
			node = node.child.computeIfAbsent(str.charAt(i), key -> new Node());
		}

		node.endOfWord = true;
	}

	public int search(String str) {
		Node node = this;

		for (int i = 0; i < str.length(); i++) {
			node = node.child.getOrDefault(str.charAt(i), null);
			if (node == null)
				return 0;    // 아예 없음
		}

		if (node.endOfWord)
			return 2; // 단어
		return 1; // prefix만
	}
}