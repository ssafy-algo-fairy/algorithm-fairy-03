# 🐼 BOJ 1937 - 욕심쟁이 판다

- 🔗 문제 링크: https://www.acmicpc.net/problem/1937  
- 🏷️ 분류: DFS + DP(메모이제이션) / 그래프 DP

---

## 💡 접근 아이디어 / 시행착오
- 그냥 흔한 메모이제이션 문제
- 4방향으로 돌아보면서 최대 값을 가져와 저장한다.
- 4방향으로 돌아볼 때 가봤던 곳이면 저장된 dp 값을 가져온다.
- 걍 풀어본 유형 (잘난척 아님.)

---

## 🛠️ 회고
- 최적화 하려면 재귀에 들어가서 -1이 아닌지 확인하고 return 이 아니라 가기전에 바로 체크하는게 더 빠를 듯
- 어차피 한칸은 무조건 가는거라 -1이 아니라 그냥 0으로 둬도 되겠네
- 오늘은 어려운 문제가 아니라 다행이다. ㄹㅇ 잠오는데 어려운 문제였으면 기절 당했을 듯
- 의재님이 문제들을 잘 올려줘서 좋다ㅎㅎ 이걸 볼라나

---

## 📝 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1937 {

    static int n;
    static int[][] map;
    static int[][] memo;
    static int[] dx = {0, -1, 0 ,1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        memo = new int[n][n];

        for (int i=0; i<n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<n; j++) {
                memo[i][j] = -1;
            }
        }

        int result = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                result = Math.max(result, find(i, j));
            }
        }
        System.out.println(result);
    }

    static int find(int sx, int sy) {
        if (memo[sx][sy] != -1) {
            return memo[sx][sy];
        }

        int result = 1;
        for (int i=0; i<4; i++) {
            int nx = sx + dx[i], ny = sy + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (map[sx][sy] >= map[nx][ny]) continue;
            result = Math.max(result, find(nx, ny) + 1);
        }
        memo[sx][sy] = result;

        return result;
    }

}
```

---