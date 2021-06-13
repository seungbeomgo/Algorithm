package C16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q33 {
	public static int N;
	public static int[] T, P;

	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer stringTokenizer = new StringTokenizer(line);
		N = Integer.parseInt(stringTokenizer.nextToken());

		T = new int[N];
		P = new int[N];
		for(int i=0; i<N; i++) {
			line = br.readLine();
			stringTokenizer = new StringTokenizer(line);
			T[i] = Integer.parseInt(stringTokenizer.nextToken());
			P[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		
		int[] dp = new int[N];
		// 초기값 설정
		if(T[N-1] == 1) {
			dp[N-1] = P[N-1];
		}
		for(int i=N-2; i>=0; i--) {
			// 상담 가능 여부 확인
			if(i+T[i] == N) {
				dp[i] = Math.max(P[i], dp[i+1]);
			} else if(i+T[i] < N) {
				dp[i] = Math.max(dp[i+T[i]] + P[i], dp[i+1]);
			} else {
				dp[i] = dp[i+1];
			}
		}
		System.out.println(dp[0]);
	}
}