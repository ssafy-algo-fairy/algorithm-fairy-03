package day_0305;

import java.io.*;
import java.util.*;

public class boj11003 {
	static int N, L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		ArrayDeque<int[]> deque = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			// 현재 수보다 큰 수 제거
			while (!deque.isEmpty() && deque.peekLast()[1] > n) {
				deque.removeLast();
			}

			deque.add(new int[] { i, n });
			
			if (deque.peekFirst()[0] <= (i - L)) {
				deque.removeFirst();
			}

			sb.append(deque.peekFirst()[1]).append(" ");

		}

		System.out.println(sb.toString());
	}

}
