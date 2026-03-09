package B9202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int W, B;
    static String[] words;
    static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static char[][] board;
    static boolean[][] visited;
    static int maxScore, count;
    static String bestWord;
    static List<int[]>[] positions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        W = Integer.parseInt(br.readLine());
        words = new String[W];
        for (int i = 0; i < W; i++) {
            words[i] = br.readLine();
        }

        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        });

        br.readLine();

        B = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < B; i++) {
            board = new char[4][4];
            maxScore = 0;
            count = 0;
            bestWord = "";
            positions = new ArrayList[26];

            for (int j = 0; j < 26; j++) {
                positions[j] = new ArrayList<>();
            }

            for (int j = 0; j < 4; j++) {
                String line = br.readLine();
                for (int k = 0; k < 4; k++) {
                    board[j][k] = line.charAt(k);
                    positions[board[j][k] - 'A'].add(new int[]{j, k});
                }
            }

            if (i != B - 1) {
                br.readLine();
            }

            for (String word : words) {
                if (canMake(word)) {
                    maxScore += score[word.length()];
                    count++;

                    if (bestWord.isEmpty()) {
                        bestWord = word;
                    }
                }
            }

            sb.append(maxScore).append(" ").append(bestWord).append(" ").append(count).append("\n");
        }

        System.out.print(sb);
    }

    static boolean canMake(String word) {
        List<int[]> starts = positions[word.charAt(0) - 'A'];

        for (int[] pos : starts) {
            visited = new boolean[4][4];
            if (dfs(word, 0, pos[0], pos[1])) {
                return true;
            }
        }

        return false;
    }

    static boolean dfs(String word, int idx, int r, int c) {
        if (board[r][c] != word.charAt(idx)) {
            return false;
        }

        if (idx == word.length() - 1) {
            return true;
        }

        visited[r][c] = true;

        for (int dir = 0; dir < 8; dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) {
                continue;
            }
            if (visited[nr][nc]) {
                continue;
            }

            if (dfs(word, idx + 1, nr, nc)) {
                visited[r][c] = false;
                return true;
            }
        }

        visited[r][c] = false;
        return false;
    }
}