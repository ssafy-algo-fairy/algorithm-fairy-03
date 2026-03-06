# BOJ 1937 - 욕심쟁이 판다

## 📌 문제 설명

n*n 크기의 map에서 오름차순으로만 이동할 때 이동할 수 있는 최대 칸의 개수를 구하는 문제

## 💡 해결 아이디어

1. 오름차순으로만 이동할 수 있으니 이전에 왔던 곳으로 돌아갈 수 없다. => visited가 필요없음
2. 어디서 왔든 그 칸에서 갈 수 있는 칸들의 최대 개수는 항상 같다. => dp로 저장해놓기

## 🧠 코드 해설

```java
		dp = new int[n][n];	
		
		for (int i = 0; i < n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.fill(dp[i], -1);
		}
```

그 칸에서 갈 수 있는 칸들의 최대 개수를 저장해놓는 dp

```java
	public static int dfs(int x, int y) {
		if (dp[x][y] != -1) return dp[x][y];
		
		int max = 1;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			
			if (map[x][y] >= map[nx][ny]) continue;
			
			max = Math.max(max, 1 + dfs(nx, ny));
		}
		
		return dp[x][y] = max;
	}
```

dp != -1일 경우(dp에 저장된 값이 있을 경우) dp를 return  
최소 지금 칸 1칸은 갈 수 있으므로 max를 1로 설정  
이동할 수 있는 칸이 있을 경우 지금 칸(1) + 그 칸에서 이동할 수 있는 칸의 개수(dfs)를 더한 값을 구하고 4방향에 대해서 그 값의 최댓값을 구한다.  
만약 4방향 모두 갈 수 없을 경우 max=1  
이 값을 dp에 저장하고 return

## 🚀 느낀점

어디서 왔든 상관없이 그 칸에서 갈 수 있는 칸들의 최대 개수는 항상 일정하다는 것이 이 문제 해결의 핵심 원리인 것 같다.