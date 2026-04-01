package B16438;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new char[7][N];

        solve(0, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            boolean hasA = false;
            boolean hasB = false;

            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 'A') {
                    hasA = true;
                }
                if (arr[i][j] == 'B') {
                    hasB = true;
                }
            }

            if (!hasA) {
                arr[i][0] = 'A';
            }
            if (!hasB) {
                arr[i][0] = 'B';
            }

            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static void solve(int day, int start, int end) {
        if (day == 7) {
            return;
        }

        int mid = (start + end) / 2;

        for (int i = start; i < mid; i++) {
            arr[day][i] = 'A';
        }
        for (int i = mid; i < end; i++) {
            arr[day][i] = 'B';
        }

        solve(day + 1, start, mid);
        solve(day + 1, mid, end);
    }
}