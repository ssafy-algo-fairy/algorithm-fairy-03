# 🚌 BOJ 17842 - 버스 노선 (#62)

- 🔗 문제 링크: https://www.acmicpc.net/problem/17842  
- 🏷️ 분류: 트리 / 그리디

---

## 💡 접근 아이디어 / 시행착오
- 결국 새로운 노선을 만들 땐 리프때문에 만드는 거임
- 중간거는 겹치든 안겹치든 한번은 지나갈거기 때문..
- 리프가 홀수개일수도 있기 때문에 나머지를 더해준다.

---

## 🛠️ 회고
- 어제꺼 너무 어려워서 회피했는데 다시 풀어봐야겠다.

---

## 📝 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17842 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] connected = new int[n];
        
        for (int i=0; i<n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            connected[num1]++;
            connected[num2]++;
        }

        int count = 0;
        for (int i=0; i<n; i++) {
            if (connected[i] == 1) count++;
        }

        System.out.println(count/2 + count%2);

    }
}
```