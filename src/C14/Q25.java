package C14;

import java.util.Arrays;
import java.util.Collections;

public class Q25 {
	
	public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        Stage[] stageArray = new Stage[N];
        for(int i=0; i<N; i++) {
        	Stage stage = null;
        	int fail = 0, total = 0;
        	for(int j=0; j<stages.length; j++) {
        		if(stages[j] >= i+1) {
        			total++;
        			if(stages[j] == i+1) {
        				fail++;
        			}
        		}
        	}
        	if(fail == 0) {
        		stage = new Stage(i+1, 0);
        	} else {
        		stage = new Stage(i+1, (float) fail / total);
        	}
        	stageArray[i] = stage;
        }
        Arrays.sort(stageArray, Collections.reverseOrder());
        for(int i=0; i<N; i++) {
        	answer[i] = stageArray[i].number;
        }
        return answer;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 7;
		int stages[] = {1, 2, 2, 3, 4, 5};
		int[] result = solution(N, stages);
		for(int i=0; i<N; i++) {
			System.out.print(result[i]);
		}
	}

	public static class Stage implements Comparable {
		int number;
		float rate;
		
		public Stage(int number, float rate) {
			this.number = number;
			this.rate = rate;
		}

		@Override
		public int compareTo(Object o) {
			Stage stage = (Stage) o;
			if (this.rate < stage.rate) {
	            return -1;
	        } else if (this.rate == stage.rate) {
	            return 0;
	        } else {
	            return 1;
	        }
		}
	}
}