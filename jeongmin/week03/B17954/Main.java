package week03.B17954;

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb0 = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		if (n == 1) {
			sb.append(2).append("\n").append(1).append("\n").append(2);
			System.out.println(sb);
			return;
		}
		
		long sum = 0;
		for (int i = 1; i <= 2*n; i++) {
			sum += i;
		}
		
		long answer = 0;
		for (int t = 1; t < 2*n; t++) {
			if (t < n) {
				sum -= 2*n-2-t;
				sb.append(2*n-2-t).append(" ");
			}
			else if (t == n) {
				sum -= 2*n-2;
				sb.append(2*n-2).append("\n");
			}
			else if (t == n+1) {
				sum -= 2*n-1;
				sb.append(2*n-1).append(" ");
			}
			else {
				sum -= 2*n-t;
				sb.append(2*n-t).append(" ");
			}
			
			answer += sum*t;
		}
		
		sb.append(2*n);
		sb0.append(answer).append("\n");
		sb0.append(sb);
		
		System.out.println(sb0);
	}

}
