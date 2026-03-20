# BOJ 1655 - 가운데를 말해요

## 📌 문제 설명

숫자를 n개 주는데 숫자를 줄때마다 오름차순 정렬을 해서 가운데에 있는 값을 출력하는 문제

## 💡 해결 아이디어

1. 중앙값과 중앙값보다 작은 수, 중앙값보다 큰 수 이렇게 3개로 나눠서 관리해야겠다.
2. 중앙값보다 작은 숫자 중 중앙값 후보는 가장 큰 수일 거고 중앙값보다 큰 숫자 중 중앙값 후보는 가장 작은 숫자겠다. => pq
3. 다음 숫자가 중앙값보다 클때랑 작을때로 케이스 나눠서 하면 되겠군.
4. 숫자가 짝수개일 경우도 고려해야겠다.


## 🧠 코드 해설

```java
		PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> higher = new PriorityQueue<>();
		
		int mid = Integer.parseInt(br.readLine());
```

중앙값을 기준으로 작은 숫자랑 큰 숫자를 관리할 pq

```java
			if (input >= mid) {
				higher.add(input);
				if (i%2 != 0) {
					lower.add(mid);
					mid = higher.poll();
				}
```

input이 중앙값보다 클 경우  
숫자가 짝수개일 경우 higher에 add하고 끝  
숫자가 홀수개일 경우 중앙값을 lower에 add하고 higher에서 새로운 중앙값을 poll

```java
			} else {
				lower.add(input);
				if (i%2 == 0) {
					higher.add(mid);
					mid = lower.poll();
				}
			}
```

input이 중앙값보다 작을 경우  
숫자가 홀수개일 경우 lower에 add하고 끝  
숫자가 짝수개일 경우 중앙값을 higher에 add하고 lower에서 새로운 중앙값을 poll

## 🚀 느낀점

SWEA에서 똑같은 문제를 풀었어서 금방 풀었다.  
이거 다 같이 풀었던 문제 같은데..