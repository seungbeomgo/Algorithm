package C11;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q02 {

	public static void main(String[] args) {
		
		/* Input From Keyboard */
		Scanner scan = new Scanner(System.in);
		String line = scan.next();
		scan.close();
		
		/* Solve Problem */
		List<Integer> numbers = new ArrayList<Integer> ();
		for(int i=0; i<line.length(); i++) {
			numbers.add(Integer.parseInt(line.substring(i, i+1)));
		}
		
		int current = numbers.get(0);
		for(int i=1; i<numbers.size(); i++) {
			int next = numbers.get(i);
			if(current == 0 || current == 1) {
				current += next;
			} else {
				if(next == 0 || next == 1) {
					current += next;
				} else {
					current *= next;
				}
			}
		}
		System.out.println(current);
	}
}