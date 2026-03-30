# 🎮 BOJ 32645 - 동까뚱뽭 게임

- 🔗 문제 링크: https://www.acmicpc.net/problem/32645  
- 🏷️ 분류: 게임 이론 / DP

---

## 💡 접근 아이디어 / 시행착오
- 사실 못풀었다. 선규형과 대화 끝에 어케 푸는지 알았다.
- 힌트는 결국 특정 위치마다 승자가 정해져있다는 것
- 동점이거나 리프면 혁준, 그외에는 동우가 이긴다.
- dfs 로 따라 내려가면 된다.


## 💭 느낀 점
- 한 1~2주 정도 쉬니까 감 다떨어졌다. B형 땄다고 자만한줄 알겠지만 자소서 쓴다고 바빳다.
---

## 🛠️ 구현 포인트
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_32645 {
    static ArrayList<ArrayList<Integer>> edges;
    static int[] dp;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        edges = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            edges.add(new ArrayList<>());
        }

        for(int i=0; i<n-1; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        dp = new int[n+1];
        visited = new boolean[n+1];
        visited[1] = true;
        find(1);

        for (int i=1; i<=n; i++) {
            if (dp[i] == 1) {
                System.out.println("donggggas");
            } else {
                System.out.println("uppercut");
            }
        }
    }
    //동우가 시작 동점이면 혁준, 아니면 동우 맨끝은 혁준
    // 동우가 1번, 혁준이가 2번
    static int find(int now) {
        visited[now] = true;
        if (dp[now] != 0) return dp[now];
        

        int temp = 0;
        for (int next : edges.get(now)) {
            if (visited[next]) continue;
            temp = Math.max(temp, find(next));
        }

        if (temp == 1 || temp == 0) {
            dp[now] = 2;
        } else {
            dp[now] = 1;
        }
        return dp[now];
    }
}
```

---


