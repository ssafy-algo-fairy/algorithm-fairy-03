# BOJ 9251 - LCS

## 📌 문제 설명

LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.

예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.

## 💡 해결 아이디어

1. 두 수열을 계속 번갈아가면서 봐야하네.. 오래 걸리겠는데?
2. DP로 기록해놔야겠다.


## 🧠 코드 해설

```java
		int[][] dp = new int[a.length][b.length];
```

dp 생성

```java
		for (int i = 0; i < a.length; i++) {
			int max = 0;
			for (int j = 0; j < b.length; j++) {
				max = i == 0 || j == 0 ? 0 : Math.max(max, dp[i-1][j-1]);
				
				if (i != 0 && dp[i-1][j] != 0) dp[i][j] = dp[i-1][j];
				if (a[i] == b[j]) dp[i][j] = Math.max(max+1, dp[i][j]);
			}
		}
```

i: a 수열을 어디까지 확인했는가?  
j: b 수열을 어디까지 확인했는가?  
이전 dp에서 가장 큰 값을 확인하고 만약 이번 확인한 char가 일치할 경우 이전 dp에서 가장 큰 값에 1을 더해서 저장한다.

```java
		int answer = -1;
		for (int j = 0; j < b.length; j++) {
			answer = Math.max(answer, dp[a.length-1][j]);
		}
```

a 수열을 다 확인했을 때 나올 수 있는 가장 큰 값을 찾는다.

## 🚀 느낀점

dp인거 알면 쉬운거 같다.  
자소서 기간이라고 쉬운 문제 가져온 의재님에게 감사를..