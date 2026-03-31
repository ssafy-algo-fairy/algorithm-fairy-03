import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main  {
	
	static int n;
	static int[] w;
	static List<Integer>[] tree;
	
	static List<Integer> max_group;
	static int[][] dp;
	
	static int dfs(int node, int parent, boolean select_parent) {
		int sel_max  = 0;
		if (!select_parent) {
			if (dp[node][1] != 0)
				sel_max = dp[node][1];
			else {
				sel_max = w[node];
				for (int child : tree[node]) {
					if (child == parent)
						continue;
					sel_max += dfs(child, node, true);
				}
				dp[node][1] = sel_max;
			}
		}
		
		int no_sel_max = 0;
		if (dp[node][0] != 0)
			no_sel_max = dp[node][0];
		else {
			for (int child : tree[node]) {
				if (child == parent)
					continue;
				no_sel_max += dfs(child, node, false);
			}
			dp[node][0] = no_sel_max;
		}
		
		return Math.max(sel_max, no_sel_max);
	}
	
	static void findset(int node, int parent, boolean parent_select) {
		if (parent_select || dp[node][0] > dp[node][1]) {
			for (int child : tree[node]) {
				if (child == parent)
					continue;
				findset(child, node, false);
			}
		}
		else {
			max_group.add(node);
			for (int child : tree[node]) {
				if (child == parent)
					continue;
				findset(child, node, true);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		w = new int[n + 1];
		tree = new ArrayList[n + 1];
		max_group = new ArrayList<>();
		dp = new int[n + 1][2];
		for (int i = 1; i <= n; i++) {
			tree[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			w[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		
		int max = dfs(1, 0, false);
		
		findset(1, 0, false);
		
		bw.append(max + "\n");
		max_group.sort((a, b) -> a - b);
		for (int i: max_group) {
			bw.append(i + " ");
		}
		bw.append("\n");
		bw.flush();
		bw.close();
	}
}