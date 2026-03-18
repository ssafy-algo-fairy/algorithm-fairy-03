### 📌 문제 정보

- **번호:** 16566
- **제목:** 카드 게임
- **난이도:** Gold 1
- **분류:** 자료구조, 분리 집합(Union-Find)

---

### 💡 접근 방식

> 민수의 카드보다 큰 철수의 카드 중 가장 작은 카드를 찾는 문제입니다.  
> 단순 반복문 → TreeSet → Union-Find 순으로 최적화했습니다.

---

### 🔹 1단계 – 단순 반복문 O(K×N)

민수 카드보다 큰 첫 번째 철수 카드를 순차 탐색

```java
for (int j = card + 1; j <= N; j++) {
    if (cards[j] == 1) {
        sb.append(j).append("\n");
        cards[j] = 0;
        break;
    }
}
```

N이 최대 4,000,000이라 시간초과

---

### 🔹 2단계 – TreeSet O(K×logN)

TreeSet은 내부적으로 레드-블랙 트리로 구현되어 있어서  
삽입/삭제/탐색이 전부 O(logN)

```java
TreeSet<Integer> cards = new TreeSet<>();
int result = cards.higher(card);  // card보다 큰 첫 번째 값
cards.remove(result);             // 사용한 카드 제거
```

`higher()`가 이분탐색과 동일하게 동작해서 O(logN)  
하지만 Integer 객체 기반이라 메모리 접근 비용이 커서 시간초과

---

### 🔹 3단계 – Union-Find O(K×α(N)) ≈ O(1)

**핵심 아이디어**

카드를 제거하는 대신 "다음 카드로 가라"고 포인터만 변경

```
철수 카드 있는 칸 → parent[i] = i
철수 카드 없는 칸 → parent[i] = i + 1  (다음 칸으로 바로 넘김)
```

카드 사용 후 `parent[result] = result + 1` 로 설정하면  
다음에 같은 카드를 찾을 때 `find()`가 자동으로 다음 카드로 이동

**경로 압축**으로 한 번 탐색한 경로는 다음에 바로 점프

```java
static int find(int x) {
    while (parent[x] != x) {
        parent[x] = parent[parent[x]];  // 경로 압축
        x = parent[x];
    }
    return x;
}
```

---

### 💻 핵심 코드

```java
// 카드 없는 칸은 다음으로 넘기기
for (int i = 1; i <= N; i++) {
    if (cards[i] == 0) parent[i] = i + 1;
}

// 민수 카드 처리
int nextCard = find(card + 1);  // card보다 큰 첫 번째 철수 카드
parent[nextCard] = nextCard + 1;  // 사용했으니 다음 카드로 넘김
```

---

### ⏳ 복잡도 분석

| 방법 | 시간 복잡도 | 결과 |
|------|------------|------|
| 반복문 | O(K×N) | 시간초과 |
| TreeSet | O(K×logN) | 시간초과 |
| Union-Find | O(K×α(N)) | 통과 |

- **공간 복잡도:** `O(N)`

---

### ⚠️ 어려웠던 점

- 단순 반복으로 했을 때 시간초과가 나서, 이분탐색을 적용하려고 TreeSet을 썼는데 여전히 시간초과가 났습니다.
- Union-Find를 통해 해결했습니다.