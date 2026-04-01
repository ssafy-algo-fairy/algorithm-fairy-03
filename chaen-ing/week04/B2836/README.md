## **🔍 문제 요약**

- 상근이 집 0번
- 집은 0 ~ M번 강을 따라서 번호. 인접한 집 끼리는 1km
- M번으로 가는길에 사람들을 태워줌
- N명이 타고 이들을 모두 태울 수 있음
- 모든 사람들을 데려다주고 마지막에 M에 도착할때 이동 거리 최솟값

- 사람 N ≤ 300,000
- 3 ≤ 집 M ≤ 10^9

---

## **💡문제 접근 / 풀이 전략**

- **순방향일때**
    - 0 → M 경로에 있으므로 걍 가면됨
- **역방향일때**
    - start, end를 뒤집고 start 기준 오름차순 정렬
    - 중간부분이 겹치면 합치기

---

## **✅ 코드 & 소요 시간**

```java
package week04.B2836;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long M;
	static ArrayList<Node> arrayList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());    // 사람 수
		M = Long.parseLong(st.nextToken());    // 마을 수

		arrayList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			if (start < end)
				continue;    // 순방향은 패스
			arrayList.add(new Node(end, start));
		}

		long sum = M;
		if (arrayList.isEmpty()) {
			System.out.println(M);
			return;
		}

		Collections.sort(arrayList);
		long start = arrayList.get(0).start;
		long end = arrayList.get(0).end;

		for (int i = 1; i < arrayList.size(); i++) {
			Node cur = arrayList.get(i);

			if (cur.start <= end) {    // 겹치면 합침
				end = Math.max(end, cur.end);
			} else {
				sum += (end - start) * 2;
				start = cur.start;
				end = cur.end;
			}
		}

		sum += (end - start) * 2;

		System.out.println(sum);

	}
}

class Node implements Comparable<Node> {
	long start;
	long end;

	public Node(long start, long end) {
		this.start = start;
		this.end = end;
	}

	public int compareTo(Node o) {
		return Long.compare(this.start, o.start);
	}
}

```

102752 / 836

---

## **✍️ 회고**

- 오랜만이다..

---