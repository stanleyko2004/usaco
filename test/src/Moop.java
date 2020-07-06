import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Moop {
	public static boolean[] traversed;
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("moop.in")));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
		int n = Integer.parseInt(reader.readLine());
		Node[] particles = new Node[n];
		for (int i=0; i<n; i++){
			StringTokenizer st = new StringTokenizer(reader.readLine());
			particles[i] = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		reader.close();
		//drawing edges
		for (int i=0; i<n; i++){
			for (int j=i+1; j<n; j++){
				if ((particles[i].x<=particles[j].x && particles[i].y<=particles[j].y) || (particles[i].x>=particles[j].x && particles[i].y>=particles[j].y)){
					particles[i].neighbours.add(j);
					particles[j].neighbours.add(i);
				}
			}
		}
		//traverse to find cc
		traversed = new boolean[n];
		LinkedList<Integer> cur = new LinkedList<Integer>();
		int cc = 0;
		for (int i=0; i<n; i++){
			if (!traversed[i]){
				cur.push(i);
				while (!cur.isEmpty()){
					int next = cur.poll();
					if (traversed[next]){
						continue;
					}
					traversed[next] = true;
					for (Integer j: particles[next].neighbours){
						if (!traversed[j]){
							cur.push(j);
						}
					}
				}
				cc++;
			}
		}
		writer.println(cc);
		writer.close();
	}
	public static class Node{
		public ArrayList<Integer> neighbours;
		public int x, y;
		public Node(int x, int y){
			this.x = x;
			this.y = y;
			neighbours = new ArrayList<Integer>();
		}
	}
}
