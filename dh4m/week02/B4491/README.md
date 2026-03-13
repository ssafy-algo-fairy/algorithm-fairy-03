# 📝 백준 4991 - 로봇 청소기

## 1. 문제 요약

* **목표**: 직사각형 구역 내에 흩어진 모든 더러운 칸(`*`)을 로봇 청소기(`o`)가 방문하여 깨끗하게 만드는 최소 이동 횟수를 구합니다.
* **제한 사항**:
* 가구(`x`)가 있는 칸은 통과할 수 없습니다.
* 더러운 칸의 개수는 최대 10개이며, 구역의 크기는 최대 $20 \times 20$입니다.
* 모든 더러운 칸을 방문할 수 없는 경우 -1을 출력합니다.



---

## 2. 사용 로직 및 핵심 알고리즘

이 문제는 **BFS(Breadth-First Search)**를 이용한 최단 거리 측정과 **TSP(Traveling Salesperson Problem)**를 해결하기 위한 **비트마스킹 DP(Dynamic Programming)**를 결합하여 해결하였습니다.

1. **BFS (최단 거리 전처리)**:
* 로봇의 시작 위치와 각 더러운 칸 사이, 그리고 더러운 칸들 상호 간의 최단 거리를 BFS를 통해 미리 계산합니다.
* `dist(i, j)` 함수를 통해 노드 간 가중치를 산출하여 `cost[][]` 배열에 저장합니다.


2. **비트마스킹 DP (순회 최적화)**:
* 방문해야 할 지점(더러운 칸)이 최대 10개이므로, 현재 위치와 방문한 지점들의 상태를 비트로 표현($2^{11}$)하여 메모이제이션을 수행합니다.
* `tsp(node, visit)` 함수는 현재 `node`에 있고 `visit` 상태의 칸들을 청소했을 때, 남은 칸들을 모두 청소하는 최소 비용을 반환합니다.



---

## 3. 코드 하이라이트

### BFS를 이용한 노드 간 거리 계산

```java
static int dist(int i, int j) {
    if (i == j) return 0;
    if (cost[i][j] > 0) return cost[i][j]; // 메모이제이션 활용
    
    int len = 1;
    Queue<Vertex> q = new ArrayDeque<>();
    boolean[][] visit = new boolean[h][w];
    q.add(new Vertex(coor[i][0], coor[i][1]));
    
    while (!q.isEmpty()) {
        int qsize = q.size();
        for (int r = 0; r < qsize; r++) {
            Vertex now = q.poll();
            for (int[] d : delta) {
                int di = now.i + d[0];
                int dj = now.j + d[1];
                if (di == coor[j][0] && dj == coor[j][1]) {
                    cost[i][j] = cost[j][i] = len;
                    return len;
                }
                if (isValid(di, dj) && map[di][dj] != -1 && !visit[di][dj]) {
                    visit[di][dj] = true;
                    q.add(new Vertex(di, dj));
                }
            }
        }
        len++;
    }
    return -1; // 도달 불가능한 경우
}

```

### 비트마스킹을 활용한 TSP 구현

```java
static int tsp(int node, int visit) {
    // 모든 더러운 칸을 방문한 경우 종료 (1 << (dest + 1)) - 1
    if (visit == (1 << (dest + 1)) - 1) return 0;
    if (dp[node][visit] != 0) return dp[node][visit];
    
    int result = Integer.MAX_VALUE;
    for (int i = 1; i <= dest; i++) {
        if (((1 << i) & visit) == 0) { // 아직 방문하지 않은 더러운 칸 확인
            int distance = dist(node, i);
            if (distance == -1) return -1;
            
            int tsp_val = tsp(i, visit | (1 << i));
            if (tsp_val != -1) {
                result = Math.min(result, distance + tsp_val);
            }
        }
    }
    return dp[node][visit] = (result == Integer.MAX_VALUE) ? -1 : result;
}

```

---

## 4. 시간 복잡도 분석

* **BFS 단계**: 노드(시작점 + 더러운 칸)의 개수를 $K$라고 할 때, 최대 $K(K-1)/2$번의 BFS가 수행됩니다. 각 BFS는 $O(W \times H)$이므로 전처리 복잡도는 $O(K^2 \times W \times H)$입니다.
* **DP 단계**: 상태의 수는 $O(K \times 2^K)$이며, 각 상태에서 다음 노드를 찾는 데 $O(K)$가 소요됩니다. 따라서 $O(K^2 \times 2^K)$입니다.
* **최종 복잡도**: $O(K^2(WH + 2^K))$ ($K \le 11, W, H \le 20$). 이는 주어진 시간 제한 내에 충분히 수행 가능합니다.

---

## 5. 학습 포인트 및 개선 사항

* **학습 포인트**: 단순히 BFS로 모든 경로를 탐색하는 것이 아니라, 지점 간 거리를 먼저 구한 뒤 TSP 문제로 환원하여 풀이하는 전략을 익혔습니다.
* **개선 사항**:
* 현재 코드에서는 `dist` 함수 내에서 매번 `new boolean[h][w]`를 생성합니다. 전역 배열을 선언하고 `Arrays.fill`을 사용하거나 방문 체크 방식을 최적화하면 가비지 컬렉션 부하를 줄일 수 있습니다.
* TSP 수행 전, 시작점(`o`)에서 모든 더러운 칸(`*`)으로의 도달 가능 여부를 먼저 체크하여 불가능할 경우 조기에 -1을 반환하도록 설계하면 효율적입니다.

