### 📌 문제 정보

- **번호:** 20010  
- **제목:** 악덕 영주 혜유  
- **난이도:** Gold 2  
- **분류:** MST, 크루스칼, Union-Find, 트리 지름, DFS

---

### 💡 접근 방식

> 문제에서 먼저 모든 정점을 **최소 비용으로 연결**해야 하므로,  
> 원래 그래프에서 MST를 만들어야 합니다.  
> 이후 만들어진 MST는 트리 구조이므로, 그 안에서 가장 긴 경로인 **트리의 지름**을 구했습니다.

---

### 🔹 1단계 – 간선 입력 및 정렬

- 간선을 `arr[i][0] = u`, `arr[i][1] = v`, `arr[i][2] = w` 형태로 저장
- 크루스칼 알고리즘을 적용하기 위해 **가중치 기준 오름차순 정렬**

```java
Arrays.sort(arr, (a, b) -> a[2] - b[2]);
```

---

### 🔹 2단계 – 유니온 파인드 준비

- 크루스칼에서 사이클 여부를 빠르게 판단하기 위해 유니온 파인드 활용
- `find()`로 대표 부모를 찾고
- `union()`으로 서로 다른 집합이면 합침

```java
static int find(int x) {
    if (parent[x] == x) return x;
    return parent[x] = find(parent[x]);
}
```

```java
static boolean union(int a, int b) {
    a = find(a);
    b = find(b);

    if (a == b) return false;

    parent[b] = a;
    return true;
}
```

---

### 🔹 3단계 – 크루스칼로 MST 만들기

- 정렬된 간선을 앞에서부터 확인
- `union(u, v)`가 가능하면 MST에 포함
- MST 총 가중치 `sum`에 더하고,
- MST 전용 그래프 `graph`에 양방향으로 저장

```java
if (union(u, v)) {
    edgeCnt++;
    sum += w;

    graph[u].add(new int[]{v, w});
    graph[v].add(new int[]{u, w});
}
```

- MST는 정점이 `N`개일 때 간선이 `N-1`개이므로, `edgeCnt == N-1`이 되면 종료

---

### 🔹 4단계 – MST의 지름 구하기

- MST는 트리이므로, 트리의 지름은 **DFS 두 번**으로 구할 수 있음

1. 아무 정점(0번)에서 DFS → 가장 먼 정점 `farNode` 찾기
2. `farNode`에서 다시 DFS → 가장 먼 거리 `maxDist` 구하기

```java
dfs(0, -1, 0);

maxDist = 0;
dfs(farNode, -1, 0);
```

---

### 💻 핵심 로직

#### 크루스칼

```java
for (int i = 0; i < K; i++) {
    int u = arr[i][0];
    int v = arr[i][1];
    int w = arr[i][2];

    if (union(u, v)) {
        edgeCnt++;
        sum += w;

        graph[u].add(new int[]{v, w});
        graph[v].add(new int[]{u, w});

        if (edgeCnt == N - 1) break;
    }
}
```

#### DFS로 지름 구하기

```java
static void dfs(int now, int prev, int dist) {
    if (dist > maxDist) {
        maxDist = dist;
        farNode = now;
    }

    for (int[] next : graph[now]) {
        int nextNode = next[0];
        int weight = next[1];

        if (nextNode == prev) continue;

        dfs(nextNode, now, dist + weight);
    }
}
```

---

### ⏳ 복잡도 분석

- **시간 복잡도:** `O(K log K + N)`
    - 간선 정렬: `O(K log K)`
    - Union-Find를 포함한 크루스칼: 거의 `O(K)`
    - DFS 두 번: `O(N)`

- **공간 복잡도:** `O(N + K)`
    - 간선 배열, parent 배열, MST 그래프 저장

---

### ⚠️ 구현 포인트

- 입력받은 간선을 바로 그래프에 넣는 것이 아니라, **MST에 포함된 간선만 graph에 저장**해야 함
- 크루스칼에서는 사이클 방지를 위해 Union-Find가 필요함
- MST는 트리이므로, 지름은 DFS 두 번으로 구할 수 있음

---

### ⚠️ 어려웠던 점

- MST 문제는 풀어도 풀어도 헷갈리고 어렵습니다.
- 정렬하는 방법도 매번 헷갈립니다.