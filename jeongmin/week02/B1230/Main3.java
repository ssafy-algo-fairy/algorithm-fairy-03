package week02.B1230;

import java.util.*;
import java.io.*;

public class Main3 {
	
	static char[] o, n;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		o = br.readLine().toCharArray();
		n = br.readLine().toCharArray();
		dp = new int[o.length][n.length];
		
		for (int i = 0; i < o.length; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		
		for (int j = 0; j < n.length; j++) {
			if (n[j] == o[0]) dp[0][j] = j==0? 0 : 1;
		}
		
		for (int i = 1; i < o.length; i++) {
			int min = Integer.MAX_VALUE;
			int index = -1;
			
			for (int j = 1; j < n.length; j++) {
				if (dp[i-1][j-1] != Integer.MAX_VALUE && dp[i-1][j-1] <= min) {
					min = dp[i-1][j-1];
					index = j-1;
				}
				
				if (o[i] == n[j]) {
					if (index == -1) continue;
					dp[i][j] = index == j-1? min : min+1;
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		for (int j = 0; j < n.length; j++) {
			if (dp[o.length-1][j] < min) {
				min = j==n.length-1? dp[o.length-1][j] : dp[o.length-1][j]+1;
			}
		}
		
		if (min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}

}
