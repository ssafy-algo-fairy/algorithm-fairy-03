package B11003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        Deque<int[]> deque = new ArrayDeque<>();
        // deque에 값과 인덱스를 저장하여 범위 비교도 할 수 있도록 함
        // [0]: 인덱스, [1]: 값
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());

            while (!deque.isEmpty() && cur < deque.peekLast()[1]) {
                // 만약 현재 값이 deque의 마지막 값보다 작다면
                // 이제 마지막 값은 안 씀
                deque.removeLast();
            }

            deque.addLast(new int[]{i, cur});

            if (deque.peekFirst()[0] <= i - L) {
                // 첫번째 값의 인덱스가 L 범위를 벗어나면 이제 안 씀
                deque.removeFirst();
            }

            sb.append(deque.peekFirst()[1]).append(" ");
        }

        System.out.println(sb);
    }
}