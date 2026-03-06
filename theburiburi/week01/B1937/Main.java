package theburiburi.week01.B1937;

import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int N;
    static int[][] dp, arr;
    static int dx[] = {1, -1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    public static void main(String args[])throws IOException{
        inputFile();
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        dp = new int[N][N];
        arr = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        int ans = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                ans = Math.max(ans, dfs(i, j));
            }
        }
        System.out.println(ans);
    }

    static int dfs(int y, int x){
        if(dp[y][x] != -1) return dp[y][x];

        int next = 1;
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(0>ny || ny >=N || 0 > nx || nx >= N) continue;
            if(arr[ny][nx] > arr[y][x]){
                next = Math.max(next, dfs(ny, nx)+1);
            }
        }

        return dp[y][x] = next;
    }
}