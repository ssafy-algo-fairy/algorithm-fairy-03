package week1.B1256;

import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] dp;
	static final int MAX = 1000000001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // a 개수
		int a = n;
		int m = Integer.parseInt(st.nextToken()); // z 개수
		int z = m;
		int k = Integer.parseInt(st.nextToken());
		
		dp = new int[201][101];
		
		char[] output = new char[n+m+1];
		
		int index = 1;
		
		while (index < n+m) {
			int i = 0;
			
			for (; i <= a; i++) {
				int c = comb(z-1+i, i);
				if (k > c) k -= c;
				else break;
				
				if (i == a) {
					System.out.println(-1);
					return;
				}
			}
			
			if (i == 0) {
				for (int j = index; j < m+n+1; j++) {
					if (a > 0) {
						output[index++] = 'a';
						a--;
					} else output[index++] = 'z';			
				}
			} else {
				int count = a-i;
				for (int j = 0; j < count; j++) {
					output[index++] = 'a';
					a--;
				}
				output[index++] = 'z';
				z--;
			}
			
			if (z == 0) {
				while (index <= m+n) {
					output[index++] = 'a';
				}	
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n+m; i++) {
			sb.append(output[i]);
		}
		System.out.println(sb);
	}
	
	public static int comb(int n, int r) {
		if (dp[n][r] != 0) return dp[n][r];
		
		if (r == 1) return dp[n][r] = n;
		if (r == 0) return dp[n][r] = 1;
		if (n == r) return dp[n][r] = 1;
		
		return dp[n][r] = Math.min(MAX, comb(n-1,r) + comb(n-1,r-1));
	}

}
