# 🎮 BOJ 32645 - 동까뚱뽭 게임

- 🔗 문제 링크: https://www.acmicpc.net/problem/32645  
- 🏷️ 분류: 게임 이론 / DP

---

## 💡 접근 아이디어 / 시행착오
- 동우는 리프 노드에서 패배이고, 리프 노드를 자식 노드로 가지고 있는 노드에서는 승리한다
- 그런 식으로 자식 노드 중 패배하는 노드가 있으면 그 노드에서는 승리한다
- 아래에서부터 위로 업데이트하는 dp로 접근

---

## 🛠️ 구현 포인트
```
win = new boolean[N + 1];
dfs(1, 0);

...

static boolean dfs(int cur, int parent) {
    boolean canWin = false;

    for (int next : graph[cur]) {
        if (next == parent) continue;

        boolean childWin = dfs(next, cur);
        if (!childWin) {
            canWin = true;
        }
    }

    win[cur] = canWin;
    return win[cur];
}
```
- dfs를 사용해 리프 노드까지 접근하고 리프 노드는 false로 저장
- 자식 노드 중 false가 있으면 true로 저장

---

## 💭 느낀 점
- 약간 베스킨라빈스31 같은 게임
