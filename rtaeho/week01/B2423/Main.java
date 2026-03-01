package B2423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    // 오른쪽 위, 오른쪽 아래, 왼쪽 위, 왼쪽 아래
    static int[] dr = {-1, 1, -1, 1};
    static int[] dc = {1, 1, -1, -1};

    static int[][] dist;

    // 우선순위 큐 용 클래스
    static class Node implements Comparable<Node> {
        int cost, r, c;

        Node(int cost, int r, int c) {
            this.cost = cost;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            // 비용이 작은 순서대로
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        dijkstra();
    }

    static void dijkstra() {
        // 꼭짓점 기준
        dist = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        // 우선순위 큐로 다익스트라 구현
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 내 위치는 0으로 초기화
        dist[0][0] = 0;
        pq.add(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int cost = cur.cost;
            int r = cur.r;
            int c = cur.c;

            if (cost > dist[r][c]) {
                // 더 적은 비용으로 방문 가능하면 탐색 X
                continue;
            }
            if (r == N && c == M) {
                // 도착
                break;
            }

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                // 꼭짓점 기준 범위 체크
                if (nr < 0 || nr > N || nc < 0 || nc > M) {
                    continue;
                }
                // 타일의 위치는 꼭짓점 중 작은 값으로
                int tr = Math.min(r, nr);
                int tc = Math.min(c, nc);

                // 타일 범위 체크
                if (tr < 0 || tr >= N || tc < 0 || tc >= M) continue;

                int add;
                if (dr[k] == dc[k]) {
                    // 같으면 \ 모양
                    add = (map[tr][tc] == '\\') ? 0 : 1;
                    // map에 저장된 값과 다를 경우 1 더함
                } else {
                    // 다르면 / 모양
                    add = (map[tr][tc] == '/') ? 0 : 1;
                    // map에 저장된 값과 다를 경우 1 더함
                }

                int sum = cost + add;
                // 기존 비용 + 추가 비용
                if (sum < dist[nr][nc]) {
                    // 기존 비용보다 작으면 업데이트
                    dist[nr][nc] = sum;
                    pq.add(new Node(sum, nr, nc));
                }
            }
        }

        if (dist[N][M] == Integer.MAX_VALUE) {
            System.out.println("NO SOLUTION");
        } else {
            System.out.println(dist[N][M]);
        }
    }
}