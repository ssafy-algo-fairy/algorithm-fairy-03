package week03.B1655;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> higher = new PriorityQueue<>();
		
		int mid = Integer.parseInt(br.readLine());
		sb.append(mid).append("\n");
		
		for (int i = 2; i <= n; i++) {
			int input = Integer.parseInt(br.readLine());
			
			if (input >= mid) {
				higher.add(input);
				if (i%2 != 0) {
					lower.add(mid);
					mid = higher.poll();
				}
			} else {
				lower.add(input);
				if (i%2 == 0) {
					higher.add(mid);
					mid = lower.poll();
				}
			}
			
			sb.append(mid).append("\n");
		}
		
		System.out.println(sb);
	}
}
