package theburiburi.week01.B20010;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb;
    static int N, K;
    static int total;
    static List<Node> list[];
    static List<SortNode> sl;
    static int[] arr;
    
    static int maxMove;
    static int farNode;
    static boolean[] visited;

    public static void main(String args[]) throws IOException {
        inputFile();
        solve();
        System.out.println(sb);
    }

    static class SortNode implements Comparable<SortNode> {
        int left, right, value;
        public SortNode(int left, int right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
        @Override
        public int compareTo(SortNode o) {
            return value - o.value;
        }
    }

    static class Node {
        int num, value;
        public Node(int num, int value) {
            this.num = num;
            this.value = value;
        }
    }

    static void inputFile() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new List[N];
        arr = new int[N];
        sl = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
            arr[i] = i;
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            sl.add(new SortNode(left, right, value));
        }
        Collections.sort(sl);
    }

    static void solve() {
        total = 0;
        int count = 0;
        for (int i = 0; i < K; i++) {
            SortNode now = sl.get(i);
            if (find(now.left) != find(now.right)) {
                union(now.left, now.right, now.value);
                list[now.left].add(new Node(now.right, now.value));
                list[now.right].add(new Node(now.left, now.value));
                if (++count == N - 1) break;
            }
        }
        sb.append(total).append("\n");

        maxMove = 0;
        visited = new boolean[N];
        dfs(0, 0);

        maxMove = 0;
        visited = new boolean[N];
        dfs(farNode, 0);
        
        sb.append(maxMove);
    }

    static void dfs(int now, int dist) {
        visited[now] = true;
        if (dist > maxMove) {
            maxMove = dist;
            farNode = now;
        }

        for (Node next : list[now]) {
            if (!visited[next.num]) {
                dfs(next.num, dist + next.value);
            }
        }
    }

    static void union(int x, int y, int value) {
        x = find(x);
        y = find(y);
        if (x != y) {
            arr[x] = y;
            total += value;
        }
    }

    static int find(int x) {
        if (arr[x] == x) return x;
        return arr[x] = find(arr[x]);
    }
}