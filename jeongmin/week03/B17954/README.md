# BOJ 17954 - 투튜브

## 📌 문제 설명

deque 2개에서 숫자를 꺼내는데 꺼낼 수 있는 숫자 중 가장 작은 숫자를 꺼낸다.  
이 규칙을 만족하면서 최대한 큰 숫자들을 먼저 꺼내는 문제

## 💡 해결 아이디어

1. 꺼내는 곳이 4개면 제일 큰 숫자 3개는 꺼낼 수가 없다.
2. 꺼낼 수 있는 곳 숫자를 빨리 줄여야겠다.
3. 제일 큰 숫자 3개를 빼고 나머지 숫자들중에 큰 순서대로 꺼내야겠네.
4. 한줄을 먼저 다 꺼내서 꺼내는 곳을 줄이면 큰 숫자들을 꺼낼 수 있겠군.

## 🧠 코드 해설

```java
		if (n == 1) {
			sb.append(2).append("\n").append(1).append("\n").append(2);
			System.out.println(sb);
			return;
		}
```

내가 짠 로직이 1일때 제대로 작동을 안하기 때문에 1일 경우 예외처리

```java
		long sum = 0;
		for (int i = 1; i <= 2*n; i++) {
			sum += i;
		}
```

사과들의 총합 구하기

```java
		long answer = 0;
		for (int t = 1; t < 2*n; t++) {
			if (t < n) {
				sum -= 2*n-2-t;
				sb.append(2*n-2-t).append(" ");
			}
			else if (t == n) {
				sum -= 2*n-2;
				sb.append(2*n-2).append("\n");
			}
			else if (t == n+1) {
				sum -= 2*n-1;
				sb.append(2*n-1).append(" ");
			}
			else {
				sum -= 2*n-t;
				sb.append(2*n-t).append(" ");
			}
			
			answer += sum*t;
		}
```

사과를 빼는 순서를 앞에서부터 순서대로 설계해서 빼는대로 StringBuilder에 append  
사과를 빼고 남은 총합에 t를 곱해서 answer에 누적하기

## 🚀 느낀점

long이 밉다...  
아니 그리고 eclipse console에 출력제한이 있다는 사실 알고 계셨나요?  
n을 9257 이상 값을 넣으면 2번째줄, 3번째줄이 너무 길어서 eclipse 출력제한에 걸려서 출력이 이상하게 나온답니다.  
저는 이게 overflow 난줄 알고 BigInteger쓰고 막 별 짓을 다 했는데 아무런 문제가 없던 거였어요..허허......;;