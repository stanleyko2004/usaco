import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FencePlan {
	public static boolean[] checked;
	public static ArrayList<Integer>[] connections;
	public static int[][] coords;
	public static int n,m;
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("fenceplan.in"));
		n = reader.nextInt();
		m = reader.nextInt();
		checked = new boolean[n];
		coords = new int[n][2];
		for (int i=0;i<n;i++){
			coords[i][0] = reader.nextInt();
			coords[i][1] = reader.nextInt();
		}
		connections = new ArrayList[n];
		for (int i=0;i<n;i++){
			connections[i] = new ArrayList<Integer>();
		}
		for (int i=0;i<m;i++){
			int v1 = reader.nextInt()-1;
			int v2 = reader.nextInt()-1;
			connections[v1].add(v2);
			connections[v2].add(v1);
		}
		int perimeter = Integer.MAX_VALUE;
		for (int i=0;i<n;i++){
			if (!checked[i]){
				checked[i] = true;
				int[] dim = floodfill(i,new int[]{coords[i][0],coords[i][1],coords[i][0],coords[i][1]});
				perimeter = Math.min(perimeter, 2*(dim[3]-dim[1]+dim[2]-dim[0]));
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("fenceplan.out"));
		writer.write(Integer.toString(perimeter));
		writer.close();
		reader.close();
	}
	public static int[] floodfill(int x,int[] y){
		for (int i:connections[x]){
			if (!checked[i]){
				checked[i] = true;
				y[0] = Math.min(y[0], coords[i][0]);
				y[1] = Math.min(y[1], coords[i][1]);
				y[2] = Math.max(y[2], coords[i][0]);
				y[3] = Math.max(y[3], coords[i][1]);
				y = floodfill(i,y);
			}
		}
		return y;
	}
}
