package week04.B4902;

import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	static int[][] triangle;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			if (n == 0) break;
			
			sb.append(tc).append(". ");
			
			triangle = new int[n+1][2*n];
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j < 2*i; j++) {
					triangle[i][j] = Integer.parseInt(st.nextToken()) + triangle[i][j-1];
				}
			}
			
			max = Integer.MIN_VALUE;
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j < 2*i; j++) {
					run(i, j);
				}
			}
			
			sb.append(max).append("\n");
			tc++;
		}
		System.out.println(sb);
	}
	
	public static void run(int i, int j) {
		int sum = triangle[i][j] - triangle[i][j-1];
		max = Math.max(sum, max);
		
		boolean up = false;
		if (j%2 == 0) up = true;
		
		for (int c = 1; c < n; c++) {
			int x = up? i-c : i+c;
			int lower = up? j-2*c : j;
			int upper = up? j : j+2*c;
			
			if (lower <= 0 || upper >= 2*x || x <= 0 || x > n) return;
			
			sum += triangle[x][upper] - triangle[x][lower-1];
			
			max = Math.max(sum, max);
		}		
	}
}
