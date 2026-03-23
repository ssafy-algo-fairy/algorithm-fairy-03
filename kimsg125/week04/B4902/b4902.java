package com.ssafy.algo.fairy.m3.week04.B4902;

import java.util.*;
import java.io.*;

public class b4902 {

	static int N, maxSum;

	static int[][] triSum;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int testCase = 1;

		while (true) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;

			maxSum = Integer.MIN_VALUE;
			triSum = new int[N + 1][2 * N];
			for (int i = 1; i <= N; i++)
				for (int j = 1; j <= 2 * i - 1; j++)
					triSum[i][j] = triSum[i][j - 1] + Integer.parseInt(st.nextToken());

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= 2 * i - 1; j++) {
					if (j % 2 == 1) {
						int sum = triSum[i][j] - triSum[i][j - 1], len = 1;
						maxSum = Math.max(maxSum, sum);

						for (int k = 1; k < N - i + 1; k++) {
							len += 2;
							sum += triSum[i + k][j + len - 1] - triSum[i + k][j - 1];

							maxSum = Math.max(maxSum, sum);
						}
					} else {
						int sum = triSum[i][j] - triSum[i][j - 1], len = 1;
						maxSum = Math.max(maxSum, sum);

						for (int k = 1; k < i; k++) {
							len += 2;
							if (j - len < 0 || j > 2 * (i - k) - 1)
								break;

							sum += triSum[i - k][j] - triSum[i - k][j - len];

							maxSum = Math.max(maxSum, sum);
						}
					}
				}
			}

			sb.append(testCase).append(". ").append(maxSum).append("\n");

			testCase++;

		}

		System.out.println(sb);

	}

}
