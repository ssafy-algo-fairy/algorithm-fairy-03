# 전구(회로 연결) 복원하기 풀이 발표

---



## 🌳 문제 핵심

> 타일(격자칸) 안의 전선을 회전시켜, 
> **좌상단 출발점(0, 0)**에서 **우하단 도착점(N, M)**까지 연결하는 최소 회전 횟수를 구하는 문제입니다. 
> 핵심은 칸 자체가 아닌 **'칸의 꼭짓점(교차점)'을 노드(Node)로 생각**해야 한다는 점입니다.

---

## 💡 핵심 알고리즘 : 다익스트라 (Dijkstra) / 0-1 BFS

```text
도달 가능성 판별 = (N + M)이 홀수면 대각선 이동 특성상 절대 도달 불가 (NO SOLUTION)
  └─ 출발점(0, 0)을 PriorityQueue에 넣고 탐색 시작 (비용 0)
      └─ 인접한 4방향 대각선 꼭짓점 탐색
           ├─ 해당 방향으로 가기 위한 타일의 전선 방향 일치 -> 비용 +0
           ├─ 전선 방향 불일치(회전 필요) -> 비용 +1
           └─ 누적 비용(cost)이 더 적은 경우에만 갱신 후 Queue에 추가
```

```java
while(!pq.isEmpty()){
    Node now = pq.poll();
    if(now.cost > cost[now.y][now.x]) continue;

    for(int i=0; i<4; i++){
        int ny = now.y + dy[i];
        int nx = now.x + dx[i];
        if(ny < 0 || ny > N || nx < 0 || nx > M) continue;

        // 현재 꼭짓점에서 다음 꼭짓점으로 갈 때 확인해야 하는 실제 타일의 좌표
        int targetY = now.y + correctionY[i];
        int targetX = now.x + correctionX[i];

        // 타일의 모양이 가고자 하는 방향과 같으면 비용 0, 다르면 1(회전)
        int nextCost = arr[targetY][targetX] == targetShape[i] ? 0 : 1;
        
        if(cost[ny][nx] <= now.cost + nextCost) continue;

        cost[ny][nx] = now.cost + nextCost;
        pq.add(new Node(ny, nx, cost[ny][nx]));
    }
}
```

| 항목 | 내용 |
| --- | --- |
| **탐색 방식** | 다익스트라 (우선순위 큐 활용) |
| **노드 기준** | 격자칸(N*M)이 아닌 **꼭짓점((N+1)*(M+1))** |
| **비용 계산** | 전선 모양 일치 여부 (일치=0, 불일치=1) |
| **도달 불가 판별** | `(N+M) % 2 == 1` 인 경우 도착점에 도달 불가 |

<br/>

### 🔑 Key Point:
격자 칸의 좌표와 꼭짓점의 좌표가 다르다는 까다로운 점을 `correctionY`, `correctionX` 배열을 활용해 깔끔하게 해결한 점이 훌륭합니다. 현재 꼭짓점(Vertex)에서 대각선 꼭짓점으로 이동할 때 어떤 타일(Tile)을 거쳐가는지 정확히 매핑했습니다. 

### 🎯 결론 및 배울 점
* **그래프 모델링의 발상 전환:** 타일이 아닌 **교차점**을 노드로 삼는 아이디어가 돋보이는 문제입니다.
* **효율적인 최단 거리 탐색:** 가중치가 0과 1로만 이루어진 그래프에서 `PriorityQueue`를 활용하여 누적 회전 횟수가 적은 경로를 먼저 탐색함으로써, 최소 비용(최소 회전수)을 정확하게 찾아냈습니다. (이러한 가중치 특성을 이용해 `Deque`를 활용한 0-1 BFS로 최적화할 수도 있습니다.)