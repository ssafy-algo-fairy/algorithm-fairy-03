package week01.B20010;

import java.io.*;
import java.util.*;

public class Main {
	static int N, K, sum, farNode, maxDist;
	static Edge[] village;
	static ArrayList<Node>[] tree;
	static int[] parent;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		village = new Edge[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			village[i] = new Edge(
				Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
			);
		}
		Arrays.sort(village);    // 가격순으로 정렬

		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		tree = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}

		sum = 0;
		kruskal();    // 크루스칼 알고리즘으로 MST 만들기
		System.out.println(sum);

		// 비용이 가장 큰 경로의 비용
		visited = new boolean[N];
		farNode = -1;
		maxDist = -1;
		visited[0] = true;
		dfs(0, 0);    // 0번에서 가장 먼 정점

		visited = new boolean[N];
		maxDist = -1;
		visited[farNode] = true;
		dfs(farNode, 0);

		System.out.println(maxDist);
	}

	static void kruskal() {
		int cnt = 0;

		for (int i = 0; i < K; i++) {
			int from = village[i].from;
			int to = village[i].to;
			int cost = village[i].cost;

			if (union(from, to)) {    // 사이클 체크
				sum += cost;
				cnt++;
				tree[from].add(new Node(to, cost));
				tree[to].add(new Node(from, cost));
			}

			if (cnt == N - 1)
				break;
		}
	}

	static boolean union(int from, int to) {
		from = find(from);
		to = find(to);

		if (from == to)
			return false;
		parent[to] = from;
		return true;
	}

	static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	// 트리의 지름
	static void dfs(int node, int dist) {
		if (dist > maxDist) {
			maxDist = dist;
			farNode = node;
		}

		for (Node next : tree[node]) {
			if (visited[next.to])
				continue;
			visited[next.to] = true;
			dfs(next.to, dist + next.cost);
		}
	}

}

class Edge implements Comparable<Edge> {
	int from;
	int to;
	int cost;

	Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

class Node {
	int to, cost;

	Node(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}
}