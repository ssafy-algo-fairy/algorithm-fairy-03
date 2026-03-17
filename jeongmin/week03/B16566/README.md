# BOJ 16566 - 카드 게임

## 📌 문제 설명

1~n까지의 m개의 숫자 중에 k개의 입력 중 그거보다 크면서 가장 작은 숫자를 출력하는 문제(한번 출력하면 다시 사용 x)

## 💡 해결 아이디어

1. 이거 완전 ceiling(higher)아니야? => TreeSet
2. 시간초과나네.. 그냥 2중 for문 돌릴까
3. 또 시간초과나네...
4. 이게 union-find라고?(Shout out to 류태호)

## 🧠 코드 해설

```java
		p = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int a = Integer.parseInt(st.nextToken());
			p[a] = a;
		}
		
		for (int i = n-1; i > 0; i--) {
			if (p[i] == 0) p[i] = p[i+1];
		}
```

union-find를 위한 p 배열 생성  
m개의 카드들만 p[a] = a로 설정한 후 p 배열에 보유하지 않은 카드들은 보유한 카드 중 가장 작은 카드의 숫자를 집어넣음

```java
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			sb.append(find(Integer.parseInt(st.nextToken())+1)).append("\n");
		}
```

k개의 숫자들로 find 함수 돌리기  
크면서 가장 작은 숫자이니 +1이 포인트

```java
	public static int find(int a) {
		if (p[a] == a) {
			p[a] = p[a+1];
			return a;
		}
		
		return p[a] = find(p[a]);
	}
```

기존 union-find와 다른 점이라 하면 사용한 경우 배열 숫자 날려주기

## 🚀 느낀점

아니 어떻게 이 문제를 보고 union-find를 떠올리지..변탠가?