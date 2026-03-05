import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_11003 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {

            while (q.peekLast() != null && q.peekLast()[0] > arr[i]) q.pollLast();

            q.addLast(new int[]{arr[i], i});

            while (q.peekFirst() != null && q.peekFirst()[1] <= i - l) q.pollFirst();

            sb.append(q.peekFirst()[0]).append(" ");
        }

        System.out.println(sb);
    }
}
