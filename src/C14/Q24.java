package C14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q24 {
	
	public static int N;
	public static Integer[] houses;
	
	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer stringTokenizer = new StringTokenizer(line);
		N = Integer.parseInt(stringTokenizer.nextToken());
		
		PriorityQueue<Integer> houseQueue = new PriorityQueue<> ();
		line = br.readLine();
		stringTokenizer = new StringTokenizer(line);
		for(int i=0; i<N; i++) {
			houseQueue.offer(Integer.parseInt(stringTokenizer.nextToken()));
		}
		
		houses = new Integer[N];
		for(int i=0; i<N; i++) {
			houses[i] = houseQueue.poll();
		}
		
		int answer = 0;
		answer = houses[(N-1)/2];
		System.out.println(answer);
	}
}