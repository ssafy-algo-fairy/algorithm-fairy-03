package day_0319;

import java.io.*;
import java.util.*;

public class boj17954 {
	static int N;
	static int tmp = 0;
	static int t = 1;
	static long sum = 0;
	static long answer = 0;

	static int min, max;

	static ArrayDeque<Integer> queue1 = new ArrayDeque();
	static ArrayDeque<Integer> queue2 = new ArrayDeque();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= 2 * N; i++) {
			sum += i;
		}

		if (N == 1) {
			System.out.println("2");
			System.out.println("1");
			System.out.println("2");
		} else {
			queue1.addFirst(2 * N);
			sb1.append(2 * N + " ");
			for (int i = 1; i <= N - 2; i++) {
				queue1.addLast(i);
				sb1.append(i + " ");
			}
			queue1.addLast(2 * N - 1);
			sb1.append((2 * N - 1) + " ");

			queue2.addFirst(2 * N - 2);
			sb2.append(2 * N - 2 + " ");
			for (int i = 1; i <= N - 2; i++) {
				int n = N - 2 + i;
				if (n < 0)
					break;
				queue2.addLast(n);
				sb2.append(n + " ");
			}
			queue2.addLast(2 * N - 3);
			sb2.append(2 * N - 3 + " ");

			calculate();
			System.out.println(answer);
			System.out.println(sb1);
			System.out.println(sb2);
		}
	}

	public static void calculate() {
		int count = 0;
		while (!queue2.isEmpty()) {
			int n = queue2.pollLast();
			if (count == 1) {
				min = n;
			}
			count++;
			tmp += n;
			answer += (sum - tmp) * t;
			t++;
		}

		while (!queue1.isEmpty()) {
			int n = queue1.pollLast();
			tmp += n;
			answer += (sum - tmp) * t;
			t++;

		}

	}
}
