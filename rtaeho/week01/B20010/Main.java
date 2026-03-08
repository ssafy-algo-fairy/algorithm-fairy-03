package B20010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static List<int[]>[] graph;
    static int[][] arr;
    static int[] parent;
    static int maxDist = 0;
    static int farNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 간선 저장용
        arr = new int[K][3];

        for (int i = 0; i < K; i++) {
            // 간선 정보 저장
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }
        // 가중치 기준 오름차순 정렬
        // 이렇게 하는 거 익숙하지 않은데 좀 외우자..
        Arrays.sort(arr, (a, b) -> a[2] - b[2]);
        /*
        음수 → a가 앞
        0   → 같은 값
        양수 → b가 앞
         */

        /*
        크루스칼 규칙
        1. 간선을 가중치 기준으로 정렬
        2. 가장 작은 간선부터 확인
        3. 사이클이 생기지 않으면 추가(유니온 파인드)
        4. N-1개의 간선이 선택되면 종료(트리의 특성)
        */

        // MST용 그래프
        graph = new ArrayList[N];
        // 유니온 파인드용
        parent = new int[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            parent[i] = i;
        }

        // 크루스칼로 MST 만들기
        int edgeCnt = 0;
        int sum = 0;

        for (int i = 0; i < K; i++) {
            int u = arr[i][0];
            int v = arr[i][1];
            int w = arr[i][2];

            if (union(u, v)) {
                edgeCnt++;
                sum += w;

                // 양방향에 추가
                graph[u].add(new int[]{v, w});
                graph[v].add(new int[]{u, w});

                // 간선이 N-1개 되면 종료
                if (edgeCnt == N - 1) {
                    break;
                }
            }
        }

        // 가장 먼 거리는 dfs 두 번으로 구할 수 있음

        // 가장 먼 노드 찾기
        dfs(0, -1, 0);

        maxDist = 0;
        dfs(farNode, -1, 0);

        System.out.println(sum);
        System.out.println(maxDist);
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return false;
        }

        parent[b] = a;
        return true;
    }

    static void dfs(int now, int prev, int dist) {
        if (dist > maxDist) {
            maxDist = dist;
            farNode = now;
        }

        for (int[] next : graph[now]) {
            int nextNode = next[0];
            int weight = next[1];

            if (nextNode == prev) {
                continue;
            }

            dfs(nextNode, now, dist + weight);
        }
    }
}