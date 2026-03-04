package com.ssafy.algo.fairy.m3.week01.B1256;

import java.util.*;
import java.io.*;

public class b1256 {

	static int N, M, K;

	static int[][] dp;

	static int MAX = 1000000001;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N + M + 1][N + M + 1];

		for (int i = 0; i <= N + M; i++) {
			for (int j = 0; j < i; j++) {
				comb(i, j);
			}
		}

		if (dp[N + M][M] < K) {
			System.out.println(-1);
			return;
		}

		while (N > 0 || M > 0) {
			if (N == 0) { // z만 남음
				sb.append('z');
				M--;
				continue;
			}
			if (M == 0) { // a만 남음
				sb.append('a');
				N--;
				continue;
			}

			if (K <= dp[N + M - 1][N - 1]) {
				sb.append('a');
				N--;
			} else {
				sb.append('z');
				K -= dp[N + M - 1][N - 1];
				M--;
			}
		}

		System.out.println(sb);

	}

	static int comb(int n, int r) {
		if (dp[n][r] != 0)
			return dp[n][r];
		if (r == 0 || n == r)
			return dp[n][r] = 1;

		// nCr=n-1Cr-1 + n-1Cr
		return dp[n][r] = Math.min(MAX, comb(n - 1, r - 1) + comb(n - 1, r));
	}

}
