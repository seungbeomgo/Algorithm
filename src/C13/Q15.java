package C13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q15 {
	private static int N;
	private static int M;
	private static int K;
	private static int X;
	private static List<Integer>[] graph;
	private static Node[] city;
	private static Queue<Integer> queue;
	private static PriorityQueue<Integer> pq;
	
	public static void bfs() {
		// 도시 초기화(visit 배열)
		city = new Node[N+1];
		for(int i=1; i<=N; i++) {
			city[i] = new Node(i);
		}
		
		// 출발지 삽입 및 방문 처리
		queue = new LinkedList<> ();
		queue.add(X);
		city[X].visit = true;
		
		// 이동 가능한 인접 경로 돌면서 방문 처리
		while(!queue.isEmpty()) {
			int current = queue.poll();
			for(int i : graph[current]) {
				if(!city[i].visit) {
					queue.add(i);
					city[i].cost = city[current].cost + 1;
					city[i].visit = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		/* Input From Keyboard*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer stringTokenizer = new StringTokenizer(line);
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());
		X = Integer.parseInt(stringTokenizer.nextToken());
		
		// 도로에 존재하는 간선 리스트 초기화
		graph = new List[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<> ();
		}
		
		// 간선 저장
		for(int i=0; i<M; i++) {
			line = br.readLine();
			stringTokenizer = new StringTokenizer(line);
			int start = Integer.parseInt(stringTokenizer.nextToken());
			int end = Integer.parseInt(stringTokenizer.nextToken());
			graph[start].add(end);
		}
		
		// BFS
		bfs();
		
		// 최단 거리 K 찾기
		pq = new PriorityQueue<> ();
		for(int i=1; i<=N; i++) {
			if(city[i].cost == K) {
				pq.add(city[i].node);
			}
		}
		
		// 오름차순 출
		if(!pq.isEmpty()) {
			while(!pq.isEmpty()) {
				System.out.println(pq.poll());
			}
		} else {
			System.out.println("-1");
		}
	}
	
	public static class Node {
		public int node;
		public int cost = 0;
		public boolean visit = false;
		
		public Node(int node) {
			this.node = node;
		}
	}
}