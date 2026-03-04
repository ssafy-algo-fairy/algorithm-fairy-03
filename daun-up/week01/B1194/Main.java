
/**
 * 1194. 달이 차오른다, 가자
 */
import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static char[][] map;
    // 상하좌우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static Queue<int[]> queue;
    static boolean[][][] visited;

    static List<String> keys; // 키 소장 여부 저장

    static int ans;

    static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    static void bfs() {

        while (!queue.isEmpty()) {

            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], keys = curr[2], dist = curr[3];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!inRange(nr, nc))
                    continue;

                char cell = map[nr][nc];
                int newKeys = keys; // 기존 열쇠 상태 복사

                if (cell == '#')
                    continue; // 벽: 이동할 수 없음

                if (cell == '1') {
                    ans = dist + 1;
                    return;
                }

                // 열쇠 획득
                if (cell >= 'a' && cell <= 'f') {
                    newKeys |= (1 << (cell - 'a'));
                }
                if (cell >= 'A' && cell <= 'F') {
                    if ((newKeys & (1 << (cell - 'A'))) == 0)
                        continue;
                }

                if (!visited[nr][nc][newKeys]) {
                    visited[nr][nc][newKeys] = true;
                    queue.add(new int[] { nr, nc, newKeys, dist + 1 });
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        queue = new LinkedList<>();
        visited = new boolean[n][m][1 << 6]; // 열쇠 상태 비트마스크 2^6 -> 64 가지

        for (int r = 0; r < n; r++) {
            String line = br.readLine();
            for (int c = 0; c < m; c++) {
                map[r][c] = line.charAt(c);
                if (map[r][c] == '0') { // 출발점~
                    queue.add(new int[] { r, c, 0, 0 }); // 행, 열, 열쇠 상태
                    visited[r][c][0] = true;
                }
            }
        }

        ans = -1;
        bfs();
        System.out.println(ans);

    }
}

/**
 * 이동 횟수의 최솟값 -> 최단 거리
 * BFS
 */
