### 📌 문제 정보

- **번호:** 4991
- **제목:** 로봇 청소기
- **난이도:** Gold 1
- **분류:** 그래프 탐색, BFS, 비트마스킹, 다이나믹 프로그래밍(DP)

---

### 💡 접근 방식

> 더러운 칸을 모두 청소하는 최소 이동 횟수를 구하는 문제입니다.  
> 더러운 칸이 최대 10개이므로, 시작점과 더러운 칸들 사이의 최단거리를 BFS로 미리 구해두고  
> 비트마스크 DP로 모든 더러운 칸을 청소하는 최소 이동 횟수를 계산합니다.

---

### 🔹 1단계 – 중요 지점 수집

`o`를 `points[0]`으로, `*`들을 순서대로 `points[1~N]`에 저장
```java
if (map[i][j] == 'o') points.add(0, new int[]{i, j});
if (map[i][j] == '*') points.add(new int[]{i, j});
```

---

### 🔹 2단계 – BFS로 지점 간 최단거리 계산

각 중요 지점에서 BFS를 한 번씩 돌려서 다른 지점까지의 최단거리를 계산
```java
for (int i = 0; i < points.size(); i++) {
    times = bfs(points.get(i)[0], points.get(i)[1]);
    for (int j = 0; j < points.size(); j++) {
        dist[i][j] = times[points.get(j)[0]][points.get(j)[1]];
    }
}
```

`times` 배열이 `visited` 역할을 겸함 (`-1` = 미방문)

---

### 🔹 3단계 – 비트마스크 DP
```
dp[i][mask] = points[i+1]번 더러운 칸에 있고,
              mask 상태만큼 청소했을 때 최소 이동 횟수
```

**초기화** — `o`에서 각 더러운 칸으로 첫 출발
```java
for (int i = 0; i < trashCnt; i++) {
    dp[i][1 << i] = dist[0][i + 1];
}
```

**전이** — 현재 위치에서 아직 안 청소한 칸으로 이동
```java
int next = mask | (1 << j);
dp[j][next] = Math.min(dp[j][next], dp[i][mask] + dist[i + 1][j + 1]);
```

`dist`에서 더러운 칸이 1번부터 시작하므로 `i+1`, `j+1`로 접근

---

### 🔹 4단계 – 정답 도출

모든 칸을 청소한 상태 `(1<<N)-1`에서 최솟값
```java
for (int i = 0; i < trashCnt; i++) {
    minTime = Math.min(minTime, dp[i][(1 << trashCnt) - 1]);
}
```

---

### 💻 핵심 코드
```java
for (int mask = 1; mask < (1 << trashCnt); mask++) {
    for (int i = 0; i < trashCnt; i++) {
        if (dp[i][mask] == Integer.MAX_VALUE) continue;

        for (int j = 0; j < trashCnt; j++) {
            if ((mask & (1 << j)) != 0) continue;
            if (dist[i + 1][j + 1] == -1) continue;

            int next = mask | (1 << j);
            dp[j][next] = Math.min(dp[j][next], dp[i][mask] + dist[i + 1][j + 1]);
        }
    }
}
```

---

### ⏳ 복잡도 분석

- **시간 복잡도:** `O(지점 수 × H × W + N² × 2^N)`
    - BFS: 중요 지점마다 한 번씩 → `O(지점 수 × H × W)`
    - DP: `O(N² × 2^N)` (N ≤ 10이므로 충분)
- **공간 복잡도:** `O(H × W + N × 2^N)`

---

### ⚠️ 어려웠던 점
- DP 어려워요
- 비트마스킹 어려워요
- `addFirst()`가 Java 21부터 지원되는 메서드라 백준에서 런타임 에러가 나서 `add(0, element)`로 수정했습니다.