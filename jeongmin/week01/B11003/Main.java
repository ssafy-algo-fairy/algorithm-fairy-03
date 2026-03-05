package week1.B11003;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Deque<int[]> q = new ArrayDeque<>();
		
		for (int i = 0; i < n; i++) {
			int[] a = {i, Integer.parseInt(st.nextToken())};
			while (!q.isEmpty()) {
				if (q.peekLast()[1] > a[1]) {
					q.pollLast();
				} else break;
			}
			q.add(a);
			if (q.peek()[0] == i-l) q.poll();
			sb.append(q.peek()[1]).append(" ");
		}
		
		System.out.println(sb);
	}
}
