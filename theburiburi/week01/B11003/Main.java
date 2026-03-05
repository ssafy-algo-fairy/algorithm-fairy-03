package theburiburi.week01.B11003;

import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int N, L;
    static int arr[];
    public static void main(String args[])throws IOException{
        inputFile();
        System.out.println(sb.toString());
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());;

        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N+1];

        Deque<Integer> dq = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=N; i++){
            while(!dq.isEmpty() && arr[dq.peekLast()] > arr[i]){
                dq.removeLast();
            }
            
            dq.addLast(i);
            if(dq.peekFirst() <= i-L ){
                dq.removeFirst();
            }

            sb.append(arr[dq.peekFirst()]).append(" ");
        }
        
    }
}