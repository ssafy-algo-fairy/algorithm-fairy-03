package day_0309;

import java.io.*;
import java.util.*;

public class boj9202 {

	static int N, M;

	static char[][] dictionary;
	static char[][] map;

	static boolean[][] visited;
	static boolean[] found;

	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static int maxScore;
	static int num;
	static String word;

	static int[] score = { 0, 0, 0, 1, 1, 2, 3, 5, 11 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		dictionary = new char[N][];

		for (int i = 0; i < N; i++)
			dictionary[i] = br.readLine().toCharArray();

		br.readLine();

		M = Integer.parseInt(br.readLine());

		for (int m = 0; m < M; m++) {

			map = new char[4][4];

			for (int i = 0; i < 4; i++)
				map[i] = br.readLine().toCharArray();

			found = new boolean[N];

			maxScore = 0;
			num = 0;
			word = "";

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {

					for (int k = 0; k < N; k++) {

						if (map[i][j] != dictionary[k][0]) // 이미 찾은 단어면 건너뛰기
							continue;

						visited = new boolean[4][4];
						visited[i][j] = true;

						dfs(i, j, 1, k);

					}
				}
			}

			System.out.println(maxScore + " " + word + " " + num);

			if (m < M - 1)
				br.readLine();
		}
	}

	/**
	 *
	 * @param x : x 좌표
	 * @param y : y 좌표
	 * @param idx : 타켓 단어의 글자 인덱스
	 * @param dictIdx : 사전 타켓 단어 인덱스
	 */
	static void dfs(int x, int y, int idx, int dictIdx) {

		char[] target = dictionary[dictIdx];
		int len = target.length;

		// 전체 단어 탐색완료
		if (idx == len) {

			if (found[dictIdx])
				return;

			found[dictIdx] = true;

			num++;
			maxScore += score[len];

			String w = new String(target);

			if (word.length() < len || (word.length() == len && w.compareTo(word) < 0)) {
				word = w;
			}

			return;
		}

		for (int d = 0; d < 8; d++) {

			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || visited[nx][ny])
				continue;

			// 찾으려는 글자와 일치시
			if (map[nx][ny] == target[idx]) {
				visited[nx][ny] = true;
				dfs(nx, ny, idx + 1, dictIdx);
				visited[nx][ny] = false;
			}
		}
	}
}