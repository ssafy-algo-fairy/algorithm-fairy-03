# BOJ 17842 - 버스 노선

## 📌 문제 설명

n개의 정류장과 n-1개의 정류장 쌍이 주어질 때 정류장들을 잇는 버스 노선의 필요 개수를 구하시오

## 💡 해결 아이디어

1. 정류장 쌍 개수가 n-1개다 => 모든 정류장을 이어야 하기 때문에 cyclic이 없겠구나?
2. 하나의 노선이 두개의 끝점을 갈 수 있으니까 끝점의 개수만 세면 되겠다.


## 🧠 코드 해설

```java
		for (int i = 0; i < n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			child[a].add(b);
			child[b].add(a);
		}
```

연결된 정류장들을 기록

```java
		int answer = 0;
		for (int i = 0; i < n; i++) {
			if (child[i].size() == 1) {
				answer++;
			}
		}
		
		answer = (int) Math.round(answer/2.0);
```

연결된 정류장이 1개인 정류장(끝점)의 개수를 세고 그 개수를 2로 나눈다.  
만약 홀수면 올림처리

## 🚀 느낀점

처음엔 어떻게 푸나 했는데 생각보다 간단하게 풀린다.  
정류장 쌍의 개수가 n-1인게 문제의 핵심인거 같다.  
의재님이 쉽다 한 이유를 알거 같다.  
근데 어제그제가 어려웠으니 하루쯤 쉬워도 되지 않나..