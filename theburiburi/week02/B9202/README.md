# 🔍 [백준 9202] Boggle (보글) 문제 풀이 분석

---

## 🌳 문제 핵심

> **4x4 보드**에서 인접한(8방향) 글자들을 조합하여 사전에 등재된 단어를 찾는 게임입니다.
> 1. **제한 사항**: 한 단어를 만들 때 같은 칸을 두 번 사용할 수 없으며, 단어 길이는 최대 8자입니다.
> 2. **점수 산정**: 단어 길이에 따라 0~11점이 부여되며, 가장 긴 단어와 찾은 단어 총 개수를 출력해야 합니다.
> 3. **효율성**: 사전의 단어 수가 최대 30만 개이므로, 단순 탐색보다는 **Trie(트라이)** 자료구조를 활용한 최적화가 필수적입니다.

---

## 💡 핵심 알고리즘: Trie + DFS (Backtracking)

```text
알고리즘 흐름 = 사전 단어들을 Trie에 저장 ➔ 4x4 보드의 각 칸에서 DFS 시작
 └─ Trie(트라이) 구성
     └─ 각 노드는 자식 노드들을 Map(또는 배열)으로 관리
     └─ 단어의 끝 지점(isEnd)에 실제 단어 문자열(word)을 저장하여 탐색 효율 극대화
 └─ DFS + 백트래킹 탐색
     ├─ 8방향 이동: 상, 하, 좌, 우 및 4개의 대각선 방향 탐색
     ├─ 중복 방지: 방문 배열(visited)을 사용하여 한 단어 내 같은 칸 사용 금지
     ├─ 결과 기록: HashSet을 사용하여 보드 하나에서 같은 단어를 중복 카운트하지 않음
```

🛠 주요 로직 구현 (Java)
```
// 1. Trie 노드 구조: 자식 정보와 해당 지점에서 끝나는 단어를 저장
static class Node {
    Map<Character, Node> map = new HashMap<>();
    String word = null; // null이 아니면 해당 위치가 단어의 완성 지점
}

// 2. DFS 탐색: 현재 노드에 단어가 있다면 결과 셋에 추가 및 최댓값 갱신
static void dfs(Node now, int y, int x, boolean visited[][]) {
    // 단어 발견 시 처리 (중복 제거를 위해 Set 활용)
    if (now.word != null && !set.contains(now.word)) {
        set.add(now.word);
        wordCnt++;
        maxScore += scores[now.word.length()];
        
        // 가장 긴 단어 갱신 (길이순 -> 사전순)
        if (ansStr.length() < now.word.length()) {
            ansStr = now.word;
        } else if (ansStr.length() == now.word.length()) {
            if (ansStr.compareTo(now.word) > 0) ansStr = now.word;
        }
    }

    // 8방향 탐색 및 백트래킹
    for (int i = 0; i < 8; i++) {
        int ny = y + dy[i];
        int nx = x + dx[i];
        if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4 || visited[ny][nx]) continue;

        if (now.map.containsKey(arr[ny][nx])) {
            visited[ny][nx] = true;
            dfs(now.map.get(arr[ny][nx]), ny, nx, visited);
            visited[ny][nx] = false; // 핵심: 탐색 후 방문 해제
        }
    }
}
```

항목,내용
자료구조,"Trie (트라이), HashSet, HashMap"
탐색 방식,DFS (깊이 우선 탐색) + Backtracking
시간 복잡도,"사전 구축: O(W×L), 보드 탐색: O(B×16×88) (실제 Trie 제약으로 훨씬 적음)"
공간 복잡도,O(단어총글자수) - Trie 노드 저장 공간

Gemini의 응답
Markdown
# 🔍 [백준 9202] Boggle (보글) 문제 풀이 분석

---

## 🌳 문제 핵심

