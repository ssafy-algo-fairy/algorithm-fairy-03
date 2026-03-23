package theburiburi.week03.B2213;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;

    static int[] value;
    static int[][] dp;

    static boolean[] visited;

    static List<Integer> adj[];
    static List<Integer> res;

    public static void main(String args[]) throws IOException {
        inputFile();
        solve();
    }

    static void inputFile() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line = br.readLine();
        if (line == null) return;
        N = Integer.parseInt(line);

        value = new int[N + 1];
        adj = new ArrayList[N + 1];
        res = new ArrayList<>();
        
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            value[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            adj[left].add(right);
            adj[right].add(left);
        }
    }

    static void solve() {
        dfs(1, 0);

        Arrays.fill(visited, false);

        if (dp[1][1] > dp[1][0]) {
            sb.append(dp[1][1]).append("\n");
            findPath(1, true);
        } else {
            sb.append(dp[1][0]).append("\n");
            findPath(1, false);
        }

        Collections.sort(res);
        for (int node : res) {
            sb.append(node).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    static void dfs(int now, int parent) {
        dp[now][0] = 0;
        dp[now][1] = value[now];

        for (int next : adj[now]) {
            if (next == parent) continue;
            dfs(next, now);

            dp[now][0] += Math.max(dp[next][0], dp[next][1]);
            dp[now][1] += dp[next][0];
        }
    }

    static void findPath(int now, boolean include) {
        visited[now] = true;

        if (include) {
            res.add(now);
            for (int next : adj[now]) {
                if (visited[next]) continue;
                findPath(next, false);
            }
        } else {
            for (int next : adj[now]) {
                if (visited[next]) continue;
                if (dp[next][1] > dp[next][0]) {
                    findPath(next, true);
                } else {
                    findPath(next, false);
                }
            }
        }
    }
}