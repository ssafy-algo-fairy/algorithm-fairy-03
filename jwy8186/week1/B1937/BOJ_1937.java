import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1937 {

    static int n;
    static int[][] map;
    static int[][] memo;
    static int[] dx = {0, -1, 0 ,1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        memo = new int[n][n];

        for (int i=0; i<n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j=0; j<n; j++) {
                memo[i][j] = -1;
            }
        }

        int result = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                result = Math.max(result, find(i, j));
            }
        }
        System.out.println(result);
    }

    static int find(int sx, int sy) {
        if (memo[sx][sy] != -1) {
            return memo[sx][sy];
        }

        int result = 1;
        for (int i=0; i<4; i++) {
            int nx = sx + dx[i], ny = sy + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (map[sx][sy] >= map[nx][ny]) continue;
            result = Math.max(result, find(nx, ny) + 1);
        }
        memo[sx][sy] = result;

        return result;
    }

}