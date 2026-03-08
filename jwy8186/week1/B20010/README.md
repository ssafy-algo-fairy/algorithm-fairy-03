## **🔍 문제 요약**

- 마을 사이에 교역로 건설하기
- 교역로는 양방향 이동 가능
- 서로 이동 불가능한 마을이 없도록 교역로 건설하기
- 이때 “최소 비용”, “마을 간 이동하는 비용이 가장 큰 경로의 비용”을 구하자
    - 최소 비용 방법은 유일

- 1 ≤ N 마을 수 ≤ 1,000
- 1 ≤ K 설치가능한 교역로  ≤ 1,000,000
- 1 ≤ c 마을간에 연결 비용 ≤ 1,000,000
- 시간제한 0.5초

---

## **💡문제 접근 / 풀이 전략**
- 문제에서 대놓고 그냥 MST라고 줌
- 문제를 이상하게 적어둬서 두번꺼가 뭘 구하라는 건지 한참 고민함
- 영 보기엔 트리의 지름 문제 같기도 한데.. MST 구한거를 이용하란건지 사아아알짝 애매해서 그냥해봄
- 그냥 해보니까 맞음 ㅋㅋ
- 뭐 트리의 지름 구하는 방법은 간단함
  - 한 점 잡고 가장 멀리있는 걸 구함
  - 해당 점에서 다시 가장 멀리있는걸 구함 그게 트리의 지름
  - 증명은 흠..
    - 가장 멀리가려고 하면 트리의 지름을 지나가게 되는데
    - 그럼 지름의 한 끝점으로 가게 되어있음
    - 애초에 지름이 된 이유가 가장 멀리있는거 두개 잡고 쭉 늘린거니까..

## **✍️ 회고**
- 문제 설명이 살짝 애매쓰 하다
- 갑자기 난도가 내려가서 기분이 좋다
- 뭔가 실력 변곡점에 다가가고 잇는 것 같다
- 문제를 봤을때 딱 풀리거나 아예 안풀리거나 둘 중 하나다
- 그냥 재능의 문제인가 그러면 너무 슬프니까 그냥 뇌빼고 해야겠다.
- 오늘 농경 인적성을 시원하게 한사바리 말아먹어서 배부르다
- 농경 인적성은 지이이이이인짜 말도안되게 어렵다.


---

## **✅ 코드 & 소요 시간**

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20010 {

    static int n, k;
    static class Bridge {
        int s, e, w;
        public Bridge(int s, int e,  int w) {
            this.s=s; this.e=e; this.w=w;
        }

        public Bridge(int e, int w) {
            this.e=e; this.w=w;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        Bridge[] minBridges = new Bridge[k];
        boolean[] used = new boolean[k];

        for (int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            Bridge b = new Bridge(s, e, w);
            minBridges[i] = b;
        }

        Arrays.sort(minBridges, (a, b) -> Integer.compare(a.w, b.w));
        System.out.println(findDist(minBridges, used));

        System.out.println(findMax(minBridges, used));
    }

    static int findMax(Bridge[] bridges, boolean[] used) {
        int dist = 0;

        ArrayList<ArrayList<Bridge>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) graph.add(new ArrayList<>());

        for (int i=0; i<k; i++) {
            if (used[i]) {
                graph.get(bridges[i].s).add(new Bridge(bridges[i].e, bridges[i].w));
                graph.get(bridges[i].e).add(new Bridge(bridges[i].s, bridges[i].w));
            }
        }

        boolean[] visited = new boolean[n];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        visited[0] = true;
        int maxDist = 0, maxNode = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (Bridge next : graph.get(cur[0])) {
                if (visited[next.e]) continue;
                visited[next.e] = true;
                q.add(new int[]{next.e, cur[1] + next.w});
                if (cur[1] + next.w > maxDist) {
                    maxDist = cur[1] + next.w;
                    maxNode = next.e;
                }
            }
        }

        Arrays.fill(visited, false);
        q.add(new int[]{maxNode, 0});
        visited[maxNode] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (Bridge next : graph.get(cur[0])) {
                if (visited[next.e]) continue;
                visited[next.e] = true;
                q.add(new int[]{next.e, cur[1] + next.w});
                if (cur[1] + next.w > maxDist) maxDist = cur[1] + next.w;
            }
        }

        return maxDist;
    }

    static int findDist(Bridge[] bridges, boolean[] used) {
        int dist = 0;
        int[] parents = new int[n];
        for (int i=0; i<n; i++) parents[i] = i;

        for (int i=0; i<k; i++) {
            int p1 = find(bridges[i].s, parents);
            int p2 = find(bridges[i].e, parents);
            if (p1 != p2) {
                dist += bridges[i].w;
                used[i] = true;
                union(p1, p2, parents);
            }
        }
    
        return dist;
    }

    static int find(int x, int[] parents) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x], parents);
    }

    static void union(int a, int b, int[] parents) {
        parents[find(a, parents)] = find(b, parents);
    }


}


```

---

ref #33 