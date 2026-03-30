import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main  {
	static List<Integer>[] adj;
	static boolean[] d_win;
	
	static boolean dfs(int node, int parent) {
		if (adj[node].size() == 1 && node != parent) {
			d_win[node] = false;
			return false;
		}
		boolean ret_val = false;
		for (int c : adj[node]) {
			if (c == parent)
				continue;
			if (dfs(c, node) == false) {
				ret_val = true;
			}
		}
		d_win[node] = ret_val;
		return ret_val;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[n + 1];
		d_win = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		dfs(1, 1);
		for (int i = 1; i <= n; i++) {
			bw.append(d_win[i] ? "donggggas\n" : "uppercut\n");
		}
		bw.flush();
		bw.close();
	}
}