# [백준 2213] 트리의 독립집합

## 1. 문제 요약
* **문제 목표**: 가중치가 있는 트리 구조에서 서로 인접하지 않는 정점들의 집합인 '독립집합'을 구하되, 그 가중치의 합이 최대가 되는 경우를 찾습니다.
* **출력 데이터**: 최대 가중치의 합과 해당 독립집합에 속하는 정점 번호를 오름차순으로 출력합니다.
* **제한 사항**: 정점의 수 $n$은 최대 10,000개이며, 각 정점의 가중치는 10,000 이하의 자연수입니다.

---

## 2. 사용 로직 및 핵심 알고리즘
이 문제는 일반적인 그래프에서는 NP-Hard 문제인 '최대 독립집합 문제'를 **트리 구조**라는 특수성을 이용해 **트리 DP(Dynamic Programming on Trees)**로 해결합니다.

### **상태 정의**
* `dp[node][1]`: `node`를 독립집합에 **포함하는** 경우, 해당 서브트리의 최대 가중치 합.
* `dp[node][0]`: `node`를 독립집합에 **포함하지 않는** 경우, 해당 서브트리의 최대 가중치 합.

### **점화식**
1.  **현재 노드를 선택하는 경우 (`dp[node][1]`)**:
    * 자식 노드들은 절대로 선택할 수 없습니다.
    * `dp[node][1] = weight[node] + Σ(dp[child][0])`
2.  **현재 노드를 선택하지 않는 경우 (`dp[node][0]`)**:
    * 자식 노드들은 선택해도 되고, 안 해도 됩니다. 둘 중 큰 값을 취합니다.
    * `dp[node][0] = Σ(max(dp[child][0], dp[child][1]))`

### **집합 추적 (Backtracking)**
* DP 연산이 끝난 후, 루트 노드부터 다시 탐색하며 어떤 선택이 최적이었는지 역추적하여 독립집합에 포함된 정점들을 리스트에 담습니다.

---

## 3. 코드 하이라이트
핵심이 되는 트리 DP 탐색 부분과 역추적(findset) 로직입니다.

```java
// 트리 DP를 이용한 최대 가중치 계산
static int dfs(int node, int parent, boolean select_parent) {
    int sel_max = 0;
    if (!select_parent) { // 부모가 선택되지 않았을 때만 현재 노드 선택 가능
        if (dp[node][1] != 0) sel_max = dp[node][1];
        else {
            sel_max = w[node];
            for (int child : tree[node]) {
                if (child == parent) continue;
                sel_max += dfs(child, node, true); // 자식은 선택 불가(true 전달)
            }
            dp[node][1] = sel_max;
        }
    }

    int no_sel_max = 0;
    if (dp[node][0] != 0) no_sel_max = dp[node][0];
    else {
        for (int child : tree[node]) {
            if (child == parent) continue;
            no_sel_max += dfs(child, node, false); // 자식 선택 여부 자유
        }
        dp[node][0] = no_sel_max;
    }
    return Math.max(sel_max, no_sel_max);
}

// 최적해 구성을 위한 역추적
static void findset(int node, int parent, boolean parent_select) {
    // 부모가 선택되었거나, 현재 노드를 선택하지 않는 것이 이득인 경우
    if (parent_select || dp[node][0] > dp[node][1]) {
        for (int child : tree[node]) {
            if (child == parent) continue;
            findset(child, node, false);
        }
    } else { // 현재 노드를 선택하는 것이 이득인 경우
        max_group.add(node);
        for (int child : tree[node]) {
            if (child == parent) continue;
            findset(child, node, true);
        }
    }
}
```

---

## 4. 시간 복잡도 분석
* **트리 순회 (DFS)**: 모든 정점을 한 번씩 방문하고, 모든 간선을 두 번(부모-자식 방향) 검사합니다. 정점의 개수가 $N$일 때 간선의 개수는 $N-1$이므로 $O(N)$입니다.
* **결과 정렬**: 최종적으로 구한 독립집합의 정점들을 오름차순으로 정렬합니다. 최악의 경우 독립집합의 크기는 $N$이 될 수 있으므로 $O(N \log N)$이 소요됩니다.
* **총 복잡도**: **$O(N \log N)$** (정렬 단계가 지배적이나 $N=10,000$이므로 매우 효율적입니다).

---

## 5. 학습 포인트 및 개선 사항
* **메모이제이션(Memoization)**: 재귀 호출 과정에서 이미 계산된 `dp` 값을 활용하여 중복 계산을 방지했습니다.
* **트리에서의 역추적**: DP 테이블에 결과값만 저장하는 것이 아니라, 루트부터 하향식으로 상태를 비교하며 실제 해(Set)를 구성하는 방법을 익힐 수 있었습니다.
* **개선 사항**: 현재 코드에서는 `dfs` 내에서 `select_parent` 조건에 따라 분기 처리를 하고 있으나, `dp[node][0]`과 `dp[node][1]`을 먼저 한 번의 DFS로 완전히 채운 뒤 별도의 함수로 분리하면 가독성을 더 높일 수 있습니다.