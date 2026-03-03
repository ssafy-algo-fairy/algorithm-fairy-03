import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Time {
		int t;
		int mos;
		
		public Time(int t, boolean isStart) {
			super();
			this.t = t;
			this.mos = isStart ? 1 : -1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		List<Time> times = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			times.add(new Time(s, true));
			times.add(new Time(e, false));
		}
		
		times.sort((a, b) -> a.t - b.t);
		
		int now = 0;
		boolean now_max = false;
		int max_start = 0;
		int max_end = 0;
		int max_mos = 0;
		int mos = 0;
		for (int i = 0; i < 2 * n; i++) {
			now = times.get(i).t;
			while (i < 2 * n && times.get(i).t == now)
				mos += times.get(i++).mos;
			i--;
			if (mos > max_mos) {
				max_mos = mos;
				max_start = now;
				now_max = true;
			}
			else if (mos < max_mos && now_max) {
				now_max = false;
				max_end = now;
			}
		}
		
		System.out.println(max_mos);
		System.out.println(max_start + " " + max_end);
	}
}
