import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_32645 {
    static ArrayList<ArrayList<Integer>> edges;
    static int[] dp;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        edges = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            edges.add(new ArrayList<>());
        }

        for(int i=0; i<n-1; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        dp = new int[n+1];
        visited = new boolean[n+1];
        visited[1] = true;
        find(1);

        for (int i=1; i<=n; i++) {
            if (dp[i] == 1) {
                System.out.println("donggggas");
            } else {
                System.out.println("uppercut");
            }
        }
    }
    //동우가 시작 동점이면 혁준, 아니면 동우 맨끝은 혁준
    // 동우가 1번, 혁준이가 2번
    static int find(int now) {
        visited[now] = true;
        if (dp[now] != 0) return dp[now];
        

        int temp = 0;
        for (int next : edges.get(now)) {
            if (visited[next]) continue;
            temp = Math.max(temp, find(next));
        }

        if (temp == 1 || temp == 0) {
            dp[now] = 2;
        } else {
            dp[now] = 1;
        }
        return dp[now];
    }
}