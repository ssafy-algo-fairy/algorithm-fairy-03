package theburiburi.week01.B2423;
import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int dx[] = {1, 1, -1, -1}; //우하 우상 죄하 좌상 \ / / \
    static int dy[] = {1, -1, 1, -1};

    static char targetShape[] = {'\\', '/', '/', '\\'};
    static int correctionY[] = {0, -1, 0, -1};
    static int correctionX[] = {0, 0, -1, -1};

    static int N, M;
    static char[][] arr;
    static int[][] cost;

    static class Node implements Comparable<Node>{
        int y;
        int x;
        int cost;
        public Node(int y, int x, int cost){
            this.y = y;
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(cost, o.cost);
        }
    }
    public static void main(String args[])throws IOException{
        inputFile();
        solve();
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        for(int i=0; i<N; i++){
            arr[i] = br.readLine().toCharArray();
        }

        cost = new int[N+1][M+1];
        for(int i=0; i<=N; i++){
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
    }

    static void solve(){
        if((N+M) % 2 == 1){
            System.out.println("NO SOLUTION");
            return;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 0));
        cost[0][0] = 0;
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(now.cost > cost[now.y][now.x]) continue;

            for(int i=0; i<4; i++){
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if(ny < 0 || ny > N || nx < 0 || nx > M) continue;

                int targetY = now.y + correctionY[i];
                int targetX = now.x + correctionX[i];

                int nextCost = arr[targetY][targetX] == targetShape[i] ? 0 : 1;
                
                if(cost[ny][nx] <= now.cost + nextCost) continue;

                cost[ny][nx] = now.cost+nextCost;
                pq.add(new Node(ny, nx, cost[ny][nx]));
            }

        }
        System.out.println(cost[N][M]);
    }
}
