import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int[] num;
	static int len;
	static int max_len;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		num = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		len = n - (n % 2);

		mainLoop:
		while (len > 0) {
			int start = 0;
			Map<Integer, Integer> m1 = new HashMap<>();
			Map<Integer, Integer> m2 = new HashMap<>();
			for (int i = 0; i < len / 2; i++) {
				m1.merge(num[i], 1, (old, v) -> old + v);
			}
			for (int i = len / 2; i < len; i++) {
				m2.merge(num[i], 1, (old, v) -> old + v);
			}
			if (m1.equals(m2))
				break;
			while (start + len < n) {
				m1.compute(num[start], (k, v) -> v == null || v == 1 ? null : v - 1);
				m1.merge(num[start + len / 2], 1, (old, v) -> old + v);
				m2.compute(num[start + (len / 2)], (k, v) -> v == null || v == 1 ? null : v - 1);
				m2.merge(num[start + len], 1, (old, v) -> old + v);
				if (m1.equals(m2))
					break mainLoop;
				start++;
			}
			len -= 2;
		}
		System.out.println(len);
	}
}