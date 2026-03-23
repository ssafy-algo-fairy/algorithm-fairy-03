package theburiburi.week03.B1655;

import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    public static void main(String args[])throws IOException{
        inputFile();
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> maxPq = new PriorityQueue<>((s1,s2) -> s2-s1);
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            int now = Integer.parseInt(br.readLine());
            if(maxPq.size() == minPq.size()){ maxPq.add(now); }
            else{ minPq.add(now); }

            if(!minPq.isEmpty() && maxPq.peek() > minPq.peek()){
                int maxVal = maxPq.poll();
                int minVal = minPq.poll();

                maxPq.add(minVal);
                minPq.add(maxVal);
            }
            sb.append(maxPq.peek()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
