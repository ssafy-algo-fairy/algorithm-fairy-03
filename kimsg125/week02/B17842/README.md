# 🚌 BOJ 17842 - 버스 노선

- 🔗 문제 링크: https://www.acmicpc.net/problem/17842  
- 🏷️ 분류: 트리 / 그리디

---

## 💡 접근 아이디어 / 시행착오
- 결국 모든 정류장을 거치기 위해서는 끝에 있는 정류장을 가야한다
- 끝에서 끝을 연결하면 중간에 있는 정류장들은 고려할 필요 없겠다
- 끝에 있는 정류장 개수를 2로 나누면 되겠구나

---

## 🛠️ 구현 포인트
```
connected = new int[N];
for (int i = 0; i < N - 1; i++) {
	st = new StringTokenizer(br.readLine());

	connected[Integer.parseInt(st.nextToken())]++;
	connected[Integer.parseInt(st.nextToken())]++;
}
```
- 각 정류장에 연결된 도로를 connected로 카운트

```
for (int i = 0; i < N; i++)
	if (connected[i] == 1)
		endCnt++;
```
- 연결된 도로가 한 개인 끝에 있는 정류장을 카운트

```
if (endCnt % 2 == 1)
	endCnt++;
System.out.println(endCnt / 2);
```
- 홀수면 1 더하고 2로 나누면 정답

---

## 📝 느낀 점
- 오히려 조금 쉽게? 생각하면 풀리는 문제