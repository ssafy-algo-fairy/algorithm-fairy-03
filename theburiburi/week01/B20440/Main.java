package theburiburi.week01.B20440;

import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int N;
    static Node[] arr;
    public static void main(String args[])throws IOException{
        inputFile();
        solve();
        System.out.println(sb.toString());
    }

    static class Node implements Comparable<Node>{
        int time;
        boolean isStart;

        public Node(int time, boolean isStart){
            this.time = time;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(time, o.time);
        }
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new Node[N*2];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[i*2] = new Node(start, true);
            arr[i*2 + 1] = new Node(end, false);
        }
        Arrays.sort(arr);
    }

    static void solve(){
        int maxCount = 0;
        int curCount = 0;
        boolean isMax = false;
        int maxStartTime = 0;
        int maxEndTime = 0;
        for(int i=0; i<2*N; i++){
            if(arr[i].isStart) curCount++;
            else curCount--;

            if(i+1 < 2*N && arr[i].time == arr[i+1].time) continue;
            
            if(curCount > maxCount){
                maxCount = curCount;
                isMax = true;
                maxStartTime = arr[i].time;
            }
            else if(curCount < maxCount && isMax){
                maxEndTime = arr[i].time;
                isMax = false;
            }
        }
        sb.append(maxCount).append("\n");
        sb.append(maxStartTime).append(" ").append(maxEndTime);
    }
}
