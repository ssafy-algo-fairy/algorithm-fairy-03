package B17842;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            if (graph[i].size() == 1) {
                cnt++;
            }
        }

        // 리프가 1개면 1개
        // 리프가 2개면 1개
        // 리프가 3개면 2개
        // 리프가 4개면 2개
        // 리프가 5개면 3개
        // N + 1 / 2 개
        System.out.println((cnt + 1) / 2);
    }
}
