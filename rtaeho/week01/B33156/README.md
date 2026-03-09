### 📌 문제 정보

- **번호:** 33156  
- **제목:** 구간이 이븐하지 않아요.  
- **난이도:** Gold 2
- **분류:** 브루트포스 최적화, 해시맵, 투포인터/중심 확장

---

### 💡 접근 방식

> 두 수열이 서로 이븐하다는 것은, 결국 **원소의 순서는 상관없고 각 값의 개수만 같으면 된다**는 뜻입니다.  
> 따라서 어떤 구간을 절반으로 나눈 두 부분 수열이 이븐한지 확인하려면, 양쪽 절반의 **값별 등장 횟수 차이**를 관리하면 됩니다.

---

### 🔹 1단계 – 짝수 길이 구간만 확인

- 구간을 절반으로 나누어야 하므로 길이는 반드시 짝수여야 함
- `mid | mid+1` 를 기준으로 왼쪽과 오른쪽을 한 칸씩 동시에 확장

---

### 🔹 2단계 – 개수 차이 저장

- `map[x] = (왼쪽 절반에서 x의 개수) - (오른쪽 절반에서 x의 개수)`
- 왼쪽에 원소가 추가되면 `+1`
- 오른쪽에 원소가 추가되면 `-1`
- 이렇게 하면 두 절반이 완전히 같을 때는 모든 값의 차이가 0이 됨

---

### 🔹 3단계 – notZeroCnt로 빠르게 판별

- 현재 차이가 0이 아닌 값의 개수 `notZeroCnt`를 활용

- 차이가 `0 → 1` 또는 `0 → -1` 이 되면 `notZeroCnt++`
- 차이가 `1 → 0` 또는 `-1 → 0` 이 되면 `notZeroCnt--`

---

### 💻 핵심 로직

#### 중심을 기준으로 양쪽 확장

```java
for (int mid = 0; mid < N - 1; mid++) {
    map = new HashMap<>();
    int notZeroCnt = 0;
    int left = mid;
    int right = mid + 1;

    while (left >= 0 && right < N) {
        notZeroCnt = check(arr[left], arr[right], notZeroCnt);

        if (notZeroCnt == 0) {
            cnt = Math.max(cnt, right - left + 1);
        }

        left--;
        right++;
    }
}
```

#### 왼쪽/오른쪽 값 차이 갱신

```java
static int check(int leftNum, int rightNum, int notZeroCnt) {
    int prevDiff = map.getOrDefault(leftNum, 0);
    int curDiff = prevDiff + 1;

    if (prevDiff == 0) {
        notZeroCnt++;
    } else if (curDiff == 0) {
        notZeroCnt--;
    }

    if (curDiff == 0) map.remove(leftNum);
    else map.put(leftNum, curDiff);

    prevDiff = map.getOrDefault(rightNum, 0);
    curDiff = prevDiff - 1;

    if (prevDiff == 0) {
        notZeroCnt++;
    } else if (curDiff == 0) {
        notZeroCnt--;
    }

    if (curDiff == 0) map.remove(rightNum);
    else map.put(rightNum, curDiff);

    return notZeroCnt;
}
```

---

### ⏳ 복잡도 분석

- **시간 복잡도:** `O(N^2)`
    - 각 중심마다 양쪽으로 최대 `O(N)`번 확장
    - 중심의 개수는 `O(N)`

- **공간 복잡도:** `O(N)`
    - 각 중심마다 사용하는 `HashMap`

---

### ⚠️ 어려웠던 점

- 처음에는 시작과 끝 구간을 잡고 양쪽 절반의 개수를 매번 셌는데, `O(N^3)`이라 시간 초과가 발생했습니다.
- 그래서 중심을 기준으로 양쪽을 확장하면서 이전 상태를 저장해두고 비교하는 방법으로 진행했습니다.
- `map`에 왼쪽과 오른쪽의 숫자의 개수 차이**를 저장하고, 현재 양쪽의 차이를 저장해두는 `notZeroCnt`를 활용하였습니다.
- AI와 함께 했습니다...
