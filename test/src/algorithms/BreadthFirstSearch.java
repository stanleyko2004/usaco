package algorithms;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

public class BreadthFirstSearch {
	public static int n;//number of vertices
	public static Node[] graph;
	public static boolean[] traversed;
	public static void main(String[] args) throws IOException{
		n = 9;//number of vertices
		graph = new Node[n];
		for (int i=0; i<n; i++){
			graph[i] = new Node();
		}
		int[][] c = new int[][]{{0,1},{0,2},
			{2,3},{2,4},
			{3,5},{3,6},
			{4,7},{4,8}};
		for (int i=0; i<n-1; i++){
			graph[c[i][0]].neighbors.add(c[i][1]);
			graph[c[i][1]].neighbors.add(c[i][0]);
		}
		bfs(1);
	}
	//usually for tree, v vertices with v-1 edges and no cycles
	public static void bfs(int sourceNode){//iterative
		LinkedList<Integer> cur = new LinkedList<Integer>();
		cur.add(sourceNode);
		traversed = new boolean[n];
		//usually do something here
		for (int i=0; i<n; i++){
			//find next
			int next = cur.poll();
			while (traversed[next]){
				next = cur.poll();
			}
			//mark it
			traversed[next] = true;
			//do operation
			System.out.println(next);
			//add that node's neighbors
			while (!graph[next].neighbors.isEmpty()){
				cur.add(graph[next].neighbors.pop());
			}
		}
	}
	public static class Node{
		public Stack<Integer> neighbors;
		public Node(){
			neighbors = new Stack<Integer>();
		}
	}
}
