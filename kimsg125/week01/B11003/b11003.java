package com.ssafy.algo.fairy.m3.week01.B11003;

import java.util.*;
import java.io.*;

public class b11003 {

	static class Num {
		int num, idx;

		public Num(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}
	}

	static int N, L;

	static Deque<Num> dq = new LinkedList<>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < L; i++) {
			int num = Integer.parseInt(st.nextToken());

			while (!dq.isEmpty() && dq.getLast().num > num)
				dq.pollLast();

			dq.offer(new Num(num, i));

			sb.append(dq.getFirst().num + " ");
		}

		for (int i = L; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			while (!dq.isEmpty() && dq.getLast().num > num)
				dq.pollLast();

			dq.offer(new Num(num, i));

			if (dq.getFirst().idx == i - L)
				dq.pollFirst();

			sb.append(dq.getFirst().num + " ");
		}

		System.out.println(sb);

	}

}
