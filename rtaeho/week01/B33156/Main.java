package B33156;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;

        // 중앙을 기준으로 하나씩 늘려가며 검사
        for (int mid = 0; mid < N - 1; mid++) {
            // 왼쪽과 오른쪽의 key의 개수 차이를 value로 저장하는 map
            map = new HashMap<>();
            int notZeroCnt = 0;
            int left = mid;
            int right = mid + 1;

            while (left >= 0 && right < N) {
                notZeroCnt = check(arr[left], arr[right], notZeroCnt);

                if (notZeroCnt == 0) {
                    cnt = Math.max(cnt, right - left + 1);
                }

                left--;
                right++;
            }
        }

        System.out.println(cnt);
    }

    static int check(int leftNum, int rightNum, int notZeroCnt) {
        // 왼쪽 배열 검사

        // 이전 배열에서의 차이 가져오기
        int prevDiff = map.getOrDefault(leftNum, 0);
        // 새로 추가했으니까 diff + 1
        int curDiff = prevDiff + 1;

        // 만약 이전 차이가 없었다면
        if (prevDiff == 0) {
            notZeroCnt++;
        }
        // 이전에 차이가 있었지만 현재 차이가 없어졌다면
        else if (curDiff == 0) {
            notZeroCnt--;
        }

        // 현재 차이가 없다면 이제 필요없음
        if (curDiff == 0) {
            map.remove(leftNum);
        }
        // 아니면 추가
        else {
            map.put(leftNum, curDiff);
        }

        // 오른쪽 배열 검사
        prevDiff = map.getOrDefault(rightNum, 0);
        curDiff = prevDiff - 1;

        if (prevDiff == 0) {
            notZeroCnt++;
        } else if (curDiff == 0) {
            notZeroCnt--;
        }

        if (curDiff == 0) {
            map.remove(rightNum);
        } else {
            map.put(rightNum, curDiff);
        }

        return notZeroCnt;
    }
}