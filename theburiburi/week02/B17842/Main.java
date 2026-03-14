package theburiburi.week02.B17842;

import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int N;
    static int arr[];
    public static void main(String args[])throws IOException{
        inputFile();
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            arr[left]++;
            arr[right]++;
        }

        int count = 0;
        for(int i=0; i<N; i++){
            if(arr[i] == 1) count++;
        }
        System.out.println((count+1)/2);
    }
}
