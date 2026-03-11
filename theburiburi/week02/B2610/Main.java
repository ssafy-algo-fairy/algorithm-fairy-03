package theburiburi.week02.B2610;

import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int N, M;
    static int arr[][];
    static final int INF = 1000;
    public static void main(String args[])throws IOException{
        inputFile();
        solve();
        System.out.println(sb.toString());
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        for(int i=0; i<N+1; i++){
            Arrays.fill(arr[i], INF);
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            arr[left][right] = 1;
            arr[right][left] = 1;
        }
    }

    static void solve(){
        for(int i=1; i<=N; i++){
            arr[i][i] = 0;
        }

        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                if(k==i) continue;
                for(int j=1; j<=N; j++){
                    if(arr[i][j] > arr[i][k] + arr[k][j]){
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }

        boolean visited[] = new boolean[N+1];
        List<Integer> represents = new ArrayList<>();

        for(int i=1; i<=N; i++){
            if(visited[i]) continue;
            
            List<Integer> list = new ArrayList<>();
            for(int j=1; j<=N; j++){
                if(arr[i][j] != INF){
                    list.add(j);
                    visited[j] = true;
                }
            }


            int realMaxDistance = INF;
            int representNum = i;
            for(int represent : list){
                int maxDistance = 0;
                for(int other : list){
                    maxDistance = Math.max(maxDistance, arr[represent][other]);
                }

                if(realMaxDistance > maxDistance){
                    representNum = represent;
                    realMaxDistance = maxDistance;
                }
            }

            represents.add(representNum);
        }

        represents.sort((s1,s2) -> s1.compareTo(s2));

        sb.append(represents.size()).append("\n");
        for(int now : represents){
            sb.append(now).append("\n");
        }
    }
}

