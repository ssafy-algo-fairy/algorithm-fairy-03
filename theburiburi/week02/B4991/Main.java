package theburiburi.week02.B4991;

import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int w, h;
    static char arr[][];

    static int dy[] = {1, -1, 0, 0};
    static int dx[] = {0, 0, 1, -1};
    static int startY, startX, dustCnt;

    static Map<Integer, Integer> map;
    public static void main(String args[])throws IOException{
        readInput();
        System.out.println(sb.toString());
    }

    public static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w == 0 && h == 0) break;

            startY = 0;
            startX = 0;
            dustCnt = 0;
            arr = new char[h][w];
            map = new HashMap<>();
            for(int i=0; i<h; i++){
                String str = br.readLine();
                for(int j=0; j<w; j++){
                    arr[i][j] = str.charAt(j);
                    if(arr[i][j] == 'o'){
                        startY = i;
                        startX = j;
                    }
                    else if(arr[i][j] == '*'){
                        map.put(makeKey(i, j), dustCnt++);
                    }
                }
            }
            sb.append(solve()).append("\n");
        }
    }

    public static int solve(){
        boolean visited[][][] = new boolean [h][w][1<<dustCnt];
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{startY, startX, 0, 0});
        visited[startY][startX][0] = true;

        while(!que.isEmpty()){
            int now[] = que.poll();
            
            for(int i=0; i<4; i++){
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                int bit = now[2];

                if(ny < 0 || ny >= h || nx < 0 || nx >= w) continue;

                if(arr[ny][nx] == '*'){
                    int num = map.get(makeKey(ny, nx));
                    if((bit & (1<<num)) == 0){
                        bit = bit | (1<<num);
                    }
                }
                else if(arr[ny][nx] == 'x') continue;

                if(bit == (1 << dustCnt)-1){
                    return now[3]+1;
                } 

                if(visited[ny][nx][bit]) continue;
                que.add(new int[]{ny,nx,bit,now[3]+1});
                visited[ny][nx][bit] = true;
            }
        }
        
        return -1;
    }

    public static int makeKey(int y, int x){
        return (y * 100 + x);
    }
}
