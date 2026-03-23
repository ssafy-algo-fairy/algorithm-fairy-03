# BOJ 4902 - 삼각형의 값

## 📌 문제 설명

단위 삼각형을 붙여서 만든 삼각형에서 찾을 수 있는 삼각형 중에 그 삼각형에 포함된 단위 삼각형의 숫자들의 총 합이 가장 큰 삼각형 찾기

## 💡 해결 아이디어

1. n이 400..? 단위 삼각형 16만개... 다 해도 되겠는데? => 완전탐색


## 🧠 코드 해설

```java
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j < 2*i; j++) {
					triangle[i][j] = Integer.parseInt(st.nextToken()) + triangle[i][j-1];
				}
			}
```

삼각형을 누적합으로 저장(Shout out to 조의재)

```java
	public static void run(int i, int j) {
		int sum = triangle[i][j] - triangle[i][j-1];
		max = Math.max(sum, max);
		
		boolean up = false;
		if (j%2 == 0) up = true;
		
		for (int c = 1; c < n; c++) {
			int x = up? i-c : i+c;
			int lower = up? j-2*c : j;
			int upper = up? j : j+2*c;
			
			if (lower <= 0 || upper >= 2*x || x <= 0 || x > n) return;
			
			sum += triangle[x][upper] - triangle[x][lower-1];
			
			max = Math.max(sum, max);
		}		
	}
```

삼각형을 만드는 함수  
j가 짝수면 위의 방향(up = true), 홀수면 아래방향(up = false)  
up에 따라서 x값과 lower, upper 계산  
x, lower, upper 중에 무엇인가가 범위를 벗어나면 return  
누적합 이용해서 sum 계산 후 max 갱신

## 🚀 느낀점

처음에 누적합 안쓰고 그냥 돌렸는데 통과는 됐는데 시간이 2112ms가 나왔다.  
의재님 코드 보니까 480ms길래 어떻게 했나 하고 봤더니 누적합(!)을 사용한 것을 봤다.  
누적합으로 코드를 개선하니 716ms까지 개선할 수 있었다.  
대 의 재