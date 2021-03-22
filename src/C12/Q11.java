package C12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q11 {

	public static void main(String[] args) throws IOException {
		int answer = 0;
		
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] map = null;
		map = new int[N+1][N+1];
		map[1][1] = 1;
		for(int i=0; i<K; i++) {
			String line = br.readLine();
			StringTokenizer tokenizer = new StringTokenizer(line);
			map[Integer.parseInt(tokenizer.nextToken())][Integer.parseInt(tokenizer.nextToken())] = 2; 
		}
		int L = Integer.parseInt(br.readLine());
		char[] turn = new char[10001];
		for(int i=0; i<L; i++) {
			String line = br.readLine();
			StringTokenizer tokenizer = new StringTokenizer(line);
			turn[Integer.parseInt(tokenizer.nextToken())] = tokenizer.nextToken().charAt(0);
		}
		br.close();
		
		LinkedList<Pair> snake = new LinkedList<> ();
		Pair priv = new Pair(1, 1);
		snake.add(priv);
		int direction = 0;
		for(int i=1; ; i++) {
			// 다음 위치 설정
			Pair temp = snake.getLast();
			Pair next = new Pair(temp.row, temp.col);
			switch (direction) {
			case 0:
				next.col++;
				break;
			case 1:
				next.row++;
				break;
			case 2:
				next.col--;
				break;
			case 3:
				next.row--;
				break;
			}
			
			// 종료 조건 확인 (벽 또는 자기 자신)
			if(next.row == 0 || next.row == N+1 || next.col == 0 || next.col == N+1
					|| map[next.row][next.col] == 1) {
				answer = i;
				break;
			}
			
			// 뱀 머리 이동
			// 사과 존재 유무 확인
			snake.add(next);
			if(map[next.row][next.col] == 2) {
				map[next.row][next.col] = 1;
			} else if(map[next.row][next.col] == 0) {
				map[next.row][next.col] = 1;
				// 꼬리 당겨짐
				Pair tail = snake.removeFirst();
				map[tail.row][tail.col] = 0;
			}
			
			// 방향 전환 확인
			if(turn[i] == 'D') {
				direction = (direction+1)%4;
			} else if(turn[i] == 'L') {
				direction = (direction+3)%4;
			}
		}
		System.out.println(answer);
	}
}

class Pair {
	int row;
	int col;
	
	public Pair(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
