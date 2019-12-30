package algorithms;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {
	public static int n;
	public static boolean[] traversed;
	public static Node[] graph;
	public static NodeH[] graphH;
	public static void main(String[] args) throws IOException{
		n = 4;
/*		
		//testing dijkstra
		graph = new Node[n];
		for (int i=0; i<n; i++){
			graph[i] = new Node();
		}
		int[][] c = new int[][]{{0,1,10},{0,2,8},
			{1,2,10},{1,3,10},{1,0,1},
			{2,1,10},{2,3,5},{2,0,8},
			{3,1,1}}; //the connections
		for (int i=0; i<c.length; i++){
			graph[c[i][0]].neighbors.add(new CEdge(c[i][1],c[i][2]));
		}
		dijkstra(0);
		for (int i=0;i<n;i++){
			System.out.println(i+" "+graph[i].sp);
		}
*/		
		//testing dijkstraH
		graphH = new NodeH[n];
		for (int i=0; i<n; i++){
			graphH[i] = new NodeH();
		}
		int[][] c = new int[][]{{0,1,10},{0,2,8},
			{1,2,10},{1,3,10},{1,0,1},
			{2,1,10},{2,3,5},{2,0,8},
			{3,1,1}}; //the connections
		for (int i=0; i<c.length; i++){
			graphH[c[i][0]].neighbors.add(new CEdge(c[i][1],c[i][2]));
		}
		dijkstraH(3);
		for (int i=0; i<n; i++){
			System.out.print(i+" "+graphH[i].sp);
			System.out.println();
			for (int j : graphH[i].path){
				System.out.print(" "+j);
			}
			System.out.println();
			System.out.println();
		}
	}
	/* uses CEdge, Node, Node[] graph, boolean[] traversed, int n (number of vertices)
	 * graph must have a distinct path from any node to any other node
	 */
	public static void dijkstra(int sourceNode){
		//only use y in Comparable Edge since x will be the source node
		PriorityQueue<CEdge> pq = new PriorityQueue<CEdge>();
		traversed = new boolean[n];
		pq.add(new CEdge(sourceNode, 0));//starting point
		graph[sourceNode].sp = 0;
		for (int i=0; i<n; i++){
			CEdge next = pq.poll();
			while (traversed[next.y]){//make sure "next" doesn't go to an already-visited node
				next = pq.poll();
			}
			traversed[next.y] = true;
			graph[next.y].sp = next.w;
			for (CEdge j : graph[next.y].neighbors){//add all neighbors into priority queue
				pq.add(new CEdge(j.y, next.w+j.w));
			}
		}
	}
	/* uses CEdge, NodeH, NodeH[] graphH, boolean[] traversed, int n (number of vertices)
	 * graph must have a distinct path from any node to any other node
	 */
	public static void dijkstraH(int sourceNode){//dijkstra with history, traces shortest path
		//only use y in Comparable Edge since x will be the source node
		PriorityQueue<CEdge> pq = new PriorityQueue<CEdge>();
		traversed = new boolean[n];
		pq.add(new CEdge(sourceNode, sourceNode, 0));//starting point
		graphH[sourceNode].sp = 0;
		for (int i=0; i<n; i++){
			CEdge next = pq.poll();
			while (traversed[next.y]){//make sure "next" doesn't go to an already-visited node
				next = pq.poll();
			}
			traversed[next.y] = true;
			graphH[next.y].sp = next.w;
			for (int j : graphH[next.x].path){
				graphH[next.y].path.add(j);
			}
			graphH[next.y].path.add(next.y);
			for (CEdge j : graphH[next.y].neighbors){//add all neighbors into priority queue
				pq.add(new CEdge(next.y, j.y, next.w+j.w));
			}
		}
	}
	public static class Node{
		public int sp,id,w;//shortest path, id, weight
		public ArrayList<CEdge> neighbors;
		public Node(){
			neighbors = new ArrayList<CEdge>();
		}
		public Node(int ID, int shortestPath){
			sp = shortestPath;
			id = ID;
		}
	}
	public static class NodeH{//node for dijkstra with history
		public int sp,id,w;//shortest path, id, weight
		public ArrayList<CEdge> neighbors;
		public ArrayList<Integer> path;
		public NodeH(){
			neighbors = new ArrayList<CEdge>();
			path = new ArrayList<Integer>();
		}
		public NodeH(int ID, int shortestPath){
			sp = shortestPath;
			id = ID;
		}
	}
	public static class CEdge implements Comparable<CEdge>{//Comparable Edge: lightest edge has higher priority
		public int x,y,w;
		public CEdge(int y, int w){
			this.y = y;
			this.w = w;
		}
		public CEdge(int x, int y, int w){
			this.x = x;
			this.y = y;
			this.w = w;
		}
		public int compareTo(CEdge other){
			return w-other.w;
		}
	}
}
