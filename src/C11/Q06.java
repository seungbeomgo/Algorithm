package C11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q06 {
	
	public static int solution(int[] food_times, long k) {	
		int answer = 0;
		int currentFoodIndex = 0;
		for(long i=0l; i<k; i++) {
			if(food_times[currentFoodIndex] > 0) {
				food_times[currentFoodIndex] -= 1;
				currentFoodIndex = (currentFoodIndex+1)%food_times.length;
			}
			// 다음 먹을 것 준비
			// 가장 가까운 음식을 찾아라
			int count = 0;
			while(food_times[currentFoodIndex] == 0) {
				if(count == food_times.length - 1) {
					answer = -1;
					return answer;
				}
				currentFoodIndex = (currentFoodIndex+1)%food_times.length;
				count++;
			}
		}
		answer = currentFoodIndex + 1;
		return answer;
	}

	public static void main(String[] args) throws IOException {

		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		line = line.substring(1, line.length()-1);
		StringTokenizer st = new StringTokenizer(line, ", ");
		
		int[] food_times = null;
		food_times = new int[st.countTokens()];
		for(int i=0; i<food_times.length; i++) {
			food_times[i] = Integer.parseInt(st.nextToken());
		}
		int k = Integer.parseInt(br.readLine());
		br.close();
		
		System.out.println(solution(food_times, k));
	}
}
