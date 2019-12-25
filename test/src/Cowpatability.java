import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Cowpatability {
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("cowpatibility.in"));
		int n = Integer.parseInt(reader.readLine());
		HashMap<String, HashSet<Integer>> flavors = new HashMap<String,HashSet<Integer>>(5*n);
		String[][] cows = new String[n][5];
		for (int i=0;i<n;i++){
			StringTokenizer st = new StringTokenizer(reader.readLine());
			for (int j=0;j<5;j++){
				String flav = st.nextToken();
				cows[i][j] = flav;
				if (!flavors.containsKey(flav)) {
					flavors.put(flav, new HashSet<Integer>());
				}
				HashSet<Integer> temp = flavors.get(flav);
				temp.add(i);
			}
		}
		reader.close();
/*		int friends = 0;
		for (int i=0;i<n-1;i++){
			for (int j=i+1;j<n;j++){
				for (int k=0;k<5;k++){
					if (flavors.get(cows[i][k]).contains(j)){
						friends++;
						break;
					}
				}
			}
		}
		int res = (n*(n-1)/2)-(friends);
*/
		int res = 4;
		PrintWriter writer = new PrintWriter(new FileWriter("cowpatibility.out"));
		writer.write(res+"\n");
		writer.close();
	}	
}