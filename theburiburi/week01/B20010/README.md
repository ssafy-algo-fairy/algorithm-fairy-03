# 🔍 최소 스패닝 트리와 트리의 지름 풀이 발표

---

## 🌳 문제 핵심

> $N$개의 정점과 $K$개의 간선 정보가 주어질 때, 모든 정점을 연결하는 **최소 스패닝 트리(MST)**를 구축하고, 그 트리에서 **가장 먼 두 정점 사이의 거리(트리의 지름)**를 구하는 문제입니다.
> 1. **Kruskal 알고리즘**: 간선들을 가중치 기준으로 정렬한 뒤, Union-Find를 통해 사이클 없이 $N-1$개의 간선을 선택하여 MST의 총 가중치를 구합니다.
> 2. **트리의 지름 (DFS)**: 임의의 노드에서 가장 먼 노드를 찾고, 그 노드에서 다시 가장 먼 노드까지의 거리를 측정하여 트리의 최대 경로를 구합니다.

---

## 💡 핵심 알고리즘 : Kruskal & Tree Diameter (DFS)



```text
알고리즘 흐름 = MST 구축 후 트리 내 최장 경로 탐색
 └─ 1. MST 구축 (Kruskal)
     ├─ 간선 리스트(sl)를 가중치(value) 오름차순으로 정렬
     ├─ Union-Find를 사용하여 사이클이 발생하지 않는 간선만 선택
     └─ 선택된 간선의 가중치 합(total) 계산 및 인접 리스트 생성
 └─ 2. 트리의 지름 구하기 (2-Phase DFS)
     ├─ 1차 DFS: 임의의 노드(0번)에서 가장 먼 노드(farNode)를 탐색
     └─ 2차 DFS: 찾아낸 farNode에서 다시 가장 먼 노드까지의 거리(maxMove) 계산
```

```
// 1. Kruskal을 이용한 MST 구축 및 인접 리스트 구성
for (int i = 0; i < K; i++) {
    SortNode now = sl.get(i);
    if (find(now.left) != find(now.right)) { // 사이클 체크
        union(now.left, now.right, now.value);
        // 트리의 지름을 구하기 위해 MST에 포함된 간선들로 인접 리스트 생성
        list[now.left].add(new Node(now.right, now.value));
        list[now.right].add(new Node(now.left, now.value));
        if (++count == N - 1) break;
    }
}

// 2. 트리의 지름 구하기 (두 번의 DFS)
maxMove = 0;
visited = new boolean[N];
dfs(0, 0); // 1차: 가장 멀리 있는 노드(farNode) 찾기

maxMove = 0;
visited = new boolean[N];
dfs(farNode, 0); // 2차: farNode로부터의 최대 거리(지름) 계산
```


항목,내용
MST 알고리즘,Kruskal (Union-Find 활용)
지름 탐색 방식,DFS (Depth First Search) 2회 수행
시간 복잡도,O(KlogK) (간선 정렬이 지배적)
공간 복잡도,O(N+K) (인접 리스트 및 간선 리스트 저장)


🔑 Key Point: 단순히 MST의 총합만 구하는 것이 아니라, 선택된 간선들로 새로운 트리 구조(인접 리스트)를 재구성하는 것이 핵심입니다. 트리의 지름을 구할 때 사용하는 2번의 DFS 방식은 모든 노드 사이의 거리를 구할 필요 없이 $O(V+E)$만에 최장 거리를 보장하는 매우 효율적인 기법입니다.

🎯 결론 및 배울 점

복합 알고리즘의 결합: MST와 트리의 지름이라는 두 가지 독립된 개념을 하나의 문제에서 유기적으로 연결하여 해결하는 능력을 배양했습니다.

Union-Find의 효율성: 경로 압축(Path Compression)을 적용한 find 연산을 통해 MST 구축 시 성능을 최적화하였습니다.

트리 특성의 이해: 일반 그래프와 달리 사이클이 없는 '트리' 구조에서만 성립하는 지름 구하기 공식을 실전 코드로 구현하며 자료구조에 대한 이해도를 높였습니다.