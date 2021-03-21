package C11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q6_1 {
	
	public static int solution(int[] food_times, long k) {
		long sum = 0l;
		// 시간 우선순위 큐 생성
		Comparator<Food> comp_time = (a, b) -> {
			return a.remain - b.remain;
		};
		PriorityQueue<Food> queue_time = new PriorityQueue<> (comp_time);
		for(int i=0; i<food_times.length; i++) {
			sum += food_times[i];
			queue_time.add(new Food(i+1, food_times[i]));
		}
		
		// 기본 우선순위 큐 생성
		Comparator<Food> comp_index = (a, b) -> {
			return a.index - b.index;
		};
		PriorityQueue<Food> queue_index = new PriorityQueue<> (comp_index);
		
		// 전체 시간 합보다 k가 크면 시간안에 음식을 다 먹은 것이므로 종료
		if(k>=sum) {
			return -1;
		}
	
		int min = queue_time.peek().remain;
		int time = 0;
		int cycle = queue_time.size();
		long total = k;
		// cycle이 남은 기회보다 작아질 때까지 반복
		while(total - (long) cycle * min >= 0l) {
			if(total == (long) cycle * min) {
				// 같으면 무조건 첫번째 음식부터 먹으면 된다
				queue_time.poll();
				queue_index.addAll(queue_time);
				return queue_index.peek().index;
			}
			time += min;
			total -= cycle * min;
			queue_time.poll();
			min = queue_time.peek().remain - time;
			cycle--;
		}
		queue_index.addAll(queue_time);
		for(long i=0; i<total%(long)cycle; i+=1l) {
			queue_index.poll();
		}
		return queue_index.peek().index;
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

class Food {
	int index;
	int remain;
	
	public Food(int index, int remain) {
		this.index = index;
		this.remain = remain;
	}
}