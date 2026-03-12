package week02.B17842;

import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	static List<Integer>[] child;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		child = new LinkedList[n];
		
		for (int i = 0; i < n; i++) {
			child[i] = new LinkedList<>();		
		}
		
		for (int i = 0; i < n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			child[a].add(b);
			child[b].add(a);
		}
		
		int answer = 0;
		for (int i = 0; i < n; i++) {
			if (child[i].size() == 1) {
				answer++;
			}
		}
		
		answer = (int) Math.round(answer/2.0);

		System.out.println(answer);
	}
}
