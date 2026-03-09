# 백준 9202: Boggle

## 1. 문제 요약

4x4 크기의 그리드(Boggle Board)에서 인접한 8방향의 글자들을 조합하여 단어 사전(Dictionary)에 등재된 단어들을 찾는 게임입니다.

* **제한 사항**:
* 사전 단어 수: 최대 300,000개
* 보드 수: 최대 30개
* 단어 길이: 1자 이상 8자 이하
* 한 보드에서 같은 칸은 두 번 사용할 수 없으며, 찾은 단어는 중복 점수를 계산하지 않습니다.


* **출력**: 각 보드별로 **획득할 수 있는 최대 점수**, **가장 긴 단어**, **찾은 단어의 총 개수**를 출력합니다.

---

## 2. 사용 로직 및 핵심 알고리즘

이 문제는 대량의 단어 사전에서 효율적인 문자열 탐색이 필요하므로 **Trie(트라이)** 자료구조와 **DFS(깊이 우선 탐색)**를 결합하여 해결했습니다.

* **Trie 자료구조**: 30만 개의 사전 단어를 효율적으로 저장하고 탐색하기 위해 사용합니다. 각 노드는 다음 문자를 가리키는 포인터 배열(`Trie[26]`)과 단어의 끝임을 나타내는 `end` 플래그를 가집니다.
* **DFS + 백트래킹**: 4x4 보드의 각 칸을 시작점으로 하여 인접한 8방향으로 이동하며 단어를 조합합니다. 이때 Trie를 타고 내려가며, 사전에 없는 접두사(Prefix)인 경우 탐색을 즉시 종료(Pruning)하여 성능을 최적화합니다.
* **중복 방지**: 한 보드에서 동일한 단어가 여러 번 발견될 수 있으므로 `HashSet<String>`을 사용하여 유니크한 단어만 결과에 반영합니다.

---

## 3. 코드 하이라이트

### Trie 구조 및 삽입

```java
static class Trie {
    Trie[] node = new Trie[26];
    boolean end = false;
    
    void input(String str, int idx) {
        if (str.length() == idx) {
            this.end = true;
            return;
        }
        int c = str.charAt(idx) - 'A';
        if (node[c] == null) {
            node[c] = new Trie();
        }
        node[c].input(str, idx + 1);
    }
}

```

### DFS 기반 Boggle 탐색

```java
static void search(int i, int j, int depth, Trie root) {
    nowword[depth] = board[i][j];
    // 단어를 찾은 경우 처리 (Set을 통한 중복 체크 및 점수 계산)
    if (root.end) {
        String now = String.valueOf(nowword, 0, depth + 1);
        if (!set.contains(now)) {
            set.add(now);
            updateResult(now); // 최장 단어 및 점수 갱신 로직
        }
    }
    
    // 8방향 탐색 및 백트래킹
    for (int[] d: delta) {
        int di = i + d[0], dj = j + d[1];
        if (isValid(di, dj) && !visit[di][dj] && root.node[board[di][dj] - 'A'] != null) {
            visit[di][dj] = true;
            search(di, dj, depth + 1, root.node[board[di][dj] - 'A']);
            visit[di][dj] = false;
        }
    }
}

```

---

## 4. 시간 복잡도 분석

1. **Trie 구축**: $O(W \times L)$, 여기서 $W$는 단어의 개수(300,000), $L$은 최대 단어 길이(8)입니다.
2. **보드 탐색**: 보드당 $16$개의 칸에서 시작하여 최대 $8^8$의 경로를 가질 수 있으나, Trie를 이용한 가지치기 덕분에 실제 탐색 범위는 매우 작습니다.
3. **총 복잡도**: $O(W \cdot L + B \cdot (16 \cdot \text{dfs}))$. 여기서 $B$는 보드 개수(30)입니다. $L$이 8로 매우 작기 때문에 제한 시간 내에 충분히 수행 가능합니다.

---

## 5. 학습 포인트 및 개선 사항

* **Trie의 효율성**: 단순 문자열 비교 대신 Trie를 사용함으로써, 보드 내에서 만들어지는 수많은 부분 문자열이 사전에 존재하는지 여부를 $O(L)$만에 판별할 수 있었습니다.
* **정렬 조건 주의**: 가장 긴 단어를 찾을 때 길이가 같다면 사전 순으로 앞서는 것을 선택해야 합니다. `compareTo` 메서드를 활용하여 `(len == longword.length() && now.compareTo(longword) < 0)` 조건을 정확히 구현했습니다.
* **메모리 최적화**: `Trie` 노드가 많이 생성될 수 있으므로, 메모리 제한이 타이트할 경우 배열 대신 `HashMap`을 고려하거나 필요한 경우에만 노드를 생성하는 전략이 중요합니다. 본 코드에서는 고정 배열을 사용하여 속도를 우선시했습니다.

---