🚀 백준 2610 : 회의준비
1. 문제 개요
알고리즘: 플로이드-워셜 (Floyd-Warshall)

핵심 포인트:

arr[k][i][j] 순서 지키기

그룹별로 list를 독립적으로 운영하여 대표 선출

핵심 소스 코드
```
    static void solve() {
        for (int i = 1; i <= N; i++) {
            arr[i][i] = 0;
        }

        // 플로이드-워셜
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (k == i) continue;
                for (int j = 1; j <= N; j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }

        boolean visited[] = new boolean[N + 1];
        List<Integer> represents = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            // 1. 그룹 멤버 찾기
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= N; j++) {
                if (arr[i][j] != INF) {
                    list.add(j);
                    visited[j] = true;
                }
            }

            // 2. 그룹 내 대표 선출
            int realMaxDistance = INF;
            int representNum = i;
            for (int represent : list) {
                int maxDistance = 0;
                for (int other : list) {
                    maxDistance = Math.max(maxDistance, arr[represent][other]);
                }

                if (realMaxDistance > maxDistance) {
                    representNum = represent;
                    realMaxDistance = maxDistance;
                }
            }
            represents.add(representNum);
        }

        // 3. 정렬 및 출력
        represents.sort((s1, s2) -> s1.compareTo(s2));
        sb.append(represents.size()).append("\n");
        for (int now : represents) {
            sb.append(now).append("\n");
        }
    }
```

2. 셀프 피드백
플로이드-워셜: k-i-j 순서로 중간 경로를 완벽하게 탐색하도록 구현함.

그룹 처리: visited 배열과 내부 list를 사용해 연결 요소(위원회)를 명확히 구분함.

대표 선출: 그룹 내 모든 인원(list)을 순회하며 응답 시간이 가장 짧은(의사전달 거리가 짧은) 사람을 정확히 선발함.


죄송합니다 변수명 너무 대충 썼습니다.