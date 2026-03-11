# BOJ 2610 - 회의준비

## 📌 문제 설명

서로 알고 있는 사람은 반드시 같은 위원회에 속해야 한다.  
효율적인 회의 진행을 위해 위원회의 수는 최대가 되어야 한다.  
  
이런 방식으로 위원회를 구성한 후에 각 위원회의 대표를 한 명씩 뽑아야 한다.  
대표를 뽑을 때에는 가장 먼 사람의 거리가 제일 작은 사람으로 정한다.

## 💡 해결 아이디어

1. union-find로 위원회 분리하기
2. 각 위원회에서 사람별로 가장 먼 사람의 거리 구하기(n이 작다 => 완전탐색)
3. 가장 먼 사람의 거리가 제일 짧은 사람이 대표


## 🧠 코드 해설

```java
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		p[a] = b;
	}
	
	public static int find(int a) {
		if (a == p[a]) return a;
		
		return p[a] = find(p[a]);
	}
```

union-find 기본구현

```java
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a, b);
			e[a].add(b);
			e[b].add(a);
		}
```

input을 union-find 방식으로 위원회 구성  
edge들은 나중에 거리를 구할 때 사용해야 하기에 따로 저장(양방향 잊지 않기)

```java
		for (int i = 1; i <= n; i++) {
			if (p[i] == i) {
				count++;
				group.add(i);
			}
		}
```

위원회 개수를 세고 위원회를 대표할 인물 선정(이 대표자는 무작위로 선정되었기 때문에 수정해야함)

```java
		for (int g : group) {
			int min = Integer.MAX_VALUE;
			int index = -1;
			
			for (int i = 1; i <= n; i++) {
				if (find(i) != g) continue;
				
				int max = bfs(i);
				
				if (max < min) {
					min = max;
					index = i;
				}
			}
			
			head.add(index);
		}
```

위원회 별로 각 구성원의 제일 먼 사람의 거리를 측정하고 그 중 최솟값을 찾으면 그 사람을 대표자로 선정한다.

```java
	public static int bfs(int a) {
		boolean[] visited = new boolean[n+1];
		visited[a] = true;
		
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {a, 0});
		
		while (!q.isEmpty()) {
			int[] input = q.poll();
			
			for (int next : e[input[0]]) {
				if (visited[next]) continue;
				
				visited[next] = true;
				q.add(new int[] {next, input[1]+1});
			}
			
			if (q.isEmpty()) return input[1];
		}
		
		return Integer.MAX_VALUE;
	}
```

거리 측정을 위한 bfs 구현

## 🚀 느낀점

n이 100밖에 안되서 그냥 무식하게 해봤더니 됐다.  
  
문제 수준이 아쉬울 따름이다...