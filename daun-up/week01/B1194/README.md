# 🌙 1194. 달이 차오른다, 가자

**"조건을 꼼꼼히 보자 조건을! 조건을! 조건을 꼼꼼히 보자! 조건이 너무 너무 많고 처리를 못 하고 놓친다 조건을 확인하자!"**

## 📌 문제 분석

* 미로에서 출발점 `'0'` 에서 출구 `'1'` 까지 최단 거리를 탐색한다. (**BFS**)
* 열쇠 (`a~f`) 를 주워서 문 (`A ~ F`) 을 열 수 있다.
* 같은 위치라도 열쇠 보유 상태가 다르면 다른 상태다.
* 단순 좌표(`r, c`) 방문 처리는 불가능하며 어떤 열쇠를 가졌는지에 따라 같은 좌표라도 다시 방문해야 할 수 있다.



## 🛠 문제 풀이

* 처음에는 `keys` 배열에서 열쇠 목록을 관리하면서 해당하는 문을 만난다면 배열에서 빼고 넣고를 반복하는 방법으로 풀고자 했다. 하지만 **BFS는 여러 경로를 동시에 탐색하기 때문에 경로 간에 열쇠 상태가 공유되어서 그렇게 풀 순 없다.**
* 배열로 관리할 경우, `visited[N][M][열쇠 배열 그 자체]` 를 인덱스로 쓸 수 없다. 배열은 객체이므로 배열의 내용이 같아도 주소값이 다르면 다른 것으로 인식하기 때문이다.
* **비트마스킹**으로 관리할 경우 열쇠 6개의 모든 조합을 `0~63` 이라는 하나의 정수로 변환할 수 있다. 정수는 배열의 인덱스로 즉시 사용할 수 있기 때문에 이 열쇠들을 들고 이 자리에 온 적이 있는지를 $O(1)$ 로 바로 체크 가능하다.
* 만약 우리가 2차원 배열만 쓴다면, 열쇠가 없는 상태가 먼저 지나가는 순간 방문 처리가 되어버려서, 나중에 열쇠를 들고 왔을 때는 "이미 누가 왔었네?" 하고 돌아가 버린다. 비트마스크를 인덱스로 사용하면 열쇠가 있는 턴의 나와 열쇠가 없는 턴의 나를 다른 존재로 분리할 수 있다.
* 열쇠 상태는 각 큐 노드에 함께 저장해야 한다. (**노드와 같이 쇽쇽 이동**)

## 💻 문제 구현

### 1. 기본 체크

* 범위를 체크한다.
* 그리고 `map` 에 접근하여 해당 칸의 정보들을 `cell`, `newKeys` 에 저장한다. 가독성...
* 만약 벽이면 이동하지 않는다.
* 만약 출구라면 미로를 탈출한다. (`return`)
* 미로를 탈출하지 못 하면 `-1` 을 반환하기 때문에 `ans` 는 `-1` 로 초기화해둔다.

### 2. 열쇠 획득

```java
if (cell >= 'a' && cell <= 'f')
    newKeys |= (1 << (cell - 'a'));
```

* 열쇠 a → `1 << 0 = 0b000001`
* 열쇠 b → `1 << 1 = 0b000010`
* 열쇠 c → `1 << 2 = 0b000100`
* 열쇠 d → `1 << 3 = 0b001000`
* 열쇠 e → `1 << 4 = 0b010000`
* 열쇠 f → `1 << 5 = 0b100000`
* 현재 a, b 보유했다면 `newKeys = 0b000011`
* c 열쇠 획득한다면 `0b000100` 그리고 이 두 개를 **OR 연산**을 하면
* `=> 0b000011 | 0b000100 = 0b000111` (a, b, c 보유)



### 3. 문 통과 체크

```java
if (cell >= 'A' && cell <= 'F')
    if ((newKeys & (1 << (cell - 'A'))) == 0)
        continue;
```

* 만약 `cell` 이 문이라면 키 배열에 해당 키가 있는지 확인하고 하나라도 `0` 이면 (**AND 연산**) 넘어간다.
* `1 << (cell - 'A')`: 현재 마주친 문이 6 개의 비트 중에 몇 번째 알파벳인지 시프트 연산.

### 4. 방문 배열 초기화

* `visited = new boolean[n][m][1 << 6];`
* `1`을 왼쪽으로 6번 밀란 뜻이고 결과값은 `64`가 된다. 열쇠가 `a, b, c, d, e, f` 총 6종류이므로 열쇠 보유 여부를 체크하기 위해 6개의 비트 자리 즉 $2^6$ 크기의 배열이 필요하다.


## 📝 전체 코드

```java
import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static char[][] map;
    static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
    static int[] dc = { 0, 0, -1, 1 };
    static Queue<int[]> queue;
    static boolean[][][] visited;
    static int ans;

    static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], keys = curr[2], dist = curr[3];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!inRange(nr, nc)) continue;

                char cell = map[nr][nc];
                int newKeys = keys; // 기존 열쇠 상태 복사

                if (cell == '#') continue; // 벽

                if (cell == '1') { // 출구 도착
                    ans = dist + 1;
                    return;
                }

                // 열쇠 획득
                if (cell >= 'a' && cell <= 'f') {
                    newKeys |= (1 << (cell - 'a'));
                }
                
                // 문 통과 체크
                if (cell >= 'A' && cell <= 'F') {
                    if ((newKeys & (1 << (cell - 'A'))) == 0) continue;
                }

                // 방문 체크 (좌표 + 열쇠 상태)
                if (!visited[nr][nc][newKeys]) {
                    visited[nr][nc][newKeys] = true;
                    queue.add(new int[] { nr, nc, newKeys, dist + 1 });
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        queue = new LinkedList<>();
        visited = new boolean[n][m][1 << 6]; // 2^6 = 64가지 상태

        for (int r = 0; r < n; r++) {
            String line = br.readLine();
            for (int c = 0; c < m; c++) {
                map[r][c] = line.charAt(c);
                if (map[r][c] == '0') { 
                    queue.add(new int[] { r, c, 0, 0 }); 
                    visited[r][c][0] = true;
                }
            }
        }

        ans = -1;
        bfs();
        System.out.println(ans);
    }
}

```

## 💡 문제 정리

* **비트 마스킹으로 상태 관리**: 복잡한 상태의 조합을 정수 하나로 압축 가능.
* **최단 거리 -> BFS**: 이동 횟수의 최솟값을 구하므로 BFS
* 큐에는 좌표와 보유 키 상태, 최단 거리를 같이 전달한다.
