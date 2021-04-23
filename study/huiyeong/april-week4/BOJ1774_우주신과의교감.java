package STUDY.April._4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1774_우주신과의교감 {
	static int N, M;
	static int[] roots;
	static List<Edge> edgeList;
	static class Edge implements Comparable<Edge>{
		int from, to;
		double weight;
		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
		@Override
		public String toString() {
			return from+" "+to+" "+weight;
		}
		
	}
	
	static void makeSet() {
		for (int i=1;i<=N;i++) {
			roots[i] = -1;
		}
	}
	
	static int findSet(int x) {
		if (roots[x]<0) return x;
		return roots[x] = findSet(roots[x]);
	}
	
	static boolean union(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if (x==y) return false;
		roots[y] = x;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 우주선의 개수
		M = Integer.parseInt(st.nextToken()); // 이미 연결된 신들과의 통로 개수
		int[][] plate = new int[N+1][2];
		roots = new int[N+1];
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			plate[i][0] = Integer.parseInt(st.nextToken());
			plate[i][1] = Integer.parseInt(st.nextToken());
		}
		
		edgeList = new ArrayList<>();
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				if (i!=j) {
					double dis = getDis(plate[i][0], plate[i][1], plate[j][0], plate[j][1]);
					edgeList.add(new Edge(i, j, dis));
				}
			}
		}
		
		makeSet();
		double cost = 0;
		int cnt = 0;
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (union(from, to)) cnt++;
		}
		
		Collections.sort(edgeList);
		
		for (Edge edge:edgeList) {
			if (union(edge.from, edge.to)) {
				cost+=edge.weight;
				if (cnt++ == N-1 ) break;
			}
		}
		
		System.out.printf("%.2f", cost);
	}
	
	static double getDis(int sX, int sY, int eX, int eY) {
		return Math.sqrt(Math.pow(Math.abs(eY-sY), 2)+Math.pow(Math.abs(sX-eX), 2));
	}
}	
