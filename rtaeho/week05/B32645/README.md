### 📌 문제 정보

- **번호:** 32645
- **제목:** 트리 게임
- **난이도:** Gold 3
- **분류:** 트리, 게임 이론, DFS

---

### 💡 접근 방식

> 트리에서 두 플레이어가 번갈아가며 간선을 제거하는 게임에서 승패를 구하는 문제입니다.  
> 현재 노드의 자식 중 하나라도 패배 상태(0)가 있으면 현재 노드는 승리 상태(1)입니다.  
> 처음에는 트리가 방향 그래프인 줄 알고 풀었다가, 양방향으로 다시 풀었습니다.

---

### 🔹 1단계 – 승패 정의
```
dp[i] = 0  →  i번 노드에서 현재 플레이어가 지는 상태 (uppercut)
dp[i] = 1  →  i번 노드에서 현재 플레이어가 이기는 상태 (donggggas)
```

---

### 🔹 2단계 – 리프 노드 처리

리프 노드는 더 이상 이동할 수 없으므로 무조건 패배
```java
if (isLeaf) {
    return dp[cur] = 0;
}
```

---

### 🔹 3단계 – 내부 노드 처리

자식 중 하나라도 패배 상태(0)면 → 그쪽으로 이동하면 이기므로 승리  
모든 자식이 승리 상태(1)면 → 어디로 가도 상대가 이기므로 패배
```java
for (int next : graph.get(cur)) {
    if (next == p) continue;

    if (solve(next, cur) == 0) {
        canWin = true;  // 자식 중 패배 상태 존재
    }
}

return dp[cur] = canWin ? 1 : 0;
```

---

### 💻 핵심 코드
```java
static int solve(int cur, int p) {
    boolean canWin = false;
    boolean isLeaf = true;

    for (int next : graph.get(cur)) {
        if (next == p) continue;

        isLeaf = false;
        if (solve(next, cur) == 0) {
            canWin = true;
        }
    }

    if (isLeaf || !canWin) {
        return dp[cur] = 0;
    } else {
        return dp[cur] = 1;
    }
}
```

---

### ⏳ 복잡도 분석

- **시간 복잡도:** `O(N)`
    - 모든 노드를 한 번씩 방문
- **공간 복잡도:** `O(N)`
    - 그래프, dp 배열

---

### ⚠️ 어려웠던 점

- 처음에 트리를 방향 그래프로 잘못 이해해서 단방향으로 풀었다가 양방향으로 다시 풀었습니다.
- 부모 노드로 역방향 탐색을 막기 위해 `parent` 를 넘겨줘야 했습니다.
- 리프 노드와 모든 자식이 승리하는 경우 둘 다 패배라서 `isLeaf || !canWin` 으로 묶었습니다.