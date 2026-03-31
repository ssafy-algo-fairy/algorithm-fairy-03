package B32645;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dp = new int[N + 1];
        solve(1, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dp[i] == 1 ? "donggggas" : "uppercut").append("\n");
        }
        System.out.print(sb);
    }

    static int solve(int cur, int p) {
        boolean canWin = false;
        boolean isLeaf = true;

        for (int next : graph.get(cur)) {
            if (next == p) {
                continue;
            }

            isLeaf = false;
            if (solve(next, cur) == 0) {
                canWin = true;
            }
        }

        if (isLeaf || !canWin) {
            return dp[cur] = 0;
        } else {
            return dp[cur] = 1;
        }
    }
}