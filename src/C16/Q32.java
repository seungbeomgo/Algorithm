package C16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q32 {
	public static int n;
	public static int[][] triangle;

	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer stringTokenizer = new StringTokenizer(line);
		n = Integer.parseInt(stringTokenizer.nextToken());

		triangle = new int[n][];
		for(int i=0; i<n; i++) {
			triangle[i] = new int[i+1];
			line = br.readLine();
			stringTokenizer = new StringTokenizer(line);
			for(int j=0; j<i+1; j++) {
				triangle[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		// DP 배열 초기화
		int[][] dp = new int[n][];
		for(int i=0; i<n; i++) {
			dp[i] = new int[i+1];
		}
		
		// DP 계산
		dp[0][0] = triangle[0][0];
		for(int i=1; i<n; i++) {
			for(int j=0; j<i+1; j++) {
				if(j==0) {
					dp[i][j] = dp[i-1][j] + triangle[i][j];
				} else if(j==i) {
					dp[i][j] = dp[i-1][j-1] + triangle[i][j];
				} else {
					dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
				}
			}
		}
		
		// 최대값 구하기
		int max = -1;
		for(int i=0; i<n; i++) {
			max = (dp[n-1][i] > max) ? dp[n-1][i] : max;
		}
		System.out.println(max);
	}
}