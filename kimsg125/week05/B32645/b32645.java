package com.ssafy.algo.fairy.m3.week05.B32645;

import java.io.*;
import java.util.*;

public class b32645 {

	static int N;
    static boolean[] win;
    static List<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        win = new boolean[N + 1];
        dfs(1, 0);

        for (int i = 1; i <= N; i++) {
            sb.append(win[i] ? "donggggas\n" : "uppercut\n");
        }

        System.out.print(sb);
    }

    static boolean dfs(int cur, int parent) {
        boolean canWin = false;

        for (int next : graph[cur]) {
            if (next == parent) continue;

            boolean childWin = dfs(next, cur);
            if (!childWin) {
                canWin = true;
            }
        }

        win[cur] = canWin;
        return win[cur];
    }

}
