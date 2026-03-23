package theburiburi.week03.B16566;

import java.io.*;
import java.util.*;

public class Main {
    static int[] cards;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        cards = new int[M];
        parent = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        Arrays.sort(cards);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int cheol = Integer.parseInt(st.nextToken());
            int idx = upper(cheol);
            idx = find(idx);
            
            sb.append(cards[idx]).append("\n");
            union(idx, idx + 1);
        }

        System.out.println(sb.toString());
    }


    static int upper(int target) {
        int left = 0;
        int right = cards.length - 1;
        int res = cards.length;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (cards[mid] > target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        if (y >= parent.length) return;

        x = find(x);
        y = find(y);
        if (x != y) {
            parent[x] = y;
        }
    }
}