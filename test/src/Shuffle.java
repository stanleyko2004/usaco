import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Shuffle {
	public static void main(String[] args) throws IOException{
		Scanner reader = new Scanner(new FileReader("shuffle.in"));
		int n = reader.nextInt();
		int[] to = new int[n];
		int[] parent = new int[n];
		for (int i=0;i<n;i++){
			to[i] = reader.nextInt()-1;
			parent[to[i]]++;
		}
		reader.close();
		LinkedList<Integer> q = new LinkedList<Integer>();
		int ans = n;
		for (int i=0;i<n;i++){
			if (parent[i]==0){
				q.add(i);
				ans--;
			}
		}
		while(!q.isEmpty()){
			int cur = q.removeFirst();
			if (--parent[to[cur]]==0){
				q.add(to[cur]);
				ans--;
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("shuffle.out"));
		writer.write(Integer.toString(ans));
		writer.close();
	}
}
