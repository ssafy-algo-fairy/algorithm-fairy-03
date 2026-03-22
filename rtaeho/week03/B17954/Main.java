package B17954;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[][] arr = new int[2][N];
        long size = ((2L * N) * ((2L * N) + 1)) / 2;
        long answer = 0;

        if (N == 1) {
            arr[0][0] = 1;
            arr[1][0] = 2;
        } else {
            arr[0][0] = 2 * N - 3;
            arr[0][N - 1] = arr[0][0] + 1;

            arr[1][0] = 2 * N - 1;
            arr[1][N - 1] = arr[1][0] + 1;
            for (int i = 0; i <= 1; i++) {
                for (int j = 1; j <= N - 2; j++) {
                    arr[i][j] = arr[0][0] - ((i * (N - 2)) + j);
                }
            }

        }

        for (int i = 0; i <= N - 1; i++) {
            size -= arr[0][i];
            answer += (i + 1) * size;
        }

        for (int i = 0; i <= N - 2; i++) {
            size -= arr[1][i];
            answer += (N + i + 1) * size;
        }

        sb.append(answer).append("\n");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
