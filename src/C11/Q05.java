package C11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Q05 {

	public static void main(String[] args) throws IOException {
		
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] balls = null;
		balls = new int[N];
		for(int i=0; i<N; i++) {
			balls[i] = Integer.parseInt(st.nextToken());
		}
		
		/* Solve Problem */
		Set<Integer> weightSet = new HashSet<Integer> ();
		for(int i=0; i<N; i++) {
			weightSet.add(balls[i]);
		}
		int result = (((N - 1) * N) / 2) - (N - weightSet.size());
		System.out.println(result);
		br.close();
	}

}
