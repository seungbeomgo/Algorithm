package C13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Q21 {
	
	/* 문제 해결 전략
	 * 1. 모든 지도를 돌면서(단순 이중 포문 탐색) 인구 이동할 지역 간선 생성
	 * 2. 지역 간선 탐색(BFS)하면서 인구 이동
	 * 참고: 인구 이동 그룹이 여러개 생성될 수 있음
	*/
	
	public static int N, L, R;
	public static int[][] map;
	public static Set<Pair>[][] graph;

	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer stringTokenizer = new StringTokenizer(line);
		N = Integer.parseInt(stringTokenizer.nextToken());
		L = Integer.parseInt(stringTokenizer.nextToken());
		R = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[N][N];
		for(int i=0; i<N; i++) {
			line = br.readLine();
			stringTokenizer = new StringTokenizer(line);
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		solution();
	}
	
	public static void solution() {
		int count = 0;
		
		// 지역 간선 생성
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		while(true) {
			boolean needMove = false;
			graph = new Set[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					Pair current = new Pair(i, j);
					graph[i][j] = new HashSet<Pair> ();
					for(int k=0; k<4; k++) {
						Pair next = new Pair(i + dx[k], j + dy[k]);
						if(isMigration(current, next)) {
							needMove = true;
							graph[i][j].add(next);
						}
					}
				}
			}
			// 인구 이동할 간선이 생성되지 않으면 종료
			if(!needMove) {
				break;
			}
			
			// 간선을 돌면서 인구 이동할 집단 생성
			Set<Set<Pair>> migrationGroupSet = new HashSet<> ();
			Queue<Pair> queue = new LinkedList<> ();
			boolean[][] visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j] == false) {
						Set<Pair> migrationGroup = new HashSet<> ();
						Pair country = new Pair(i, j);
						queue.offer(country);
						migrationGroup.add(country);
						visited[i][j] = true;
						while(!queue.isEmpty()) {
							Pair current = queue.poll();
							for(Pair next : graph[current.r][current.c]) {
								if(visited[next.r][next.c] == false) {
									queue.offer(next);
									migrationGroup.add(next);
									visited[next.r][next.c] = true;
								}
							}
						}
						migrationGroupSet.add(migrationGroup);
					}
				}
			}
			
			// 인구 이동
			for(Set<Pair> group : migrationGroupSet) {
				int total = 0;
				int avg = 0;
				for(Pair p : group) {
					total += map[p.r][p.c];
				}
				avg = total / group.size();
				for(Pair p : group) {
					map[p.r][p.c] = avg;
				}
			}
			count++;
		}
		System.out.println(count);
	}
	
	public static boolean isMigration(Pair current, Pair next) {
		if(next.r < 0 || next.r >= N || next.c < 0 || next.c >= N) {
			return false;
		}
		int diff = Math.abs(map[current.r][current.c] - map[next.r][next.c]);
		if(diff >= L && diff <= R) {
			return true;
		} else {
			return false;
		}
	}
	
	public static class Pair {
		public int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}