package C13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q20 {
	
	private static int N;
	private static String[][] map;
	private static List<Pair> teachers;
	private static List<Pair> candidates;
	private static List<List<Pair>> obsCombList;
	private static String result;

	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer stringTokenizer = new StringTokenizer(line);
		N = Integer.parseInt(stringTokenizer.nextToken());

		map = new String[N][N];
		for(int i=0; i<N; i++) {
			line = br.readLine();
			stringTokenizer = new StringTokenizer(line);
			for(int j=0; j<N; j++) {
				map[i][j] = stringTokenizer.nextToken();
			}
		}
		
		// 선생님 및 공백(장애물 후보) 위치 파악
		teachers = new ArrayList<> ();
		candidates = new ArrayList<> ();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				switch(map[i][j]) {
				case "T":
				case "X":
					Pair p = new Pair(i, j);
					if(map[i][j].equals("T")) {
						teachers.add(p);
					} else {
						candidates.add(p);
					}
					break;
				}
			}
		}
		
		// 장애물 3개 선정(조합) 후 T를 움직여 S와 만나는지 확인
		result = "NO";
		obsCombList = new ArrayList<> ();
		boolean[] visited = new boolean[candidates.size()];
		comb(candidates, visited, 0, candidates.size(), 3);
		
		for(List<Pair> obstacles : obsCombList) {
			
			// 임시 지도 생성
			String[][] tempMap = copyMap(map);
			for(int i=0; i<3; i++) {
				tempMap[obstacles.get(i).x][obstacles.get(i).y] = "O";
			}
			
			// T를 상, 하, 좌, 우 움직여 S와 만나는지 확인
			int[] dx = {-1, 1, 0, 0};
			int[] dy = {0, 0, -1, 1};
			boolean isSupervised = false;
			for(Pair t : teachers) {
				for(int i=0; i<4; i++) {
					Pair next = new Pair(t.x + dx[i], t.y + dy[i]);
					while(isValidPosition(next, tempMap)) {
						if(tempMap[next.x][next.y].equals("S")) {
							// 감시 성공
							isSupervised = true;
							break;
						}
						next.x += dx[i];
						next.y += dy[i]; 
					}
					if(isSupervised) {
						break;
					}
				}
				if(isSupervised) {
					break;
				}
			}
			if(!isSupervised) {
				result = "YES";
				break;
			}
		}
		System.out.println(result);
	}
	
	public static boolean isValidPosition(Pair p, String[][] m) {
		return p.x >= 0 && p.x < N && p.y >= 0 && p.y < N && !m[p.x][p.y].equals("O");
	}
	
	public static void comb(List<Pair> pairs, boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            // 조합 종료
        	List<Pair> obstacles = new ArrayList<> ();
			for(int i=0; i<visited.length; i++) {
				if(visited[i]) {
					obstacles.add(pairs.get(i));
				}
			}
			obsCombList.add(obstacles);
            return;
        }
        
        if (depth == n) {
            return;
        }

        visited[depth] = true;
        comb(pairs, visited, depth + 1, n, r - 1);
        visited[depth] = false;
        comb(pairs, visited, depth + 1, n, r);
    }
	
	public static String[][] copyMap(String[][] original) {
		String[][] newMap = new String[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				newMap[i][j] = original[i][j];
			}
		}
		return newMap;
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
