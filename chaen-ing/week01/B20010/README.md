## **🔍 문제 요약**

- 마을 사이에 교역로 건설하기
- 교역로는 양방향 이동 가능
- 서로 이동 불가능한 마을이 없도록 교역로 건설하기
- 이때 “최소 비용”, “마을 간 이동하는 비용이 가장 큰 경로의 비용”을 구하자
    - 최소 비용 방법은 유일

- 1 ≤ N 마을 수 ≤ 1,000
- 1 ≤ K 설치가능한 교역로  ≤ 1,000,000
    - 중복이 있나? 1000C2 보다 큰데
- 1 ≤ c 마을간에 연결 비용 ≤ 1,000,000
- 시간제한 0.5초

---

## **💡문제 접근 / 풀이 전략**

- 모든 마을이 서로 접근 가능해야하고 총 비용 최소 → MST(최소 신장 트리)를 만들어야함

<aside>
💡

**MST (최소 신장 트리)**

- **모든** 정점을 연결하되, 사이클 없고, 간선 비용 합이 **최소인** 트리

MST를 만드는 두가지 방법

1. **크루스칼** : 간선 중심
    - 가중치 오름차순 정렬
    - 가장 싼 간선부터보고 사이클 안생기면 채택
    - 간선이 N-1개 생기면 종료
    - 보통 사이클 체크는 유니온 파인드
2. **프림** : 정점 중심
    - 아무 정점 선택
    - 현재 정점에서 나가는 간선 중 가장 싼 것 선택
    - 보통 우선순위 큐 사용
</aside>

- 마을 간 이동하는 비용이 가장 큰 경로의 비용 : 트리의 지름
    - 트리는 사이클이 없으므로 막대기처럼 펼쳐놓는다고 생각하면 1 - 2 - 3 - 4- 5
    - 2에서 가장 먼 곳 5에서 가장 먼 곳 1
    - 임의의 정점에서 가장 먼 정점 A를 찾고, 그 정점에서 가장 먼 정점 B를 찾으면 |A - B|가 지름

---

## **✅ 코드 & 소요 시간**

```java
package week01.B20010;

import java.io.*;
import java.util.*;

public class Main {
	static int N, K, sum, farNode, maxDist;
	static Edge[] village;
	static ArrayList<Node>[] tree;
	static int[] parent;
	static boolean [] visited;

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
		for(int i = 0 ; i < N; i++){
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
		dfs(0, 0);	// 0번에서 가장 먼 정점

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

			if (union(from, to)) {	// 사이클 체크
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

		if (from == to) return false;
		parent[to] = from;
		return true;
	}

	static int find(int x){
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

	// 트리의 지름
	static void dfs(int node, int dist){
		if(dist > maxDist){
			maxDist = dist;
			farNode = node;
		}

		for(Node next : tree[node]){
			if(visited[next.to])	continue;
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

class Node{
	int to, cost;

	Node(int to, int cost){
		this.to = to;
		this.cost = cost;
	}
}
```

94212/1112

---

## **✍️ 회고**

- 구현할게 너무 많아서 어려웟다;;
- 크루스칼 알고리즘 기억하자.. 유니온 파인드도……

---