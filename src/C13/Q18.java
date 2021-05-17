package C13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q18 {
	
	public static String solution(String p) {
		String answer = "";
		answer = convert(p);
        return answer;
	}
	
	public static String convert(String input) {
		String result = "";
		if(input != null && !input.isEmpty()) {
			String u = "", v = "";
			int open = 0, close = 0;
			for(int i=0; i<input.length(); i++) {
				if(input.charAt(i) == '(' ) {
					open++;
				} else {
					close++;
				}
				if(open == close) {
					u = input.substring(0, i+1);
					if(i < input.length()) {
						v = input.substring(i+1);
					}
					break;
				}
			}
			if(isGreat(u)) {
				v = convert(v);
				result = u + v;
			} else {
				String temp = "(";
				temp += convert(v);
				temp += ")";
				u = u.substring(1, u.length()-1);
				temp += reverse(u);
				result = temp;
			}
		}
		return result;
	}
	
	public static boolean isGreat(String input) {
		boolean result = true;
		int openCnt = 0, closeCnt = 0;
		for(int i=0; i<input.length(); i++) {
			if(input.charAt(i) == '(' ) {
				openCnt++;
			} else {
				closeCnt++;
			}
			if(closeCnt > openCnt) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	public static String reverse(String input) {
		String result = input.replaceAll("\\(", "-");
		result = result.replaceAll("\\)", "(");
		result = result.replaceAll("-", ")");
		return result;
	}

	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(solution(br.readLine()));
	}
}
