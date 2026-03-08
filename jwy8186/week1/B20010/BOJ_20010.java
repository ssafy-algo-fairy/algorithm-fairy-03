package jwy8186.week1.B20010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20010 {

    static int n, k;
    static class Bridge {
        int s, e, w;
        public Bridge(int s, int e,  int w) {
            this.s=s; this.e=e; this.w=w;
        }

        public Bridge(int e, int w) {
            this.e=e; this.w=w;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        Bridge[] minBridges = new Bridge[k];
        boolean[] used = new boolean[k];

        for (int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            Bridge b = new Bridge(s, e, w);
            minBridges[i] = b;
        }

        Arrays.sort(minBridges, (a, b) -> Integer.compare(a.w, b.w));
        System.out.println(findDist(minBridges, used));

        System.out.println(findMax(minBridges, used));
    }

    static int findMax(Bridge[] bridges, boolean[] used) {
        int dist = 0;

        ArrayList<ArrayList<Bridge>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) graph.add(new ArrayList<>());

        for (int i=0; i<k; i++) {
            if (used[i]) {
                graph.get(bridges[i].s).add(new Bridge(bridges[i].e, bridges[i].w));
                graph.get(bridges[i].e).add(new Bridge(bridges[i].s, bridges[i].w));
            }
        }

        boolean[] visited = new boolean[n];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        visited[0] = true;
        int maxDist = 0, maxNode = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (Bridge next : graph.get(cur[0])) {
                if (visited[next.e]) continue;
                visited[next.e] = true;
                q.add(new int[]{next.e, cur[1] + next.w});
                if (cur[1] + next.w > maxDist) {
                    maxDist = cur[1] + next.w;
                    maxNode = next.e;
                }
            }
        }

        Arrays.fill(visited, false);
        q.add(new int[]{maxNode, 0});
        visited[maxNode] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (Bridge next : graph.get(cur[0])) {
                if (visited[next.e]) continue;
                visited[next.e] = true;
                q.add(new int[]{next.e, cur[1] + next.w});
                if (cur[1] + next.w > maxDist) maxDist = cur[1] + next.w;
            }
        }

        return maxDist;
    }

    static int findDist(Bridge[] bridges, boolean[] used) {
        int dist = 0;
        int[] parents = new int[n];
        for (int i=0; i<n; i++) parents[i] = i;

        for (int i=0; i<k; i++) {
            int p1 = find(bridges[i].s, parents);
            int p2 = find(bridges[i].e, parents);
            if (p1 != p2) {
                dist += bridges[i].w;
                used[i] = true;
                union(p1, p2, parents);
            }
        }
    
        return dist;
    }

    static int find(int x, int[] parents) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x], parents);
    }

    static void union(int a, int b, int[] parents) {
        parents[find(a, parents)] = find(b, parents);
    }


}

