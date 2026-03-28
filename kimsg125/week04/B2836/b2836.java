package com.ssafy.algo.fairy.m3.week04.B2836;

import java.util.*;
import java.io.*;

public class b2836 {

	static class Back {
		int start, end;

		public Back(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	static int N, M;
	static long sum;

	static List<Back> goBack = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int b = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());

			if (a > b)
				continue;

			goBack.add(new Back(a, b));
		}

		goBack.sort((o1, o2) -> o1.start - o2.start);

		sum = M;
		int left = -1, right = -1;
		for (Back back : goBack) {
			if (back.start > right) {
				sum += 2 * (right - left);

				left = back.start;
				right = back.end;
				continue;
			}

			left = Math.min(left, back.start);
			right = Math.max(right, back.end);
		}
		sum += 2 * (right - left);

		System.out.println(sum);

	}

}
