package theburiburi.week03.B17954;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb;
    static int N;
    static long ans;
    static int[] apple1, apple2;
    static Deque<Integer> apple1Dq;
    static Deque<Integer> apple2Dq;

    public static void main(String args[]) throws IOException {
        inputFile();
        solve();
        System.out.print(sb.toString());
    }

    static void inputFile() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        N = Integer.parseInt(line);
        sb = new StringBuilder();

        apple1Dq = new ArrayDeque<>();
        apple2Dq = new ArrayDeque<>();
    }

    static void solve() {
        if (N == 1) {
            sb.append(2).append("\n1\n2");
            return;
        }

        apple1 = new int[N];
        apple2 = new int[N];

        apple1[0] = 2 * N - 2;
        apple1[N - 1] = 2 * N - 3;
        apple2[0] = 2 * N;
        apple2[N - 1] = 2 * N - 1;

        int num = 1;
        for (int i = 1; i < N - 1; i++) apple2[i] = num++;
        for (int i = 1; i < N - 1; i++) apple1[i] = num++;

        for (int x : apple1) apple1Dq.addLast(x);
        for (int x : apple2) apple2Dq.addLast(x);

        long currentSum = (long) N * (2 * N + 1);
        ans = 0;

        for (int i = 0; i < 2 * N; i++) {
            int minVal = Integer.MAX_VALUE;
            int type = 0;

            if (!apple1Dq.isEmpty()) {
                if (apple1Dq.peekFirst() < minVal) { minVal = apple1Dq.peekFirst(); type = 1; }
                if (apple1Dq.peekLast() < minVal) { minVal = apple1Dq.peekLast(); type = 2; }
            }
            if (!apple2Dq.isEmpty()) {
                if (apple2Dq.peekFirst() < minVal) { minVal = apple2Dq.peekFirst(); type = 3; }
                if (apple2Dq.peekLast() < minVal) { minVal = apple2Dq.peekLast(); type = 4; }
            }

            if (type == 1) apple1Dq.pollFirst();
            else if (type == 2) apple1Dq.pollLast();
            else if (type == 3) apple2Dq.pollFirst();
            else if (type == 4) apple2Dq.pollLast();

            currentSum -= minVal;
            ans += currentSum*(i+1);
        }

        sb.append(ans).append("\n");
        for (int i = 0; i < N; i++) sb.append(apple1[i]).append(" ");
        sb.append("\n");
        for (int i = 0; i < N; i++) sb.append(apple2[i]).append(" ");
    }
}