package week03.B1655;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> first = new PriorityQueue<>(Collections.reverseOrder());    // 내림차순
		PriorityQueue<Integer> second = new PriorityQueue<>();    // 오름차순

		int tmp, res;
		for (int i = 0; i < N; i++) {
			tmp = Integer.parseInt(br.readLine());

			// 첫번째 입력 처리
			if (first.isEmpty()) {
				first.offer(tmp);
				sb.append(tmp).append("\n");
				continue;
			}

			if (!second.isEmpty() && tmp > second.peek())
				second.offer(tmp);
			else
				first.offer(tmp);

			// 개수 조정 : 항상 first가 더 많게
			if (first.size() > second.size() + 1)
				second.offer(first.poll());
			else if (second.size() > first.size())
				first.offer(second.poll());

			if (i % 2 == 1)
				res = Math.min(first.peek(), second.peek());
			else
				res = first.peek();

			sb.append(res).append("\n");
		}

		System.out.println(sb);
	}
}
