package week03.B17954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		// 1인 경우 따로 처리
		if (n == 1) {
			System.out.println(2);
			System.out.println(1);
			System.out.println(2);
			return;
		}

		long appleSum = 0;
		for (int i = 1; i <= 2 * n; i++) {
			appleSum += i;
		}

		// 사과 배치 생성
		ArrayDeque<Integer> first = new ArrayDeque<>();
		ArrayDeque<Integer> second = new ArrayDeque<>();

		for (int i = 2 * n - 3; i >= n - 1; i--) {
			first.offer(i);
			sb.append(i).append(" ");
		}
		first.offer(2 * n - 2);
		sb.append(2 * n - 2).append("\n");

		second.offer(2 * n - 1);
		sb.append(2 * n - 1).append(" ");
		for (int i = n - 2; i >= 1; i--) {
			second.offer(i);
			sb.append(i).append(" ");
		}
		second.offer(2 * n);
		sb.append(2 * n).append("\n");

		// 부패도 계산
		long sum = 0;
		int top = 0;
		for (int i = 0; i < 2 * n; i++) {
			sum += i * appleSum;
			if (i < n) {
				top = first.pollFirst();
			} else {
				top = second.pollFirst();
			}
			appleSum -= top;
		}

		System.out.println(sum);
		System.out.println(sb);
	}
}
