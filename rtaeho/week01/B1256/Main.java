package B1256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] dp;
    // dp[i][j] = i개의 a와 j개의 z로 만들 수 있는 문자열의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][M + 1];
        makeDP();
        System.out.println(solve());
    }


    static void makeDP() {
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (i == 0 || j == 0) {
                    // aCnt, zCnt, aa, zz 등 하나가 0개면 하나만 가능
                    dp[i][j] = 1;
                } else {
                    // 이전의 dp 값에서 하나씩 추가해서 더하면 됨
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    if (dp[i][j] > K) {
                        // K보다 크면 더 이상 계산할 필요 없음
                        dp[i][j] = K + 1;
                    }
                }
            }
        }
    }

    static String solve() {
        StringBuilder sb = new StringBuilder();

        if (dp[N][M] < K) {
            return "-1";
        } else {
            int aCnt = N;
            int zCnt = M;

            while (aCnt > 0 || zCnt > 0) {
                if (aCnt == 0) {
                    sb.append('z');
                    zCnt--;
                } else if (zCnt == 0) {
                    sb.append('a');
                    aCnt--;
                } else {
                    int cnt = dp[aCnt - 1][zCnt];
                    // a 먼저 추가할 때 만들 수 있는 문자열 개수
                    if (cnt >= K) {
                        // cnt가 K보다 크거나 같으면 a를 먼저 추가한 채로 만들 수 있음
                        sb.append('a');
                        aCnt--;
                    } else {
                        // cnt가 K보다 작으면 a 추가하면 안됨 더 뒤에 문자열로 가야됨
                        sb.append('z');
                        zCnt--;
                        K -= cnt;
                        // a를 추가 못하니까 z 추가하고 K에서 cnt만큼 빼줌
                        // a로 시작하는 단어의 개수가 cnt개라 그만큼 K에서 빼줘야 함
                    }
                }
            }

        }
        return sb.toString();
    }
}