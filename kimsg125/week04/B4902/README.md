# 🔺 BOJ 4902 - 삼각형의 값

- 🔗 문제 링크: https://www.acmicpc.net/problem/4902  
- 🏷️ 분류: 누적합 / 브루트포스

---

## 💡 접근 아이디어 / 시행착오
- 그냥 다 더하면서 브루트포스 해도 시간초과가 안 나지 않을까? -> 시간초과
- 누적합을 쓰자

---

## 🛠️ 구현 포인트
```
triSum = new int[N + 1][2 * N];
for (int i = 1; i <= N; i++)
	for (int j = 1; j <= 2 * i - 1; j++)
		triSum[i][j] = triSum[i][j - 1] + Integer.parseInt(st.nextToken());
```
- 누적합 배열에 저장

```
if (j % 2 == 1) {
	int sum = triSum[i][j] - triSum[i][j - 1], len = 1;
	maxSum = Math.max(maxSum, sum);

	for (int k = 1; k < N - i + 1; k++) {
		len += 2;
		sum += triSum[i + k][j + len - 1] - triSum[i + k][j - 1];

		maxSum = Math.max(maxSum, sum);
	}
}
```
- 아래로 확장되는 삼각형

```
else {
	int sum = triSum[i][j] - triSum[i][j - 1], len = 1;
	maxSum = Math.max(maxSum, sum);

	for (int k = 1; k < i; k++) {
		len += 2;
		if (j - len < 0 || j > 2 * (i - k) - 1)
			break;

		sum += triSum[i - k][j] - triSum[i - k][j - len];

		maxSum = Math.max(maxSum, sum);
	}
}
```
- 위로 확장되는 역삼각형

---

## 📝 느낀 점
- 연속되는 구간의 합을 구할 때 누적합을 떠올리자
- 인덱스 값을 0부터 했다가 고생했다.. 웬만하면 1부터 하자
