package C13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16 {
	private static int N;
	private static int M;
	private static int[][] map;
	private static int max = 0;
	
	public static void combination(List<Pair> blanks, boolean[] visited, int depth, int n, int r) {
		if(r == 0)  {
			// 종료
			List<Pair> walls = new ArrayList<> ();
			for(int i=0; i<visited.length; i++) {
				if(visited[i]) {
					walls.add(blanks.get(i));
				}
			}
			int[][] temp = makeMap(map, walls);
			int safe = bfs(temp);
			if(safe > max) {
				max = safe;
			}
			return;
		}
		if(n == depth) {
			return;
		}
		visited[depth] = true;
		combination(blanks, visited, depth+1, n, r-1);
		visited[depth] = false;
		combination(blanks, visited, depth+1, n, r);
	}
	
	public static int bfs(int[][] map) {
		Queue<Pair> queue = new LinkedList<> ();
		boolean[][] visited = new boolean[N][M];
		List<Pair> virus = new ArrayList<> ();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 2) {
					virus.add(new Pair(i, j));
				}
			}
		}
		// 상, 하, 좌, 우
		Pair[] directions = {new Pair(-1, 0), new Pair(1, 0), new Pair(0, -1), new Pair(0, 1)};
		int virusCount = 0;
		for(Pair p : virus) {
			if(!visited[p.x][p.y]) {
				queue.add(p);
				visited[p.x][p.y] = true;
				while(!queue.isEmpty()) {
					Pair current = queue.poll();
					virusCount++;
					// 인접 노드 방문
					for(Pair direction : directions) {
						Pair next = new Pair(current.x + direction.x, current.y + direction.y);
						if(next.x>=0 && next.x < N && 
								next.y>=0 && next.y < M &&
								map[next.x][next.y] == 0 &&
								!visited[next.x][next.y]) {
							queue.add(next);
							visited[next.x][next.y] = true;
						}
					}
				}
			}
		}
		// 벽 카운트
		int wall = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					wall++;
				}
			}
		}
		return N * M - wall - virusCount;
	}
	
	public static int[][] makeMap(int[][] origin, List<Pair> walls) {
		int[][] result = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				result[i][j] = origin[i][j];
			}
		}
		for(Pair p : walls) {
			result[p.x][p.y] = 1;
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer stringTokenizer = new StringTokenizer(line);
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[N][M];
		for(int i=0; i<N; i++) {
			line = br.readLine();
			stringTokenizer = new StringTokenizer(line);
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		List<Pair> blanks = new ArrayList<> ();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					Pair p = new Pair(i, j);
					blanks.add(p);
				}
			}
		}
		
		boolean[] visited = new boolean[blanks.size()];
		combination(blanks, visited, 0, blanks.size(), 3);
		System.out.println(max);
	}

	public static class Pair {
		public int x;
		public int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
