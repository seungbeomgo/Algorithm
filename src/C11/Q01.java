package C11;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q01 {
	
	public static void main(String[] args) {
		
		/* Input From Keyboard */
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		List<Integer> x = new ArrayList<Integer> ();
		for(int i=0; i<n; i++) {
			x.add(scan.nextInt());
		}
		scan.close();
		
		/* Solve Problem */
		
		// sort asc
		x.sort(null);
		
		List<List<Integer>> total = new ArrayList<List<Integer>> ();
		List<Integer> temp = new ArrayList<Integer> ();
		for(int i=0; i<n; i++) {
			int current = x.get(i);
			temp.add(current);
			if(temp.size() == current) {
				total.add(temp);
				temp = new ArrayList<Integer> ();
			}
		}
		//System.out.println(total);
		System.out.println(total.size());
	}
}
