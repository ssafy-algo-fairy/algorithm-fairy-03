import java.io.*;
import java.util.*;

public class boj17842 {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int answer = 0;
		int[] nums = new int[N];
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			nums[a]++;
			nums[b]++;
		}

		for (int i = 0; i < N; i++) {
			if (nums[i] == 1)
				answer++;
		}

		System.out.println((answer + 1) / 2);
	}

}
