# BOJ 11003 - 최솟값 찾기

## 📌 문제 설명

slide window에서 최솟값 찾기

## 💡 해결 아이디어

1. window에서 새롭게 들어온 값보다 큰 값들은 절대로 최솟값이 될 수 없다.
2. 새로운 값을 넣을 때마다 넣은 값보다 큰 값들은 제거
3. 자연스럽게 값이 오름차순으로 정렬이 된다.
4. 뒤에서부터 넣으려는 숫자보다 큰 값은 제거, 앞에서 최솟값 가져오기 => Deque
4. index를 저장해놓고 최솟값의 index가 window 밖이면 제거하기


## 🧠 코드 해설

```java
int[] a = {i, Integer.parseInt(st.nextToken())};
```

a[0] = index  
a[1] = value

```java
			while (!q.isEmpty()) {
				if (q.peekLast()[1] > a[1]) {
					q.pollLast();
				} else break;
			}
```

q에 존재하는 값들을 뒤에서부터 보면서 새로 넣으려는 값보다 큰 경우(절대로 최솟값이 될 수 없는 경우)를 제거

```java
			if (q.peek()[0] == i-l) q.poll();
			sb.append(q.peek()[1]).append(" ");
```

최솟값의 index가 window 범위 바깥일 경우 제거  
오름차순으로 정렬된 q에서 최솟값 추출

## 🚀 느낀점

처음에는 PriorityQueue로 접근했다가 시간초과가 나서 고민을 조금 했는데 전에 비슷한 문제를 풀어봐서 좀 쉽게 푼 문제였던 것 같다.  
  
Deque에서 index 순서대로 넣되 값을 오름차순으로만 남기는 이 과정이 아름다운 것 같다.  
  
아 그리고 Deque나 Queue같은 Collections 구현할 때 LinkedList를 항상 썼는데 ArrayDeque가 훨씬 빠르다는 것을 배웠다.(Shout out to 류태호)