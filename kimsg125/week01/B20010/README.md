# 🏰 BOJ 20010 - 악덕 영주 혜유

- 🔗 문제 링크: https://www.acmicpc.net/problem/20010  
- 🏷️ 분류: 최소 스패닝 트리(MST) / 크루스칼(Union-Find) / 트리 지름(DFS·BFS)

---

## 💡 접근 아이디어 / 시행착오
- Union-find를 이용한 MST를 만드는 Kruskal 알고리즘으로 풀었다
- 가장 큰 경로의 비용을 찾기 위해 MST에 사용된 간선들로 탐색을 해야하는데 아무 생각 없이 2차원 배열에 비용을 저장했다가 시간 초과가 나왔다
- 간선은 1000개를 넘지 않을 것이므로 연결된 간선만 탐색하면 시간 초과가 날 수 없다

---

## 🛠️ 구현 포인트
```
static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.exp - o2.exp);

...

pq.offer(new Edge(a, b, c));
```
- 우선순위큐에 낮은 비용 순서대로 저장

```
while (!pq.isEmpty()) {
	union(pq.poll());
}

...

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
```
- 비용이 낮은 간선부터 union-find
- 부모 노드가 다르면 expSum에 비용을 더하고 해당 간선 정보을 저장

```
for (int i = 0; i < N; i++) {
	Arrays.fill(visited, false);
	dfs(i, 0);
}

...

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
```
- 각 노드에 대해서 dfs로 비용이 가장 큰 경로 탐색

---

## 📝 배운 점
- 솔직히 예쁘게 푼 것 같진 않고, 다른 사람 코드 보면서 배워야겠다

---
