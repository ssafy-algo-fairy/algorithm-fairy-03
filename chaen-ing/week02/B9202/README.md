## **🔍 문제 요약**

- 단어 사전에 등재되어 있는 단어의 목록과 Boggle 게임 보드가 주어짐
- 보드는 4*4
- 단어는 가로, 세로, 대각선 이용해서 만들 수 있음
- 1글자, 2글자로 이루어진 단어는 0점, 3글자, 4글자는 1점, 5글자는 2점, 6글자는 3점, 7글자는 5점, 8글자는 11점
- 얻을 수 있는 `최대 점수`, `가장 긴 단어`, `찾은 단어의 수`를 구하는 프로그램

- 1 < w 단어의 수 < 300,000
- 1 < b 보드의 개수 < 30

---

## **💡문제 접근 / 풀이 전략**

1. 단어로 트라이만들기

    ```java
    class Node {
    	HashMap<Character, Node> child;
    	boolean endOfWord;
    
    	public Node() {
    		child = new HashMap<>();
    	}
    	// insert, search
    }
    ```

   - `insert` : 노드의 자식에 다음 글자가 있으면 따라가고 없으면 생성
   - `seach` : 가망없으면 0, prefix있으면 1, 단어존재하면 2
2. dfs로 탐색하면서 set에 단어넣기
3. 점수 계산

---

## **✅ 코드 & 소요 시간**

```java
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
```

400884  / 3512

---

## **✍️ 회고**

- 단어 길이가 2 이하여도 가장 긴 단어가 될 수 있음…
- 원래 contains랑 hasPrefix를 따로 찾았었는데 그래서 시간이 두배 걸리고 있었음.. 합쳐버림
- 트라이에 단어말고 노드자체를 넘기던지 하면 더 좋을듯

---