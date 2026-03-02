package B20440;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        long[] in = new long[N];
        long[] out = new long[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            in[i] = Long.parseLong(st.nextToken());
            out[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(in);
        Arrays.sort(out);

        int i = 0, j = 0;
        int cur = 0;
        int max = 0;

        long bestL = 0, bestR = 0;

        // 다음 이벤트 시각을 따라가며 구간 [t, nextT)에서의 cur을 기록
        while (i < N || j < N) {
            long t;
            if (j == N) {
                t = in[i];
            } else if (i == N) {
                t = out[j];
            } else {
                t = Math.min(in[i], out[j]);
            }

            // [t, nextT)로 넘어가기 전에, t에서 퇴장 먼저 처리해야됨
            // 그래서 in[i] < out[j]일 때만 입장을 처리

            // t에서 퇴장 처리
            while (j < N && out[j] == t) {
                cur--;
                j++;
            }

            // t에서 입장 처리
            while (i < N && in[i] == t) {
                cur++;
                i++;
            }

            // 다음 시간 구하기
            if (i == N && j == N) {
                break;
            }
            long nextT;
            if (j == N) {
                nextT = in[i];
            } else if (i == N) {
                nextT = out[j];
            } else {
                nextT = Math.min(in[i], out[j]);
            }

            // 구간 [t, nextT) 동안 cur명이 존재
            if (t < nextT) {
                // 길이가 0인 구간은 무시
                if (cur > max) {
                    max = cur;
                    bestL = t;
                    bestR = nextT;
                } else if (cur == max && max > 0) {
                    // 여러 구간이면 가장 빠른 시작을 출력해야 하므로
                    // 이미 bestL이 더 빠르면 그대로 둠
                    if (bestR == t) {
                        // 단, 같은 max 구간이 바로 이어지면 bestR만 늘려줘야 함
                        bestR = nextT;
                    }
                }
            }
        }

        System.out.println(max);
        System.out.print(bestL + " " + bestR);
    }
}