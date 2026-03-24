package com.ssafy.algo.fairy.m3.week04.B10875;

import java.util.*;
import java.io.*;

public class b10875 {

	static class Line {
		int x1, y1, x2, y2;

		public Line(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	static int L, N, x, y, dir;
	static long time;

	static List<Line> lineList = new ArrayList<>();

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		L = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());

		x = 0;
		y = 0;
		dir = 0;
		time = 0;
		boolean dead = false;
		int addTime = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			String rot = st.nextToken();

			int nx = x + dx[dir] * len, ny = y + dy[dir] * len;

			addTime = Integer.MAX_VALUE;
			// 이전 라인과 겹칠 때
			for (int idx = 0; idx < lineList.size() - 1; idx++) {
				Line line = lineList.get(idx);

				if (dir == 0 || dir == 2) {
					if (line.y1 == line.y2 && y == line.y1
							&& ((line.x1 >= Math.min(x, nx) && line.x1 <= Math.max(x, nx))
									|| (line.x2 >= Math.min(x, nx) && line.x2 <= Math.max(x, nx)))) {
						dead = true;
						addTime = Math.min(addTime, Math.min(Math.abs(line.x1 - x), Math.abs(line.x2 - x)));
					} else if (line.x1 == line.x2
							&& (y >= Math.min(line.y1, line.y2) && y <= Math.max(line.y1, line.y2))
							&& (line.x1 >= Math.min(x, nx) && line.x1 <= Math.max(x, nx))) {
						dead = true;
						addTime = Math.min(addTime, Math.abs(line.x1 - x));
					}
				} else if (dir == 1 || dir == 3) {
					if (line.x1 == line.x2 && x == line.x1
							&& ((line.y1 >= Math.min(y, ny) && line.y1 <= Math.max(y, ny))
									|| (line.y2 >= Math.min(y, ny) && line.y2 <= Math.max(y, ny)))) {
						dead = true;
						addTime = Math.min(addTime, Math.min(Math.abs(line.y1 - y), Math.abs(line.y2 - y)));
					} else if (line.y1 == line.y2
							&& (x >= Math.min(line.x1, line.x2) && x <= Math.max(line.x1, line.x2))
							&& (line.y1 >= Math.min(y, ny) && line.y1 <= Math.max(y, ny))) {
						dead = true;
						addTime = Math.min(addTime, Math.abs(line.y1 - y));
					}
				}
			}

			// 격자판 바깥으로 나갈 때
			if (dir == 0 && nx > L) {
				dead = true;
				addTime = Math.min(addTime, L - x + 1);
			} else if (dir == 1 && ny < -L) {
				dead = true;
				addTime = Math.min(addTime, y + L + 1);
			} else if (dir == 2 && nx < -L) {
				dead = true;
				addTime = Math.min(addTime, x + L + 1);
			} else if (dir == 3 && ny > L) {
				dead = true;
				addTime = Math.min(addTime, L - y + 1);
			}

			if (dead) {
				time += addTime;
				break;
			}

			lineList.add(new Line(x, y, nx, ny));

			x = nx;
			y = ny;
			if (rot.equals("L"))
				dir = (dir + 3) % 4;
			else
				dir = (dir + 1) % 4;
			time += len;
		}

		if (!dead) {
			int nx = 0, ny = 0;

			if (dir == 0) {
				nx = L;
				ny = y;
				addTime = L - x + 1;
			} else if (dir == 1) {
				nx = x;
				ny = -L;
				addTime = y + L + 1;
			} else if (dir == 2) {
				nx = -L;
				ny = y;
				addTime = x + L + 1;
			} else if (dir == 3) {
				nx = x;
				ny = L;
				addTime = L - y + 1;
			}

			// 이전 라인과 겹칠 때
			for (int idx = 0; idx < lineList.size() - 1; idx++) {
				Line line = lineList.get(idx);

				if (dir == 0 || dir == 2) {
					if (line.y1 == line.y2 && y == line.y1
							&& ((line.x1 >= Math.min(x, nx) && line.x1 <= Math.max(x, nx))
									|| (line.x2 >= Math.min(x, nx) && line.x2 <= Math.max(x, nx)))) {
						dead = true;
						addTime = Math.min(addTime, Math.min(Math.abs(line.x1 - x), Math.abs(line.x2 - x)));
					} else if (line.x1 == line.x2
							&& (y >= Math.min(line.y1, line.y2) && y <= Math.max(line.y1, line.y2))
							&& (line.x1 >= Math.min(x, nx) && line.x1 <= Math.max(x, nx))) {
						dead = true;
						addTime = Math.min(addTime, Math.abs(line.x1 - x));
					}
				} else if (dir == 1 || dir == 3) {
					if (line.x1 == line.x2 && x == line.x1
							&& ((line.y1 >= Math.min(y, ny) && line.y1 <= Math.max(y, ny))
									|| (line.y2 >= Math.min(y, ny) && line.y2 <= Math.max(y, ny)))) {
						dead = true;
						addTime = Math.min(addTime, Math.min(Math.abs(line.y1 - y), Math.abs(line.y2 - y)));
					} else if (line.y1 == line.y2
							&& (x >= Math.min(line.x1, line.x2) && x <= Math.max(line.x1, line.x2))
							&& (line.y1 >= Math.min(y, ny) && line.y1 <= Math.max(y, ny))) {
						dead = true;
						addTime = Math.min(addTime, Math.abs(line.y1 - y));
					}
				}
			}

			time += addTime;
		}

		System.out.println(time);

	}

}
