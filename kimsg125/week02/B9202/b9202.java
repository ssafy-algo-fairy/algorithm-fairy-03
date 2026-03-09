package com.ssafy.algo.fairy.m3.week02.B9202;

import java.util.*;
import java.io.*;

public class b9202 {

	static int W, B, maxScore, maxLen, wordCnt;
	static String maxLenWord;

	static String[] wordList;

	static boolean[][] visited = new boolean[4][4];
	static char[][] board = new char[4][4];

	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		W = Integer.parseInt(br.readLine());

		wordList = new String[W];
		for (int i = 0; i < W; i++)
			wordList[i] = br.readLine();
		Arrays.sort(wordList);

		br.readLine();
		B = Integer.parseInt(br.readLine());

		for (int i = 0; i < 4; i++)
			Arrays.fill(visited[i], false);
		for (int i = 0; i < B; i++) {
			for (int j = 0; j < 4; j++)
				board[j] = br.readLine().toCharArray();

			maxScore = 0;
			maxLen = 0;
			wordCnt = 0;
			boggle();

			if (i != B - 1)
				br.readLine();

			System.out.println(maxScore + " " + maxLenWord + " " + wordCnt);
		}

	}

	static void boggle() {
		for (String word : wordList) {
			boolean flag = false;

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (flag)
						break;
					
					if (dfs(word, 0, i, j))
						flag = true;
				}
				if (flag)
					break;
			}

			if (!flag)
				continue;

			if (word.length() == 3 || word.length() == 4)
				maxScore += 1;
			else if (word.length() == 5)
				maxScore += 2;
			else if (word.length() == 6)
				maxScore += 3;
			else if (word.length() == 7)
				maxScore += 5;
			else if (word.length() == 8)
				maxScore += 11;

			if (word.length() > maxLen) {
				maxLenWord = word;
				maxLen = word.length();
			}

			wordCnt++;
		}
	}

	static boolean dfs(String word, int idx, int x, int y) {
		if (board[x][y] != word.charAt(idx))
			return false;
		if (word.length() - 1 == idx)
			return true;

		boolean flag = false;

		visited[x][y] = true;
		for (int i = 0; i < 8; i++) {
			if (flag)
				break;

			int nx = x + dx[i], ny = y + dy[i];
			if (nx < 0 || nx > 3 || ny < 0 || ny > 3 || visited[nx][ny] == true)
				continue;

			if (dfs(word, idx + 1, nx, ny))
				flag = true;
		}
		visited[x][y] = false;

		return flag;
	}

}
