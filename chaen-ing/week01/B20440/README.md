## **🔍 문제 요약**

- 모기들의 입, 퇴장 시각이 주어짐
- 가장 많았던 시간대와 해당 시간대의 모기 개수를 구하자 (여러개라면 가장 앞의것)
- 1 ≤ 모기 마릿수 *N* ≤ 1,000,000
- 0 ≤  입장 *TE < 퇴장 TX* ≤ 2,100,000,000

---

## **💡문제 접근 / 풀이 전략**

- 일반적인 방법… 모기 수 올리기 → 최대값 찾고 → 그 구간 리턴

  ⇒ 메모리 초과 : 배열을 만들면 안된다~

- 모기의 입장 시간과 퇴장 시간을 기준으로 생각
    - 입장 +1, 퇴장 -1
    - 해당 값들을 시간 기준 정렬
- 같은시간에 모기가 여러 마리 나가고 들어올 수 있으므로 같은 시간끼리는 묶어서 처리
- 모기개수가 최대값보다 커지면 시작 시간 및 최대값 갱신
    - 최대값과 같고 현재 범위내에 있다면 종료시간 갱신
    - 끝났으면 isMax를 false로 바꿔서 최초의 최대 구간을 종료시킨다 (같은 max 값은 무시하도록)

---

## **✅ 코드 & 소요 시간**

```java
package boj20440;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Time[] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		time = new Time[2 * N];
		for (int i = 0; i < 2 * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			time[i++] = new Time(start, 1);
			time[i] = new Time(end, -1);
		}
		Arrays.sort(time);

		int startTime = 0, endTime = 0, cnt = 0, maxCnt = 0;
		boolean isMax = false;
		int i = 0;

		while (i < time.length) {
			// 현재 시간
			Time cur = time[i];

			while (i < time.length && cur.time == time[i].time) {    // 같은 시간 묶어서 처리
				cnt += time[i].cnt;
				i++;
			}

			if (i >= time.length)
				break;

			int next = time[i].time;

			if (cnt > maxCnt) {
				maxCnt = cnt;
				startTime = cur.time;
				endTime = next;
				isMax = true;
			} else if (isMax) {
				if (cnt == maxCnt) {
					endTime = next;    // 종료 시간 갱신
				} else {
					isMax = false;
				}
			}
		}

		System.out.println(maxCnt);
		System.out.println(startTime + " " + endTime);
	}
}

class Time implements Comparable<Time> {
	int time, cnt;

	public Time(int time, int cnt) {
		this.time = time;
		this.cnt = cnt;
	}

	// 시간 오름차순 정렬
	@Override
	public int compareTo(Time o) {
		return this.time - o.time;
	}
}

```

---

## **✍️ 회고**

- 어렵네요.. 혼자힘으로 풀었다고 할수가 x. ㅋㅋ…
- 더 익숙해져야 할 것 같슴다..

---

## **🧩 다른 풀이 (선택)**

```java

```

---