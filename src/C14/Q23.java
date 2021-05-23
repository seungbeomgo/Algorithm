package C14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q23 {
	
	public static int N;

	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer stringTokenizer = new StringTokenizer(line);
		N = Integer.parseInt(stringTokenizer.nextToken());
		
		PriorityQueue<Student> pq = new PriorityQueue<> (new StudentComparator());
		for(int i=0; i<N; i++) {
			line = br.readLine();
			stringTokenizer = new StringTokenizer(line);
			String name = stringTokenizer.nextToken();
			int kor = Integer.parseInt(stringTokenizer.nextToken());
			int eng = Integer.parseInt(stringTokenizer.nextToken());
			int math = Integer.parseInt(stringTokenizer.nextToken());
			Student student = new Student(name, kor, eng, math);
			pq.offer(student);
		}
		
		while(!pq.isEmpty()) {
			System.out.println(pq.poll().name);
		}
	}

	public static class Student {
		public String name;
		public int kor, eng, math;
		public Student(String name, int kor, int eng, int math) {
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.math = math;
		}
	}
	
	public static class StudentComparator implements Comparator<Student> {

		@Override
		public int compare(Student s1, Student s2) {
			if(s1.kor != s2.kor) {
				return s1.kor - s2.kor > 0 ? -1 : 1;
			} else if(s1.eng != s2.eng) {
				return s1.eng - s2.eng > 0 ? 1 : -1;
			} else if(s1.math != s2.math) {
				return s1.math - s2.math > 0 ? -1 : 1;
			} else {
				return s1.name.compareTo(s2.name);
			}
		}
	}
}