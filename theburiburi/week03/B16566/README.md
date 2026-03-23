# [Platinum V] 카드 게임 - 16566

[문제 링크](https://www.acmicpc.net/problem/16566)

## 💡 풀이 접근 및 핵심 아이디어

이 문제는 민수가 철수가 낸 카드보다 **큰 카드 중 가장 작은 카드**를 내야 하는 게임입니다. 단, 한 번 사용한 카드는 다시 쓸 수 없기 때문에 이를 효율적으로 처리하는 것이 핵심입니다.

### 1. 이분 탐색 (Upper Bound)
* 철수가 낸 카드보다 '초과'하면서 가장 작은 카드의 위치를 찾아야 합니다.
* 카드를 오름차순으로 정렬한 뒤, 이분 탐색(Upper Bound)을 사용하여 철수의 카드보다 큰 카드가 처음 등장하는 인덱스(`idx`)를 $O(\log M)$ 만에 찾습니다.

### 2. 분리 집합 (Union-Find)을 활용한 '사용한 카드 건너뛰기'
* 만약 Upper Bound로 찾은 인덱스의 카드를 이미 사용했다면, 그 다음으로 큰 카드를 내야 합니다.
* 남은 카드를 찾기 위해 매번 배열을 순회하면 최악의 경우 $O(M)$이 걸려 시간 초과가 발생합니다.
* 이를 해결하기 위해 **Union-Find(Disjoint Set)** 알고리즘을 사용합니다. 특정 인덱스 `idx`의 카드를 사용하면, `union(idx, idx + 1)`을 호출하여 해당 인덱스의 부모를 다음 인덱스로 설정합니다.
* 이렇게 하면 다음에 누군가 `idx` 위치를 가리키더라도 `find(idx)`를 통해 **아직 사용하지 않은 다음 카드**의 인덱스로 한 번에 건너뛸 수 있습니다.

---

## 🔑 핵심 코드 설명

```java
// 1. Upper Bound (이분 탐색)
static int upper(int target) {
    int left = 0;
    int right = cards.length - 1;
    int res = cards.length;

    while (left <= right) {
        int mid = (left + right) / 2;
        if (cards[mid] > target) {
            res = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return res;
}