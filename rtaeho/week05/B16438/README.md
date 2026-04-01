### 📌 문제 정보

- **번호:** 16438
- **제목:** 치킨 도시락
- **난이도:** Gold 3
- **분류:** 분할 정복, 재귀

---

### 💡 접근 방식

> N명이 7일 동안 매일 A조 또는 B조로 나뉠 때, 모든 쌍이 반드시 한 번은 다른 조가 되도록 배치하는 문제입니다.  
> 분할 정복으로 매일 절반씩 A/B로 나누면 최대 2^7 = 128명까지 모든 쌍을 커버할 수 있습니다.

---

### 🔹 1단계 – 분할 정복 배치

매일 현재 범위를 절반으로 나눠서 왼쪽은 A, 오른쪽은 B로 배치
```java
int mid = (start + end) / 2;

for (int i = start; i < mid; i++) arr[day][i] = 'A';
for (int i = mid; i < end; i++) arr[day][i] = 'B';

solve(day + 1, start, mid);  // 왼쪽 절반 재귀
solve(day + 1, mid, end);    // 오른쪽 절반 재귀
```

---

### 🔹 2단계 – 왜 분할 정복이 유효한가
```
1일차: [A A A A | B B B B]
2일차: [A A | B B | A A | B B]
3일차: [A|B|A|B|A|B|A|B]
```

어떤 두 사람을 골라도 반드시 한 번은 다른 조에 배치됨  
→ 처음으로 서로 다른 그룹에 속하게 되는 날이 반드시 존재

---

### 🔹 3단계 – A, B 누락 보정

범위가 1인 경우 한 쪽만 배치될 수 있으므로 보정 처리
```java
if (!hasA) arr[i][0] = 'A';
if (!hasB) arr[i][0] = 'B';
```

---

### 💻 핵심 코드
```java
static void solve(int day, int start, int end) {
    if (day == 7) return;

    int mid = (start + end) / 2;

    for (int i = start; i < mid; i++) arr[day][i] = 'A';
    for (int i = mid; i < end; i++) arr[day][i] = 'B';

    solve(day + 1, start, mid);
    solve(day + 1, mid, end);
}
```

---

### ⏳ 복잡도 분석

- **시간 복잡도:** `O(N × 7)`
    - 7일 동안 N명 배치
- **공간 복잡도:** `O(7 × N)`
    - 배치 결과 저장 배열

---

### ⚠️ 어려웠던 점

- A랑 B 중 하나도 배치되지 않은 경우를 따로 고려해야 했습니다.