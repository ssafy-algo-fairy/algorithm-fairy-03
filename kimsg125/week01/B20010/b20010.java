package com.ssafy.algo.fairy.m3.week01.B20010;

import java.util.*;
import java.io.*;

public class b20010 {

	static class Edge {
		int u, v, exp;

		public Edge(int u, int v, int exp) {
			this.u = u;
			this.v = v;
			this.exp = exp;
		}

		public Edge(int next, int exp) {
			this.u = next;
			this.exp = exp;
		}
	}

	static class Node {
		List<Edge> next = new ArrayList<>();
	}

	static int N, K, expSum, maxExp;

	static int[] root;
	static boolean[] visited;

	static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.exp - o2.exp);
	static List<Node> nodeList = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(a, b, c));
		}

		expSum = 0;
		root = new int[N];
		for (int i = 0; i < N; i++) {
			root[i] = i;
			nodeList.add(new Node());
		}

		while (!pq.isEmpty()) {
			union(pq.poll());
		}
		System.out.println(expSum);

		maxExp = 0;
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited, false);
			dfs(i, 0);
		}
		System.out.println(maxExp);

	}

	static void union(Edge e) {
		int ru = find(e.u);
		int rv = find(e.v);

		if (ru == rv)
			return;

		expSum += e.exp;
		root[rv] = ru;
		nodeList.get(e.u).next.add(new Edge(e.v, e.exp));
		nodeList.get(e.v).next.add(new Edge(e.u, e.exp));
	}

	static int find(int u) {
		if (root[u] == u)
			return u;

		return root[u] = find(root[u]);
	}

	static void dfs(int u, int sum) {
		maxExp = Math.max(maxExp, sum);

		visited[u] = true;
		for (Edge e : nodeList.get(u).next) {
			if (!visited[e.u]) {
				dfs(e.u, sum + e.exp);
			}
		}
		visited[u] = false;
	}

}
