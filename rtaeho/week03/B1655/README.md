### 📌 문제 정보

- **번호:** 1655
- **제목:** 가운데를 말해요
- **난이도:** Gold 2
- **분류:** 자료구조, 우선순위 큐(힙)

---

### 💡 접근 방식

> 수를 하나씩 입력받을 때마다 중앙값을 출력하는 문제입니다.  
> 최대 힙과 최소 힙 두 개를 유지하면서 중앙값을 관리합니다.

---

### 🔹 1단계 – 두 힙의 역할

```
maxHeap | minHeap
--------|--------
  3  2  |  5  7
↑
중앙값은 항상 maxHeap의 top
```

- **maxHeap** — 중앙값 이하의 수들 (최대 힙)
- **minHeap** — 중앙값 초과의 수들 (최소 힙)
- 중앙값은 항상 `maxHeap.peek()`

---

### 🔹 2단계 – 크기 균형 유지

```java
if (maxHeap.size() == minHeap.size()) {
    maxHeap.add(num);
} else {
    minHeap.add(num);
}
```

- 두 힙 크기가 같으면 → maxHeap에 추가 (중앙값 갱신)
- 크기가 다르면 → minHeap에 추가
- 항상 `maxHeap.size() == minHeap.size() + 1` 또는 `== minHeap.size()` 유지

---

### 🔹 3단계 – 대소 관계 정렬

```java
if (maxHeap.peek() > minHeap.peek()) {
    maxHeap.add(minHeap.poll());
    minHeap.add(maxHeap.poll());
}
```

maxHeap의 최댓값이 minHeap의 최솟값보다 크면 두 값을 교환  
→ 항상 `maxHeap의 모든 수 ≤ minHeap의 모든 수` 보장

---

### 💻 핵심 코드

```java
for (int i = 0; i < N; i++) {
    int num = Integer.parseInt(br.readLine());

    if (maxHeap.size() == minHeap.size()) {
        maxHeap.add(num);
    } else {
        minHeap.add(num);
    }

    if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
        maxHeap.add(minHeap.poll());
        minHeap.add(maxHeap.poll());
    }

    sb.append(maxHeap.peek()).append("\n");
}
```

---

### ⏳ 복잡도 분석

- **시간 복잡도:** `O(N×logN)`
    - 힙 삽입/삭제가 O(logN), N번 반복
- **공간 복잡도:** `O(N)`