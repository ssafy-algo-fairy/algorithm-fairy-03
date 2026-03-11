package B1230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String O = br.readLine();
        String N = br.readLine();

        char[] o = O.toCharArray();
        char[] n = N.toCharArray();

        int[][] dp = new int[O.length()][N.length()];

        for (int i = 0; i < O.length(); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int j = 0; j < N.length(); j++) {
            if (o[0] == n[j]) {
                dp[0][j] = (j == 0) ? 0 : 1;
            }
        }

        for (int i = 1; i < O.length(); i++) {
            int prefixMin = Integer.MAX_VALUE;

            for (int j = 1; j < N.length(); j++) {
                if (o[i] == n[j]) {
                    if (dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                    }

                    if (prefixMin != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], prefixMin + 1);
                    }
                }

                if (dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    prefixMin = Math.min(prefixMin, dp[i - 1][j - 1]);
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int j = 0; j < N.length(); j++) {
            if (dp[O.length() - 1][j] == Integer.MAX_VALUE) {
                continue;
            }

            int cur = dp[O.length() - 1][j];

            if (j != N.length() - 1) {
                cur++;
            }

            ans = Math.min(ans, cur);
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}