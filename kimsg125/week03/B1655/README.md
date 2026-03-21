# 📊 BOJ 1655 - 가운데를 말해요

- 🔗 문제 링크: https://www.acmicpc.net/problem/1655  
- 🏷️ 분류: 우선순위 큐(Priority Queue) / 두 힙(Heap)

---

## 💡 접근 아이디어 / 시행착오
- 가운데 값을 기준으로 왼쪽 오른쪽을 우선순위 큐로 관리하면 되지 않을까

---

## 🛠️ 구현 포인트
```
static PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
static PriorityQueue<Integer> right = new PriorityQueue<>((o1, o2) -> o1 - o2);
```
- 왼쪽 큐는 내림차순, 오른쪽 큐는 오름차순

```
mid = Integer.parseInt(br.readLine());
sb.append(mid).append("\n");
```
- 처음 숫자만 바로 mid에 저장

```
for (int i = 1; i < N; i++) {
	int num = Integer.parseInt(br.readLine());

	if (num > mid) {
		if (left.size() >= right.size())
			right.offer(num);
		else {
			left.offer(mid);
			right.offer(num);
			mid = right.poll();
		}
	} else {
		if (left.size() < right.size())
			left.offer(num);
		else {
			right.offer(mid);
			left.offer(num);
			mid = left.poll();
		}
	}

	sb.append(mid).append("\n");
}
```
- 두 번째 숫자부터 mid와 비교하고 왼쪽, 오른쪽 큐 크기를 비교하여 저장

---

## 📝 느낀 점
- 생각한대로 결과가 잘 나와서 바로 풀었다
