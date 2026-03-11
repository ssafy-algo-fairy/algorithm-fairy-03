
/**
 * 최솟값 찾기
 */
import java.util.*;
import java.io.*;

public class Main {
    static int N, L;
    static int[] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            // 윈도우 범위 밖 제거
            if (dq.peekFirst() <= i - L) {
                dq.pollFirst();
            }

            // 뒤에서 현재 값보다 큰 값 제거
            while (!dq.isEmpty() && A[dq.peekLast()] > A[i]) {
                dq.pollLast();
            }

            // 현재 값 추가
            dq.offerLast(i);

            sb.append(A[dq.peekFirst()]).append(' ');
        }

        System.out.print(sb);

    }
}

/**
 * N 500만 개임
 * 매번 L 개를 탐색하면 O(N * L)
 * 우선순위 큐 O(N log N)
 * Deque O(N), sliding window
 */