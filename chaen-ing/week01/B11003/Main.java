package boj11003;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		ArrayDeque<Integer> q = new ArrayDeque<>();
		int front = 0, back = L - 1;
		for (int i = 0; i < N; i++) {
			int n;

			if (i < L)
				n = arr[i];
			else
				n = arr[++back];

			// rear 값이 자신보다 크면 삭제한다 : 범위안에 자기보다 큰 수가 존재할 필요 없음
			// rear 값이 자신보다 작으면 그냥 둠 : 앞의 값이 삭제되었을 때 자신이 최솟값이 될 수 있음
			while (!q.isEmpty() && q.peekLast() > n) {
				q.pollLast();
			}
			q.offerLast(n);

			// front값이 본인과 동일하면 삭제
			if (i >= L) {
				if (arr[front++] == q.peekFirst())
					q.pollFirst();
			}

			sb.append(q.peekFirst()).append(" ");
		}

		System.out.println(sb);

	}

}
