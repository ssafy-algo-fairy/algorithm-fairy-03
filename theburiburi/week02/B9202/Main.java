package theburiburi.week02.B9202;

import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int w, b;
    static Node root;
    static char arr[][];

    static int maxScore, wordCnt;
    static String ansStr;
    static Set<String> set;

    static int dy[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int dx[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int scores[] = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    static class Node{
        Map<Character, Node> map = new HashMap<>();
        String word;
    }

    public static void main(String args[])throws IOException{
        inputFile();
        System.out.println(sb.toString());
    }
    static void inputFile()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        root = new Node();
        w = Integer.parseInt(br.readLine());

        for(int i=0; i<w; i++){
            String str = br.readLine();
            Node now = root;
            for(int j=0; j<str.length(); j++){
                now = now.map.computeIfAbsent(str.charAt(j), k -> new Node());
            }
            now.word = str;
        }
        br.readLine();

        b = Integer.parseInt(br.readLine());
        arr = new char[4][4];
        for(int i=0; i<b; i++){
            for(int j=0; j<4; j++){
                arr[j] = br.readLine().toCharArray();
            }

            maxScore = 0;
            ansStr = "";
            wordCnt = 0;
            set = new HashSet<>();
            br.readLine();
            solve();
            sb.append(maxScore).append(" ").append(ansStr).append(" ").append(wordCnt).append("\n");
        }
    }

    static void solve(){
        Node now = root;
        boolean visited[][] = new boolean[4][4];
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(now.map.containsKey(arr[i][j])){
                    visited[i][j] = true;
                    dfs(now.map.get(arr[i][j]), i, j, visited);
                    visited[i][j] = false;
                }
            }
        }
    }

    static void dfs(Node now, int y, int x, boolean visited[][]){
        if(now.word != null && !set.contains(now.word)){ //한 번만 찾기, 가장 긴 거중에 사전순 앞선거
            if(ansStr.length() < now.word.length()){
                ansStr = now.word;
            }
            else if(ansStr.length() == now.word.length()){
                if(ansStr.compareTo(now.word) > 0){
                    ansStr = now.word;
                }
            }

            maxScore += scores[now.word.length()];
            wordCnt++;
            set.add(now.word);
        }

        for(int i=0; i<8; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            if(ny < 0 || ny >= 4 || nx < 0 || nx >= 4) continue; 
            if(visited[ny][nx]) continue;

            if(now.map.containsKey(arr[ny][nx])){
                visited[ny][nx] = true;
                dfs(now.map.get(arr[ny][nx]), ny, nx, visited);
                visited[ny][nx] = false;
            }

        }

    }
}
