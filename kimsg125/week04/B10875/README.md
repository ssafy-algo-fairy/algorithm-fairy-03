# 🐍 BOJ 10875 - 뱀

- 🔗 문제 링크: https://www.acmicpc.net/problem/10875  
- 🏷️ 분류: 시뮬레이션 / 구현 / 좌표 기하(선분 교차)

---

## 💡 접근 아이디어 / 시행착오
- 회전이 최대 1000번이니까 선분으로 저장하고 교차하는지 확인하자

---

## 🛠️ 구현 포인트
```
x = 0;
y = 0;
dir = 0;
time = 0;
boolean dead = false;
int addTime = 0;
```
- 현재 좌표, 방향, 누적 시간, 죽었는지 여부, 죽었을 때 추가 시간

```
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
```
- 직전 선분을 제외한 다른 선분과 만나는 경우

```
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
```
- 격자판 밖으로 나가는 경우

```
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
```
- 죽었으면 addTime 더하고 break, 아니면 좌표,방향,시간 업데이트

```
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
```
- 마지막 회전까지 죽지 않았을 경우 어떤 선분 또는 격자판을 만날 때까지 직진

---

## 💭 느낀 점
- 구현이 제일 빡센 문제이긴 했지만 죽는 경우를 떠올리기도 굉장히 어려웠다
- 백준에서 기본 예제 좀 잘 줬으면 좋겠다
