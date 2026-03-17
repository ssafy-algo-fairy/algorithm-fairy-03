package week03.B9251;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] a = br.readLine().toCharArray();
		char[] b = br.readLine().toCharArray();
		
		int[][] dp = new int[a.length][b.length];
		
		for (int i = 0; i < a.length; i++) {
			int max = 0;
			for (int j = 0; j < b.length; j++) {
				max = i == 0 || j == 0 ? 0 : Math.max(max, dp[i-1][j-1]);
				
				if (i != 0 && dp[i-1][j] != 0) dp[i][j] = dp[i-1][j];
				if (a[i] == b[j]) dp[i][j] = Math.max(max+1, dp[i][j]);
			}
		}
		
		int answer = -1;
		for (int j = 0; j < b.length; j++) {
			answer = Math.max(answer, dp[a.length-1][j]);
		}
		
		System.out.println(answer);
	}

}
