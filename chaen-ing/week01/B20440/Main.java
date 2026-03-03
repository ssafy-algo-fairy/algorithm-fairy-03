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
