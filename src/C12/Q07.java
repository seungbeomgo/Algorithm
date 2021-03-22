package C12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q07 {

	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		br.close();
		
		String answer = null;
		String leftStr = line.substring(0, line.length()/2);
		String rightStr = line.substring(line.length()/2);
		
		int leftSum = 0, rightSum = 0;
		int left = Integer.parseInt(leftStr);
		int right = Integer.parseInt(rightStr);
		for(int i=0; i<leftStr.length(); i++) {
			// left
			int pow = leftStr.length() - (i + 1);
			leftSum += left / Math.pow(10, pow);
			left %= (Math.pow(10, pow));
			
			// right
			rightSum += right / Math.pow(10, pow);
			right %= (Math.pow(10, pow));
		}
		
		if(leftSum == rightSum) {
			answer = "LUCKY";
		} else {
			answer = "READY";
		}
		System.out.println(answer);
	}
}