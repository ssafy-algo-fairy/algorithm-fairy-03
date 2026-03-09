### 📌 문제 정보

- **번호:** 9202  
- **제목:** Boggle  
- **난이도:** Platinum 5  
- **분류:** 문자열, DFS, 백트래킹, 구현

---

### 💡 접근 방식

> 보글판에서 사전에 있는 단어를 찾는 문제입니다.  
> 각 단어를 하나씩 검사하면서, 해당 단어의 첫 글자가 있는 위치에서만 DFS를 시작하는 방식으로 풀이했습니다.  
> 단어는 **길이 내림차순, 길이가 같다면 사전순 오름차순**으로 정렬해두고,  
> 가장 먼저 찾은 단어를 정답 단어로 사용했습니다.

---

### 🔹 1단계 – 단어 정렬

- 단어를 아래 기준에 따라 정렬
  1. 길이가 긴 단어 우선
  2. 길이가 같으면 사전순으로 앞선 단어 우선

이러면 가장 먼저 찾은 단어가 가장 긴 단어이면서 사전순으로 가장 앞선 단어

```java
Arrays.sort(words, (a, b) -> {
    if (a.length() != b.length()) {
        return b.length() - a.length();
    }
    return a.compareTo(b);
});
```

---

### 🔹 2단계 – 시작 가능한 위치 저장

- 보드의 각 알파벳이 어디에 있는지 `positions`에 저장
- DFS 시작 위치 위함

```java
positions[board[j][k] - 'A'].add(new int[]{j, k});
```

---

### 🔹 3단계 – 단어별 DFS 탐색

- 한 칸은 한 번만 사용할 수 있으므로 `visited` 배열을 사용
- 현재 칸의 문자가 `word.charAt(idx)`와 다르면 바로 탐색 종료

```java
if (board[r][c] != word.charAt(idx)) {
    return false;
}
```

---

### 🔹 4단계 – 점수, 개수, 정답 단어 처리

- 단어 만들면 점수 누적, 찾은 단어 개수 증가
- 정답 단어는 처음 찾은 단어

```java
if (bestWord.isEmpty()) {
    bestWord = word;
}
```

---

### 💻 핵심 로직

#### 단어를 만들 수 있는지 확인

```java
static boolean canMake(String word) {
    List<int[]> starts = positions[word.charAt(0) - 'A'];

    for (int[] pos : starts) {
        visited = new boolean[4][4];
        if (dfs(word, 0, pos[0], pos[1])) {
            return true;
        }
    }

    return false;
}
```

#### DFS 탐색

```java
static boolean dfs(String word, int idx, int r, int c) {
    if (board[r][c] != word.charAt(idx)) {
        return false;
    }

    if (idx == word.length() - 1) {
        return true;
    }

    visited[r][c] = true;

    for (int dir = 0; dir < 8; dir++) {
        int nr = r + dr[dir];
        int nc = c + dc[dir];

        if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
        if (visited[nr][nc]) continue;

        if (dfs(word, idx + 1, nr, nc)) {
            visited[r][c] = false;
            return true;
        }
    }

    visited[r][c] = false;
    return false;
}
```

---

### ⏳ 복잡도 분석

- **시간 복잡도:**  
  단어마다 DFS를 수행하므로, 최악의 경우 꽤 커질 수 있습니다.

- **공간 복잡도:**  
  `O(W + 16)` 정도
    - 단어 배열
    - 시작 위치 저장 리스트
    - 방문 배열
---

### ⚠️ 어려웠던 점
- 완탐으로 풀긴 했는데, 다른 좋은 방법이 있나 모르겠습니다.