package B1194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int N, M;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M][64];
        // 모든 열쇠의 경우의 수 2^6개 만큼 관리

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') {
                    q.offer(new int[]{i, j, 0, 0});
                    // 최단 거리를 찾아야하므로 dist도 같이 추적
                    visited[i][j][0] = true;
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            int keys = cur[2];
            int dist = cur[3];

            if (map[y][x] == '1') {
                return dist;
            }

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dr[dir];
                int nx = x + dc[dir];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                    continue;
                }

                if (visited[ny][nx][keys]) {
                    continue;
                }

                // 출구 만남
                if (map[ny][nx] == '#') {
                    continue;
                }

                int key = keys;
                char cell = map[ny][nx];

                // 열쇠 만남
                if (cell >= 'a' && cell <= 'f') {
                    // 000001이 a만 얻은 상태이고, 000011이 ab 얻은 상태니까
                    // 기존 키에 or 연산으로 비트마스킹 연산
                    key = keys | (1 << (cell - 'a'));
                }

                // 문 만남
                if (cell >= 'A' && cell <= 'F') {
                    int door = 1 << (cell - 'A');
                    // 문도 마찬가지로 비트마스킹으로 A 빼서 맞는 열쇠 찾기
                    if ((keys & door) == 0) {
                        // & 연산으로 0이 나오면 해당 열쇠가 없는 상태
                        continue;
                    }
                }

                visited[ny][nx][key] = true;
                q.offer(new int[]{ny, nx, key, dist + 1});
            }
        }
        return -1;
    }
}