import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17842 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] connected = new int[n];
        
        for (int i=0; i<n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            connected[num1]++;
            connected[num2]++;
        }

        int count = 0;
        for (int i=0; i<n; i++) {
            if (connected[i] == 1) count++;
        }

        System.out.println(count/2 + count%2);

    }
}