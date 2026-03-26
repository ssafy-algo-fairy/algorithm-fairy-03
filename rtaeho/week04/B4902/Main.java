package B4902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = 1;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            if (N == 0) {
                break;
            }

            dp = new int[(int) (Math.pow(N, 2) + 1)];

            for (int i = 1; i <= N; i++) {
                // i-1행까지의 원소 개수 = (i-1)^2
                int prevCnt = (int) Math.pow(i - 1, 2);
                for (int j = 1; j <= i * 2 - 1; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    // 각 행 내에서의 누적합을 1차원 배열에 순차적으로 저장
                    dp[prevCnt + j] = dp[prevCnt + j - 1] + num;
                }
            }

            sb.append(T++).append(". ").append(solve()).append("\n");
        }
        System.out.print(sb);
    }

    static int solve() {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i * 2 - 1; j++) {

                // 정방향
                if (j % 2 == 1) {
                    int currentSum = 0;
                    for (int k = 0; i + k <= N; k++) {
                        int targetPrevNum = (int) Math.pow((i + k - 1), 2);
                        // i + k 행의 시작 위치 c에서 끝 위치 j + k * 2 까지의 합
                        currentSum += (dp[targetPrevNum + j + 2 * k] - dp[targetPrevNum + j - 1]);
                        maxSum = Math.max(maxSum, currentSum);
                    }
                }

                // 역방향
                else {
                    int currentSum = 0;
                    for (int k = 0; i - k >= 1; k++) {
                        // 역방향으로 갈 때 인덱스 체크
                        // j - k * 2: 현재 층보다 위층에서 왼쪽 끝 인덱스
                        // j = 현재 층보다 위층에서 오른쪽 끝 인덱스 (위층의 최대 열 개수 이내여야 함)
                        if (j - k * 2 < 1 || j > (i - k) * 2 - 1) {
                            break;
                        }

                        int targetPrevNum = (int) Math.pow((i - k - 1), 2);
                        currentSum += (dp[targetPrevNum + j] - dp[targetPrevNum + j - k * 2 - 1]);
                        maxSum = Math.max(maxSum, currentSum);
                    }
                }
            }
        }
        return maxSum;
    }
}