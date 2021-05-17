package C13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q17 {
	
	private static int N, K, S, X, Y;
	private static int[][] map;
	private static PriorityQueue<Cell> pq;
	private static Cell[] directions;
	
	public static int infect(int X, int Y) {
		// Step 1 - 바이러스 종류 K를 기준으로 순서대로 큐에 저장(priority queue)
		pq = new PriorityQueue<> (new CustomComparator());
		for(int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(map[i][j] > 0) {
					pq.offer(new Cell(map[i][j], 0, i, j));	
				}
			}
		}
			
		// Step 2 - 큐를 순회하면서 상하좌우 전염
		// 상, 하, 좌, 우 이동용 배열
		directions = new Cell[] {
				new Cell(0, 1, -1, 0), new Cell(0, 1, 1, 0), new Cell(0, 1, 0, -1), new Cell(0, 1, 0, 1)};
		
		Cell next = null;
		// 큐 순회
		while(!pq.isEmpty()) {
			Cell current = pq.poll();
			// 종료 조건 1 - S초 이후
			if(current.time == S) {
				return map[X-1][Y-1];
			}
			// 상, 하, 좌, 우 이동
			for(int j=0; j<4; j++) {
				next = new Cell(current.level, current.time + directions[j].time, 
						current.row + directions[j].row, current.col + directions[j].col);
				// 전염
				if(next.row >= 0 && next.row < N &&
						next.col >= 0 && next.col < N &&
						map[next.row][next.col] == 0) {
					map[next.row][next.col] = next.level;
					pq.offer(next);
					// 종료 조건 2 - X, Y에 virus 전염
					if(next.row == X-1 && next.col == Y-1) {
						return map[X-1][Y-1];
					}
				}
			}
		}
		// 종료 조건 3 - 더이상 움직일 수 없을 때 
		return map[X-1][Y-1];
	}

	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer stringTokenizer = new StringTokenizer(line);
		N = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[N][N];
		for(int i=0; i<N; i++) {
			line = br.readLine();
			stringTokenizer = new StringTokenizer(line);
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		line = br.readLine();
		stringTokenizer = new StringTokenizer(line);
		S = Integer.parseInt(stringTokenizer.nextToken());
		X = Integer.parseInt(stringTokenizer.nextToken());
		Y = Integer.parseInt(stringTokenizer.nextToken());
		
		System.out.println(infect(X, Y));
	}

	public static class Cell {
		public int level;
		public int time;
		public int row;
		public int col;
		
		public Cell(int level, int time, int row, int col) {
			this.level = level;
			this.time = time;
			this.row = row;
			this.col = col;
		}
	}
	
	public static class CustomComparator implements Comparator<Cell> {
		@Override
		public int compare(Cell o1, Cell o2) {
			if(o2.time != o1.time) {
				return o2.time > o1.time ? -1 : 1;
			}
			return o2.level >= o1.level ? -1 : 1;
		}
	}
}
