package theburiburi.week01.B33156;

import java.io.*;
import java.util.*;

public class Main{
    static int N;
    static int arr[];
    static int ans;
    public static void main(String args[])throws IOException{
        inputFile();
        solve();
        System.out.println(ans);
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        for (int mid = 0; mid < N - 1; mid++) {
            Map<Integer, Integer> map = new HashMap<>();
            int diffCnt = 0;

            for (int offset = 0;  0 <= mid - offset  && mid + 1 + offset < N; offset++) {
                int leftVal = arr[mid - offset];
                int leftCnt = map.getOrDefault(leftVal, 0);
                if (leftCnt == 0) diffCnt++;
                map.put(leftVal, leftCnt + 1);
                if (map.get(leftVal) == 0) diffCnt--;

                int rightVal = arr[mid + 1 + offset];
                int rightCnt = map.getOrDefault(rightVal, 0);
                if (rightCnt == 0) diffCnt++;
                map.put(rightVal, rightCnt - 1);
                if (map.get(rightVal) == 0) diffCnt--;

                if (diffCnt == 0) {
                    ans = Math.max(ans, (offset + 1) * 2);
                }
            }
        }
    }
}
