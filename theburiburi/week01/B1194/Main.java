package theburiburi.week01.B1194;

import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int N, M;
    static char arr[][];
    static boolean visited[][][];
    static Queue<Node> que;
    public static void main(String args[])throws IOException{
        inputFile();
        System.out.println(solve());
    }

    static class Node{
        int y;
        int x;
        int key;
        int count;
        public Node(int y, int x, int key, int count){
            this.y = y;
            this.x = x;
            this.key = key;
            this.count = count;
        }
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        que = new ArrayDeque<>();
        visited = new boolean[N][M][1<<6];
        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                arr[i][j] = str.charAt(j);            
                if (arr[i][j] == '0'){
                    que.add(new Node(i, j, 0, 0));
                }
            }
        }

    }

    static int solve(){
        int dx[] = {0, 0, 1, -1};
        int dy[] = {1, -1, 0, 0};

        while(!que.isEmpty()){
            Node now = que.poll();
            for(int i=0; i<4; i++){
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                int nowKey = now.key;
                if(!check(ny,nx)) continue;
                
                if(arr[ny][nx] == '1') return now.count+1;

                if('a' <= arr[ny][nx] && arr[ny][nx] <= 'f'){
                    nowKey = nowKey | (1 << arr[ny][nx] - 'a');
                }
                else if('A' <= arr[ny][nx] && arr[ny][nx] <= 'F'){
                    if((nowKey & (1<<(arr[ny][nx]-'A'))) == 0) continue;
                }
                else if(arr[ny][nx] == '#') continue;

                if(visited[ny][nx][nowKey]) continue;

                visited[ny][nx][nowKey] = true;
                que.add(new Node(ny, nx, nowKey, now.count+1));
            }
        }
        return -1;
    }

    static boolean check(int y, int x){
        return 0<= y && y< N && 0<= x && x < M;
    }
}
