import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Gates {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("gates.in"));
		int n = Integer.parseInt(reader.readLine());
		String dir = reader.readLine();
		int x = 0;
		int y = 0;
		Set<String> edges = new HashSet<String>();
		Set<String> vertices = new HashSet<String>();
		vertices.add(x+" "+y);
		for (int i=0;i<n;i++){
			int prevX = x;
			int prevY = y;
			char cur = dir.charAt(i);
			if (cur == 'N'){
				y++;
			} else if (cur == 'S'){
				y--;
			} else if (cur == 'E'){
				x++;
			} else {
				x--;
			}
			vertices.add(x+" "+y);
			//prevent backtrack
			if (cur == 'N' || cur == 'E'){
				edges.add(x+" "+y+" "+prevX+" "+prevY);
			} else {
				edges.add(prevX+" "+prevY+" "+x+" "+y);
			}
		}
		reader.close();
		BufferedWriter writer = new BufferedWriter(new FileWriter("gates.out"));
		writer.write(Integer.toString(edges.size()-vertices.size()+1));
		writer.close();
	}
}