> **4x4 보드**에서 인접한(8방향) 글자들을 조합하여 사전에 등재된 단어를 찾는 게임입니다.
> 1. **제한 사항**: 한 단어를 만들 때 같은 칸을 두 번 사용할 수 없으며, 단어 길이는 최대 8자입니다.
> 2. **점수 산정**: 단어 길이에 따라 0~11점이 부여되며, 가장 긴 단어와 찾은 단어 총 개수를 출력해야 합니다.
> 3. **효율성**: 사전의 단어 수가 최대 30만 개이므로, 단순 탐색보다는 **Trie(트라이)** 자료구조를 활용한 최적화가 필수적입니다.

---

## 💡 핵심 알고리즘: Trie + DFS (Backtracking)

```text
알고리즘 흐름 = 사전 단어들을 Trie에 저장 ➔ 4x4 보드의 각 칸에서 DFS 시작
 └─ Trie(트라이) 구성
     └─ 각 노드는 자식 노드들을 Map(또는 배열)으로 관리
     └─ 단어의 끝 지점(isEnd)에 실제 단어 문자열(word)을 저장하여 탐색 효율 극대화
 └─ DFS + 백트래킹 탐색
     ├─ 8방향 이동: 상, 하, 좌, 우 및 4개의 대각선 방향 탐색
     ├─ 중복 방지: 방문 배열(visited)을 사용하여 한 단어 내 같은 칸 사용 금지
     ├─ 결과 기록: HashSet을 사용하여 보드 하나에서 같은 단어를 중복 카운트하지 않음
🛠 주요 로직 구현 (Java)
Java
// 1. Trie 노드 구조: 자식 정보와 해당 지점에서 끝나는 단어를 저장
static class Node {
    Map<Character, Node> map = new HashMap<>();
    String word = null; // null이 아니면 해당 위치가 단어의 완성 지점
}

// 2. DFS 탐색: 현재 노드에 단어가 있다면 결과 셋에 추가 및 최댓값 갱신
static void dfs(Node now, int y, int x, boolean visited[][]) {
    // 단어 발견 시 처리 (중복 제거를 위해 Set 활용)
    if (now.word != null && !set.contains(now.word)) {
        set.add(now.word);
        wordCnt++;
        maxScore += scores[now.word.length()];
        
        // 가장 긴 단어 갱신 (길이순 -> 사전순)
        if (ansStr.length() < now.word.length()) {
            ansStr = now.word;
        } else if (ansStr.length() == now.word.length()) {
            if (ansStr.compareTo(now.word) > 0) ansStr = now.word;
        }
    }

    // 8방향 탐색 및 백트래킹
    for (int i = 0; i < 8; i++) {
        int ny = y + dy[i];
        int nx = x + dx[i];
        if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4 || visited[ny][nx]) continue;

        if (now.map.containsKey(arr[ny][nx])) {
            visited[ny][nx] = true;
            dfs(now.map.get(arr[ny][nx]), ny, nx, visited);
            visited[ny][nx] = false; // 핵심: 탐색 후 방문 해제
        }
    }
}
📊 성능 분석 및 데이터 요약
항목	내용
자료구조	Trie (트라이), HashSet, HashMap
탐색 방식	DFS (깊이 우선 탐색) + Backtracking
시간 복잡도	사전 구축: O(W×L), 보드 탐색: O(B×16×8^8) (실제 Trie 제약으로 훨씬 적음)
공간 복잡도	O(단어총글자수) - Trie 노드 저장 공간


🎯 결론 및 배울 점
자료구조의 선택: 30만 개의 단어 중 보드 위의 글자와 일치하는 것만 빠르게 걸러내기 위해 Trie를 사용하여 탐색 범위를 획기적으로 줄였습니다.

백트래킹의 정석: visited 배열을 통해 경로를 제어하고, 탐색이 끝난 뒤 다시 false로 되돌리는 과정을 통해 4x4의 좁은 공간에서 가능한 모든 조합을 효율적으로 찾아냈습니다.

입출력 최적화: 많은 양의 테스트 케이스(보드)를 처리하기 위해 StringBuilder를 사용하여 한 번에 출력함으로써 시간 효율성을 높였습니다.

유연한 비교 로직: String.compareTo()를 활용해 "길이가 같을 경우 사전순 앞선 단어"라는 복합적인 정렬 조건을 간결하게 해결했습니다.