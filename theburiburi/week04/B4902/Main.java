package theburiburi.week04.B4902;

import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int N, ans;
    static int[][] preSum, tree;
    public static void main(String args[])throws IOException{
        inputFile();
        System.out.println(sb.toString());
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        int idx = 1;
        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            ans = Integer.MIN_VALUE;

            if (N == 0) break;

            tree = new int[N+1][2*N];
            preSum = new int[N+1][2*N];

            for(int i=1; i<=N; i++){
                for(int j=1; j<i*2; j++){
                    tree[i][j] = Integer.parseInt(st.nextToken());
                    preSum[i][j] = preSum[i][j-1] + tree[i][j];
                }
            }

            for(int i=1; i<=N; i++){
                for(int j=1; j<i*2; j++){
                    if(j%2 == 1){
                        makeTriangle(i, j);
                    }
                    else{
                        makeReverseTriangle(i,j);
                    }
                }
            }

            sb.append(idx++).append(". ").append(ans).append("\n");
        }
    }

    static void makeTriangle(int y, int x){
        int sum = 0;
        int idx = 0;
        for(int i=y; i<=N; i++){
            sum += (preSum[i][x+(idx*2)] - preSum[i][x-1]);
            ans = Math.max(sum, ans);
            idx++;
        }
    }

    static void makeReverseTriangle(int y, int x){
        int sum = 0;
        int idx = 0;
        for(int i=y; i>0; i--){
            if(x > i*2-1) break;
            if(x-1-(idx*2) < 0) break;
            sum += (preSum[i][x] - preSum[i][x-1-(idx*2)]);
            ans = Math.max(sum, ans);
            idx++;
        }
    }
}
