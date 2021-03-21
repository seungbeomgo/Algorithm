package C12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Q09 {
	
	public static int solution(String s) {
        int answer = 0;
        PriorityQueue<Integer> pqueue = new PriorityQueue<> ();
        if(s.length() == 1) {
        	return 1;
        }
        List<String> strList = new ArrayList<> ();
        for(int i=1; i<=s.length()/2; i++) {
        	int j=0;
        	while(true) {
    			if(j+i <= s.length()) {
    				String temp = s.substring(j, j+i);
    				strList.add(temp);
    				if(j+i == s.length()) {
    					break;
    				}
    			} else {
    				if(j <= s.length()) {
    					String temp = s.substring(j);
    					strList.add(temp);
    					break;
    				}
    			}
        		j+=i;
        	}
        	String priv = strList.get(0);
        	String compress = "";
        	String temp = priv;
        	int count = 1;
        	for(int k=1; k<strList.size(); k++) {
        		if(priv.equals(strList.get(k))) {
        			count++;
        			temp = count + priv;
        		} else {
        			compress += temp;
        			priv = strList.get(k);
        			temp = priv;
        			count = 1;
        		}
        	}
        	compress += temp;
        	pqueue.add(compress.length());
        	strList = new ArrayList<> ();
        }
        answer = pqueue.peek();
        return answer;
    }

	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		br.close();
		
		System.out.println(solution(line));
	}
}
