# ⚖️ 구간이 이븐하지 않아요 (33156) 풀이 발표

---

## 🌳 문제 핵심

> "수열 $s$와 $t$를 원하는 만큼 위치를 바꿔서 같게 만들 수 있다"는 것은 두 수열의 **구성 원소와 개수가 동일(Anagram)**하다는 의미입니다.
> 
> 수열의 길이 $N$이 최대 `5,000`이므로, 모든 부분 수열을 추출하여 정렬하는 $O(N^3 \log N)$ 방식은 시간 초과가 발생합니다. 
> 따라서 **특정 경계선을 기준으로 양옆으로 확장하며 원소의 빈도 차이를 실시간으로 추적하는 $O(N^2)$ 접근**이 필요합니다.

---

## 💡 핵심 알고리즘: 중심 확장 & 빈도 차이 추적 (diffCount)

### 🖥️ 핵심 코드 (solve)
```java
static void solve() {
    // 1. 모든 가능한 경계선(mid)을 설정 (왼쪽 구간의 마지막 인덱스)
    for (int mid = 0; mid < N - 1; mid++) {
        Map<Integer, Integer> map = new HashMap<>();
        int diffCnt = 0;

        // 2. offset을 늘려가며 담장 양옆으로 한 칸씩 확장 (Symmetric Expansion)
        for (int offset = 0; 0 <= mid - offset && mid + 1 + offset < N; offset++) {
            
            // [왼쪽 원소 처리] 빈도가 0에서 변하면 diffCnt 증가, 다시 0이 되면 감소
            int leftVal = arr[mid - offset];
            int leftCnt = map.getOrDefault(leftVal, 0);
            if (leftCnt == 0) diffCnt++;
            map.put(leftVal, leftCnt + 1);
            if (map.get(leftVal) == 0) diffCnt--;

            // [오른쪽 원소 처리] 반대로 깎으면서 왼쪽과 균형이 맞는지 확인
            int rightVal = arr[mid + 1 + offset];
            int rightCnt = map.getOrDefault(rightVal, 0);
            if (rightCnt == 0) diffCnt++;
            map.put(rightVal, rightCnt - 1);
            if (map.get(rightVal) == 0) diffCnt--;

            // 3. diffCnt가 0이면 두 구간의 구성이 완벽히 일치 (이븐한 구간 발견)
            if (diffCnt == 0) {
                ans = Math.max(ans, (offset + 1) * 2);
            }
        }
    }
}
```

항목,내용
탐색 방식,중심점(mid) 기준 대칭 확장 (Symmetric Expansion)
시간 복잡도,O(N2) (Map 연산 포함 시 O(N2logN) 내외)
핵심 기법,diffCount 변수를 활용하여 Map의 모든 밸류가 0인지 매번 확인하지 않고 $O(1)$로 판별
공간 복잡도,O(N) (수열 저장 및 Map 활용)

🔑 Key Point:
"이븐함"의 재정의:
문제에서 제시한 "원하는 만큼 교환 가능" 조건은 순서가 무의미함을 뜻합니다. 이는 곧 두 구간의 숫자 구성(Multiset)이 일치해야 함을 의미하며, 이를 위해 한쪽은 더하고 한쪽은 빼는 Zero-Sum 방식의 카운팅을 적용했습니다.

인덱스 관리의 직관성:
구간의 시작점을 잡고 길이를 늘리면 중간 경계선이 계속 이동하여 인덱스 계산이 매우 복잡해집니다. 하지만 담장(mid)을 먼저 세우고 offset으로 벌려나가는 방식을 채택하여, 한 번 왼쪽 멤버는 끝까지 왼쪽 멤버로 남게 하여 인덱스 오류를 원천 차단했습니다.

효율적인 상태 판별:
diffCount는 빈도가 0인 숫자의 개수를 세는 것이 아니라, **"빈도가 0이 아닌 숫자의 종류 수"**를 추적합니다. 이 값이 0이 되는 순간이 바로 두 수열이 이븐해지는 순간이며, 이는 전체 로직의 속도를 비약적으로 향상시킵니다.

🎯 결론 및 배울 점
관점의 전환: 수열의 순차적 탐색보다 중심으로부터의 확장이 인덱스 계산과 상태 유지에 훨씬 유리함을 배웠습니다.

실시간 상태 추적: 맵의 모든 요소를 매번 전수조사하지 않고, 변경된 시점의 정보만 갱신하여 전체 상태(diffCount)를 파악하는 최적화 기법의 강력함을 익혔습니다.