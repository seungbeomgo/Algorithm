package C14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q26 {
	
	public static int N;
	public static PriorityQueue<Integer> cardQueue;

	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer stringTokenizer = new StringTokenizer(line);
		N = Integer.parseInt(stringTokenizer.nextToken());

		cardQueue = new PriorityQueue<> ();
		for(int i=0; i<N; i++) {
			line = br.readLine();
			stringTokenizer = new StringTokenizer(line);
			cardQueue.offer(Integer.parseInt(stringTokenizer.nextToken()));
		}
		
		Integer temp = null, pop1 = null, pop2 = null;
		int total = 0;
		while(!cardQueue.isEmpty()) {
			pop1 = cardQueue.poll();
			pop2 = cardQueue.poll();
			if(pop2 == null) {
				break;
			}
			temp = pop1 + pop2;
			total += temp;
			cardQueue.offer(temp);
		}
		System.out.println(total);
	}
}