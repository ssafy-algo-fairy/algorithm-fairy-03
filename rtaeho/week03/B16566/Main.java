package B16566;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[] cards;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cards = new int[N + 1];
        parent = new int[N + 2];
        for (int i = 1; i <= N + 1; i++) {
            parent[i] = i;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            cards[Integer.parseInt(st.nextToken())]++;
        }
        for (int i = 1; i <= N; i++) {
            if (cards[i] == 0) {
                parent[i] = i + 1;
            }
        }
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int card = Integer.parseInt(st.nextToken());
            int nextCard = find(card + 1);
            parent[nextCard] = nextCard + 1;
            sb.append(nextCard).append("\n");
        }
        System.out.print(sb);
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return false;
        }
        parent[rootB] = rootA;
        return true;
    }
}
