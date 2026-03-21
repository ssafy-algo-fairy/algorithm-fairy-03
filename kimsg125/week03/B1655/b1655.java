package com.ssafy.algo.fairy.m3.week03.B1655;

import java.util.*;
import java.io.*;

public class b1655 {

	static int N, mid;

	static PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
	static PriorityQueue<Integer> right = new PriorityQueue<>((o1, o2) -> o1 - o2);

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		mid = Integer.parseInt(br.readLine());
		sb.append(mid).append("\n");
		for (int i = 1; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			if (num > mid) {
				if (left.size() >= right.size())
					right.offer(num);
				else {
					left.offer(mid);
					right.offer(num);
					mid = right.poll();
				}
			} else {
				if (left.size() < right.size())
					left.offer(num);
				else {
					right.offer(mid);
					left.offer(num);
					mid = left.poll();
				}
			}

			sb.append(mid).append("\n");
		}

		System.out.println(sb);
		
	}

}
