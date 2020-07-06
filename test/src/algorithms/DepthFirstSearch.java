package algorithms;

import java.io.IOException;
import java.util.Stack;

public class DepthFirstSearch {
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
		int[] ans = ts(1);
		for (int i=0; i<n; i++){
			System.out.println(ans[i]);
		}
	}
	//usually for tree, v vertices with v-1 edges and no cycles
	public static void dfs(int sourceNode){//iterative
		Stack<Integer> cur = new Stack<Integer>();
		cur.push(sourceNode);
		traversed = new boolean[n];
		traversed[sourceNode] = true;
		//usually do something here
		for (int i=0; i<n-1; i++){
			//find next
			while (graph[cur.peek()].neighbors.isEmpty()){
				cur.pop();
			}
			int next = graph[cur.peek()].neighbors.pop();
			while (traversed[next]){
				while (graph[cur.peek()].neighbors.isEmpty()){
					cur.pop();
				}
				next = graph[cur.peek()].neighbors.pop();
			}
			traversed[next] = true;
			//do operation
			System.out.println(next);
			//push next on top
			cur.push(next);
		}
	}
	public static int[] ts(int sourceNode){//topological sort
		Stack<Integer> cur = new Stack<Integer>();
		cur.push(sourceNode);
		traversed = new boolean[n];
		traversed[sourceNode] = true;
		//usually do something here
		int ct = 0;
		int[] ans = new int[n];
		for (int i=0; i<n-1; i++){
			//find next
			while (graph[cur.peek()].neighbors.isEmpty()){
				ans[ct++] = cur.pop();
			}
			int next = graph[cur.peek()].neighbors.pop();
			while (traversed[next] && !cur.isEmpty()){
				while (graph[cur.peek()].neighbors.isEmpty()){
					ans[ct++] = cur.pop();
				}
				next = graph[cur.peek()].neighbors.pop();
			}
			traversed[next] = true;
			//do operation
			//System.out.println(next);
			//push next on top
			cur.push(next);
		}
		while (!cur.isEmpty()){
			ans[ct++] = cur.pop();
		}
		return ans;
	}
	public static class Node{
		public Stack<Integer> neighbors;
		public Node(){
			neighbors = new Stack<Integer>();
		}
	}
}
