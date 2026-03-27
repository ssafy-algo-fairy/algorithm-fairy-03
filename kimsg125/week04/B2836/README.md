# 🚤 BOJ 2836 - 수상 택시

- 🔗 문제 링크: https://www.acmicpc.net/problem/2836  
- 🏷️ 분류: 그리디 / 정렬 / 스위핑(구간 병합)

---

## 💡 접근 아이디어 / 시행착오
- 어차피 앞으로 가는건 고려할 필요 없음
- 뒤로 돌아가는 것만 구간 겹치면 합쳐서 총 길이에 더하기
- 처음엔 입력받으면서 처리했지만 시간 초과가 남
- 정렬하고 앞 구간이랑만 비교하면 되겠구나

---

## 🛠️ 구현 포인트
```
for (int i = 0; i < N; i++) {
	st = new StringTokenizer(br.readLine());

	int b = Integer.parseInt(st.nextToken());
	int a = Integer.parseInt(st.nextToken());

	if (a > b)
		continue;

	goBack.add(new Back(a, b));
}

goBack.sort((o1, o2) -> o1.start - o2.start);
```
- 뒤로 돌아가는 경우만 저장하고 정렬

```
sum = M;
int left = -1, right = -1;
for (Back back : goBack) {
	if (back.start > right) {
		sum += 2 * (right - left);

		left = back.start;
		right = back.end;
		continue;
	}

	left = Math.min(left, back.start);
	right = Math.max(right, back.end);
}
sum += 2 * (right - left);
```
- 앞 구간이랑 겹치지 않으면 앞 구간 길이에 2 곱해서 sum에 추가

---

## 💭 느낀 점
- start끼리 비교해서 정렬하면 되는데 o1.end - o2.start로 정렬하려다가 런타임에러가 나왔다
같은 값끼리 비교하자
- 값이 큰게 보이면 sum 같은건 long을 꼭 고려하자
---