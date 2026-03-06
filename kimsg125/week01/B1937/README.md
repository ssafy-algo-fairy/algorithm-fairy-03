# 🐼 BOJ 1937 - 욕심쟁이 판다

- 🔗 문제 링크: https://www.acmicpc.net/problem/1937  
- 🏷️ 분류: DFS + DP(메모이제이션) / 그래프 DP

---

## 💡 접근 아이디어 / 시행착오
- 모든 시작점에서 bfs로 탐색하고 dp에 저장해서 탐색 시간을 줄이자
- bfs로 하니까 메모리 초과가 나온다
- 그럼 dfs로 하면서 dp를 업데이트하자

---

## 🛠️ 구현 포인트
```
for (int i = 0; i < N; i++) {
	for (int j = 0; j < N; j++) {
		ans = Math.max(ans, dfs(i, j));
	}
}
```
모든 시작점에서 dfs

```
if (dp[x][y] != 0)
	return dp[x][y];
```
탐색이 완료된 곳은 리턴

```
dp[x][y] = 1;
for (int i = 0; i < 4; i++) {
	int nx = x + dx[i], ny = y + dy[i];
	if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1 || Map[nx][ny] <= Map[x][y])
		continue;

	dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
}
```
최소 1, (주변 탐색한 값+1)이 더 크면 업데이트

---

## 📝 배운 점
- 너무 bfs를 편애한다
- dfs도 좋아해보자

---