package C11;

import java.util.Scanner;

public class Q03 {
	
	public static void main(String[] args) {
		
		/* Input From Keyboard */
		Scanner scan = new Scanner(System.in);
		String line = scan.next();
		scan.close();
		
		/* Solve Problem */
		int count = 0;
		char current = line.charAt(0);
		for(int i=1; i<line.length(); i++) {
			char next = line.charAt(i);
			if(current != next) {
				current = next;
				count++;
			}
		}
		System.out.println((count+1)/2);
	}
}
