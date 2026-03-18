package week03.B16566;

import java.util.*;
import java.io.*;

public class Main2 {
	
	static int n;
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		p = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int a = Integer.parseInt(st.nextToken());
			p[a] = a;
		}
		
		for (int i = n-1; i > 0; i--) {
			if (p[i] == 0) p[i] = p[i+1];
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			sb.append(find(Integer.parseInt(st.nextToken())+1)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int find(int a) {
		if (p[a] == a) {
			p[a] = p[a+1];
			return a;
		}
		
		return p[a] = find(p[a]);
	}
}
