package com.ssafy.algo.fairy.m3.week02.B17842;

import java.util.*;
import java.io.*;

public class b17842 {

	static int N, endCnt;
	static int[] connected;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		connected = new int[N];
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			connected[Integer.parseInt(st.nextToken())]++;
			connected[Integer.parseInt(st.nextToken())]++;
		}

		for (int i = 0; i < N; i++)
			if (connected[i] == 1)
				endCnt++;

		if (endCnt % 2 == 1)
			endCnt++;
		System.out.println(endCnt / 2);

	}

}
