package B4991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int W, H;
    static char[][] map;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static Queue<int[]> q;
    static int[][] times;
    static List<int[]> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) {
                break;
            }

            map = new char[H][W];
            q = new LinkedList<>();
            points = new ArrayList<>();

            for (int i = 0; i < H; i++) {
                String str = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == 'o') {
                        points.add(0, new int[]{i, j});
                        // 21 이전에는 addFirst 없음
                    }
                    if (map[i][j] == '*') {
                        points.add(new int[]{i, j});
                    }
                }
            }

            int trashCnt = points.size() - 1;
            int[][] dist = calcDist();

            if (isImpossible(dist)) {
                sb.append(-1).append("\n");
                continue;
            }

            int minTime = solve(dist, trashCnt);
            sb.append(minTime).append("\n");
        }
        System.out.print(sb);
    }

    // 최단거리 계산
    static int[][] calcDist() {
        int[][] dist = new int[points.size()][points.size()];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < points.size(); i++) {
            // bfs로 각 i에서 다른 점들까지 거리 계산
            times = bfs(points.get(i)[0], points.get(i)[1]);
            for (int j = 0; j < points.size(); j++) {
                // i점에서 포인트들의 최단거리 저장
                dist[i][j] = times[points.get(j)[0]][points.get(j)[1]];
            }
        }
        return dist;
    }

    // 갈 수 없는 경우 판단
    static boolean isImpossible(int[][] dist) {
        for (int i = 1; i < points.size(); i++) {
            // 하나라도 -1이면 갈 수 없는 경우
            if (dist[0][i] == -1) {
                return true;
            }
        }
        return false;
    }

    // 비트마스크 DP로 최소 이동 횟수 계산
    static int solve(int[][] dist, int trashCnt) {
        // 쓰레기 수를 비트마스킹으로 관리
        int[][] dp = new int[trashCnt][1 << trashCnt];
        for (int[] cell : dp) {
            Arrays.fill(cell, Integer.MAX_VALUE);
        }

        // 시작점에서 쓰레기로 출발
        for (int i = 0; i < trashCnt; i++) {
            // 시작 점에서 i번째 쓰레기로 가는 거리
            dp[i][1 << i] = dist[0][i + 1];
        }

        // 1. 청소 적게 한 순서부터 순회 001 010 ...
        for (int mask = 1; mask < (1 << trashCnt); mask++) {
            // 2. i번 칸에서
            for (int i = 0; i < trashCnt; i++) {
                // 아직 도달 못 함
                if (dp[i][mask] == Integer.MAX_VALUE) {
                    continue;
                }
                // 3. j번 칸으로
                for (int j = 0; j < trashCnt; j++) {
                    // 이미 방문
                    if ((mask & (1 << j)) != 0) {
                        continue;
                    }
                    // 도달 불가
                    if (dist[i + 1][j + 1] == -1) {
                        continue;
                    }

                    int next = mask | (1 << j);
                    dp[j][next] = Math.min(dp[j][next], dp[i][mask] + dist[i + 1][j + 1]);
                }
            }
        }

        // 모든 칸 청소한 상태
        int minTime = Integer.MAX_VALUE;
        for (int i = 0; i < trashCnt; i++) {
            minTime = Math.min(minTime, dp[i][(1 << trashCnt) - 1]);
        }
        return minTime == Integer.MAX_VALUE ? -1 : minTime;
    }

    // 거리 계산해서 반환
    static int[][] bfs(int sr, int sc) {
        times = new int[H][W];
        for (int[] time : times) {
            Arrays.fill(time, -1);
        }

        q = new LinkedList<>();
        times[sr][sc] = 0;
        q.add(new int[]{sr, sc});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                    // 범위
                    continue;
                }
                if (map[nr][nc] == 'x') {
                    // 장애물
                    continue;
                }
                if (times[nr][nc] != -1) {
                    // 이미 방문함
                    continue;
                }
                times[nr][nc] = times[r][c] + 1;
                q.add(new int[]{nr, nc});
            }
        }
        return times;
    }
}