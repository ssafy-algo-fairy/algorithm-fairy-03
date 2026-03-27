# [BOJ] 4902번: 삼각형의 값 - Java

### 📌 문제 정보
* **문제 출처:** [백준 온라인 저지 4902번](https://www.acmicpc.net/problem/4902)
* **알고리즘 분류:** 누적 합(Prefix Sum), 브루트포스 알고리즘(Brute Force)

---

### 💡 풀이 접근
입력으로 주어지는 삼각형의 최대 줄 수 N은 400입니다. 모든 좌표를 순회하며 삼각형의 합을 일일이 더하면 시간 초과가 발생하므로, **행별 누적 합(Prefix Sum)**을 이용하여 구간 합을 O(1)로 계산하는 것이 핵심입니다.

삼각형은 꼭짓점의 방향에 따라 두 가지로 나뉩니다.
1. **정방향 삼각형 (`j % 2 == 1`)**: 
   * 위에서 아래로 내려가면서 크기가 확장됩니다.
   * 밑으로 내려갈수록 너비가 일정하게 늘어나므로 인덱스 초과 위험이 적습니다.
2. **역방향 삼각형 (`j % 2 == 0`)**: 
   * 아래에서 위로 올라가면서 크기가 확장됩니다.
   * 위로 올라갈수록 전체 행의 너비가 좁아지므로 엄격한 인덱스 경계 검사가 필수적입니다.

---

### ⚠️ 핵심 트러블 슈팅 및 주의사항

* **최댓값 초기화 오류 방지**
  * 삼각형 내부의 원소가 모두 음수일 수 있습니다. 따라서 최댓값을 저장하는 `ans` 변수를 `0`이 아닌 `Integer.MIN_VALUE`로 초기화해야 정상적인 최댓값을 갱신할 수 있습니다.
* **역방향 삼각형 우측 경계 이탈**
  * 역삼각형이 위로 확장될 때, 시작점의 x 좌표(오른쪽 끝)는 고정되어 있지만 해당 행(Row)의 최대 폭은 점점 좁아집니다.
  * 따라서 `x`가 현재 행의 최대 길이(`i * 2 - 1`)를 벗어나는 순간 즉시 확장을 멈춰야(`break`) 잘못된 빈 배열 영역(0)을 더하는 논리적 오류를 막을 수 있습니다.

---

### 💻 전체 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb;
    static int N, ans;
    static int[][] preSum, tree;
    
    public static void main(String args[]) throws IOException {
        inputFile();
        System.out.println(sb.toString());
    }
    
    static void inputFile() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        int idx = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            ans = Integer.MIN_VALUE; // 음수 데이터 처리를 위해 최소값으로 초기화

            if (N == 0) break;

            tree = new int[N + 1][2 * N];
            preSum = new int[N + 1][2 * N];

            // 1. 트리 입력 및 각 행의 누적 합 계산
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j < i * 2; j++) {
                    tree[i][j] = Integer.parseInt(st.nextToken());
                    preSum[i][j] = preSum[i][j - 1] + tree[i][j];
                }
            }

            // 2. 모든 좌표를 순회하며 삼각형 탐색
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j < i * 2; j++) {
                    if (j % 2 == 1) {
                        makeTriangle(i, j); // 정방향 삼각형 (홀수 번째)
                    } else {
                        makeReverseTriangle(i, j); // 역방향 삼각형 (짝수 번째)
                    }
                }
            }

            sb.append(idx++).append(". ").append(ans).append("\n");
        }
    }

    // 아래로 확장되는 정방향 삼각형 로직
    static void makeTriangle(int y, int x) {
        int sum = 0;
        int idx = 0;
        for (int i = y; i <= N; i++) {
            sum += (preSum[i][x + (idx * 2)] - preSum[i][x - 1]);
            ans = Math.max(sum, ans);
            idx++;
        }
    }

    // 위로 확장되는 역방향 삼각형 로직
    static void makeReverseTriangle(int y, int x) {
        int sum = 0;
        int idx = 0;
        for (int i = y; i > 0; i--) {
            if (x > i * 2 - 1) break; // 위로 갈수록 좁아지는 행의 최대 범위(우측) 초과 시 중단
            if (x - 1 - (idx * 2) < 0) break; // 좌측 인덱스 범위 초과 시 중단
            
            sum += (preSum[i][x] - preSum[i][x - 1 - (idx * 2)]);
            ans = Math.max(sum, ans);
            idx++;
        }
    }
}
```