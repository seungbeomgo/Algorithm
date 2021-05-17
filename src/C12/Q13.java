package C12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q13 {
	
	private static int N;
	private static int M;
	private static int[][] map;
	private static PriorityQueue<Integer> pq = new PriorityQueue<> ();
	private static List<Pair> chickens = new ArrayList<> ();
	private static List<Pair> houses = new ArrayList<> ();
	
	public static int answer(int[][] map) {
		int result = 0;
		// 집 및 치킨 집 설정
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) {
					houses.add(new Pair(i, j));
				} else if(map[i][j] == 2) {
					chickens.add(new Pair(i, j));
				}
			}
		}
		// 치킨 집 조합 및 거리 계산
		for(int i=1; i<=M; i++) {
			boolean[] visited = new boolean[chickens.size()];
			dfs(chickens, visited, 0, chickens.size(), i);
		}
		result = pq.peek();
		return result;
	}
	
	public static void dfs(List<Pair> chickens, boolean[] visited, int depth, int n, int r) {
		if(r == 0) {
			// 조합 종료 및 거리 계산
			List<Pair> survived = new ArrayList<> ();
			for(int i=0; i<visited.length; i++) {
				if(visited[i]) {
					survived.add(chickens.get(i));
				}
			}
			pq.add(chickenDistance(houses, survived));
			return;
		}
		if(n == depth) {
			return;
		}
		visited[depth] = true;
		dfs(chickens, visited, depth+1, n, r-1);
		visited[depth] = false;
		dfs(chickens, visited, depth+1, n, r);
	}
	
	public static int chickenDistance(List<Pair> houses, List<Pair> chickens) {
		int sum = 0;
		for(int i=0; i<houses.size(); i++) {
			int min = Integer.MAX_VALUE;
			for(int j=0; j<chickens.size(); j++) {
				if(Pair.dist(houses.get(i), chickens.get(j)) < min) {
					min = Pair.dist(houses.get(i), chickens.get(j));
				}
			}
			sum += min;
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer stringTokenizer = new StringTokenizer(line);
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			line = br.readLine();
			stringTokenizer = new StringTokenizer(line);
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		System.out.println(answer(map));
	}
	
	public static class Pair {
		public int row;
		public int col;
		
		public Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public static int dist(Pair a, Pair b) {
			return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
		}
	}
}