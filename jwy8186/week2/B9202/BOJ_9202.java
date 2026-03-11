import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_9202 {

    static int w, b;
    static String[] words;
    static char[][] boards;

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Pos {
        int x, y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        w = Integer.parseInt(br.readLine());

        words = new String[w];
        for (int i = 0; i < w; i++) words[i] = br.readLine();

        Arrays.sort(words, (s1, s2) -> {
            if (s1.length() != s2.length()) return s2.length() - s1.length();
            return s1.compareTo(s2);
        });
        br.readLine();


        b = Integer.parseInt(br.readLine());
        for (int i=0; i<b; i++) {
            boards = new char[4][4];

            for (int j=0; j<4; j++) {
                boards[j] = br.readLine().toCharArray();
            }
            
            int result = 0;
            int maxLen = 0;
            int foundCount = 0;
            String maxString = " ";
            for (int j=0; j<w; j++) {
                if (canFind(words[j])) {
                    foundCount++;
                    int len = words[j].length();
                    if (len == 3 || len == 4) result += 1;
                    else if (len == 5) result += 2;
                    else if (len == 6) result += 3;
                    else if (len == 7) result += 5;
                    else if (len >= 8) result += 11;
                    
                    if (len > maxLen) {
                        maxLen = len;
                        maxString = words[j];
                    }
                }
            }

            System.out.println(result + " " + maxString + " " + foundCount);
            br.readLine();
        }
    }

    static boolean canFind(String word) {
        boolean[][] visited = new boolean[4][4];

        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (word.charAt(0) != boards[i][j]) continue;
                visited[i][j] = true;
                if (dfs(i, j, word, 0, visited)) return true;
                visited[i][j] = false;
            }
        }

        return false;
    }

    static boolean dfs(int x, int y, String word, int idx, boolean[][] visited) {
        if (idx == word.length()-1) return true;


        for (int i=0; i<8; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx < 0 || nx >=4 || ny < 0 || ny >= 4) continue;
            if (word.charAt(idx+1) != boards[nx][ny]) continue;
            if (visited[nx][ny]) continue;

            visited[nx][ny] = true;
            if (dfs(nx, ny, word, idx + 1, visited)) return true;
            visited[nx][ny] = false;
        }
        return false;
    }
    
}