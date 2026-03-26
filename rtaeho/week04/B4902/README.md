### 📌 문제 정보

- **번호:** 4902
- **제목:** 삼각형의 최대 합
- **난이도:** Gold 1
- **분류:** 다이나믹 프로그래밍(DP), 누적 합

---

### 💡 접근 방식

> N행 삼각형에서 계단 모양으로 선택한 원소들의 합의 최댓값을 구하는 문제입니다.  
> 각 행의 누적합을 1차원 배열에 저장해두고,  
> 홀수 인덱스(아래 방향)와 짝수 인덱스(위 방향) 두 가지 방향으로 탐색합니다.

---

### 🔹 1단계 – 누적합 저장

각 행의 원소를 1차원 배열에 순차적으로 누적합으로 저장
```java
int prevCnt = (int) Math.pow(i - 1, 2);  // i-1행까지 원소 개수
dp[prevCnt + j] = dp[prevCnt + j - 1] + num;
```

i행의 시작 인덱스는 `(i-1)²`이므로  
특정 구간의 합을 O(1)에 계산 가능
```
1행: dp[1]
2행: dp[2] ~ dp[4]
3행: dp[5] ~ dp[9]
```

---

### 🔹 2단계 – 정방향 탐색 (홀수 인덱스, 아래 방향)
```java
if (j % 2 == 1) {
    for (int k = 0; i + k <= N; k++) {
        int targetPrevNum = (int) Math.pow((i + k - 1), 2);
        currentSum += (dp[targetPrevNum + j + 2 * k] - dp[targetPrevNum + j - 1]);
        maxSum = Math.max(maxSum, currentSum);
    }
}
```

`(i, j)`에서 시작해서 아래로 내려가며 선택  
k행 내려갈수록 오른쪽으로 `2*k`씩 이동

---

### 🔹 3단계 – 역방향 탐색 (짝수 인덱스, 위 방향)
```java
else {
    for (int k = 0; i - k >= 1; k++) {
        if (j - k * 2 < 1 || j > (i - k) * 2 - 1) break;

        int targetPrevNum = (int) Math.pow((i - k - 1), 2);
        currentSum += (dp[targetPrevNum + j] - dp[targetPrevNum + j - k * 2 - 1]);
        maxSum = Math.max(maxSum, currentSum);
    }
}
```

`(i, j)`에서 시작해서 위로 올라가며 선택  
k행 올라갈수록 왼쪽으로 `2*k`씩 이동  
범위 벗어나면 즉시 종료

---

### 💻 핵심 코드
```java
// i행 j열에서 정방향으로 k행 내려갔을 때 해당 구간 합
currentSum += (dp[targetPrevNum + j + 2 * k] - dp[targetPrevNum + j - 1]);

// i행 j열에서 역방향으로 k행 올라갔을 때 해당 구간 합
currentSum += (dp[targetPrevNum + j] - dp[targetPrevNum + j - k * 2 - 1]);
```

---

### ⏳ 복잡도 분석

- **시간 복잡도:** `O(N³)`
    - 모든 시작점 O(N²) × 각 방향 탐색 O(N)
- **공간 복잡도:** `O(N²)`
    - 누적합 배열 크기가 N²

---

### ⚠️ 어려웠던 점

- 역방향을 생각 못 해서 헷갈렸습니다.
- 정민님 말 듣고 누적합으로 해봤습니다.