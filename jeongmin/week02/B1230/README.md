# BOJ 1230 - 문자열 거리

## 📌 문제 설명

문자열 O에서 문자열 N까지 문자열 거리는 O를 N과 같게 만들기 위해 필요한 문자열 삽입의 최솟값이다. 문자열 삽입은 O의 어느 위치에서건 가능하다. 예를 들어, O가 “gosrl"일 때, ”sip gi"을 r이전에 삽입한다면 "gossip girl“이 된다.

## 💡 해결 아이디어

1. 그냥 greedy로 하면 되는거 아닌가?
2. 아 항상 최대한 앞에서 매치하면 최솟값 보장이 안되네
3. 완전탐색 해볼까?
4. 시간초과 나네...
5. 문제 분류가 dp네?
6. 문자열 n의 j까지 문자열 o의 char i개를 찾았을 때 삽입해야 하는 문자열의 개수를 dp[i][j]로 해야겠다.


## 🧠 코드 해설

```java
		for (int i = 0; i < o.length; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		
		for (int j = 0; j < n.length; j++) {
			if (n[j] == o[0]) dp[0][j] = j==0? 0 : 1;
		}
```

dp를 MAX_VALUE로 채워놓기  
i가 0일 때, 즉 문자열 o의 첫번째 char 위치를 탐색하고 dp에 저장

```java
				if (dp[i-1][j-1] != Integer.MAX_VALUE && dp[i-1][j-1] <= min) {
					min = dp[i-1][j-1];
					index = j-1;
				}
```

i-1까지의 문자열 o를 가장 적은 문자열 삽입으로 문자열 j-1까지 만들었을 때의 문자열 삽입 횟수를 기록 

```java
				if (o[i] == n[j]) {
					if (index == -1) continue;
					dp[i][j] = index == j-1? min : min+1;
				}
```

i번째 문자열을 찾았을 경우 기록된 삽입 횟수를 이용하여 문자열 삽입 횟수를 구하기  
만약 바로 전과 이어질 경우 문자열 삽입이 필요없으므로 그전 값을 그대로 가져오고 아니면 문자열 삽입을 1회 해야하므로 +1

```java
		int min = Integer.MAX_VALUE;
		
		for (int j = 0; j < n.length; j++) {
			if (dp[o.length-1][j] < min) {
				min = j==n.length-1? dp[o.length-1][j] : dp[o.length-1][j]+1;
			}
		}
```

문자열 o의 모든 값을 넣어서 만든 문자열 n중 문자열 삽입의 최솟값 찾기

## 🚀 느낀점

그리디, 완전탐색, dp 똑같은 문제를 3번이나 풀었더니 머리가 너무 아프다.  
카테고리가 dp인 것을 안봤더라면 아직도 못 풀었을 것...