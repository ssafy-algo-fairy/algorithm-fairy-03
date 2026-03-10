package week02.B2610;

import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	static int[] p;
	static LinkedList<Integer>[] e;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		p = new int[n+1];
		e = new LinkedList[n+1];
		
		for (int i = 1; i <= n; i++) {
			p[i] = i;
			e[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a, b);
			e[a].add(b);
			e[b].add(a);
		}	
		
		int count = 0;
		LinkedList<Integer> group = new LinkedList<>();
		
		for (int i = 1; i <= n; i++) {
			if (p[i] == i) {
				count++;
				group.add(i);
			}
		}
		
		sb.append(count).append("\n");
		
		LinkedList<Integer> head = new LinkedList<>();
		
		for (int g : group) {
			int min = Integer.MAX_VALUE;
			int index = -1;
			
			for (int i = 1; i <= n; i++) {
				if (find(i) != g) continue;
				
				int max = bfs(i);
				
				if (max < min) {
					min = max;
					index = i;
				}
			}
			
			head.add(index);
		}
		
		Collections.sort(head);
		for (int a : head) {
			sb.append(a).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		p[a] = b;
	}
	
	public static int find(int a) {
		if (a == p[a]) return a;
		
		return p[a] = find(p[a]);
	}
	
	public static int bfs(int a) {
		boolean[] visited = new boolean[n+1];
		visited[a] = true;
		
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {a, 0});
		
		while (!q.isEmpty()) {
			int[] input = q.poll();
			
			for (int next : e[input[0]]) {
				if (visited[next]) continue;
				
				visited[next] = true;
				q.add(new int[] {next, input[1]+1});
			}
			
			if (q.isEmpty()) return input[1];
		}
		
		return Integer.MAX_VALUE;
	}

}
